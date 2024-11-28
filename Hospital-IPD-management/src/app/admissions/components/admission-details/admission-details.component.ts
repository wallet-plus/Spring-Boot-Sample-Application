import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AdmissionService } from 'src/app/services/admission.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-admission-details',
  templateUrl: './admission-details.component.html',
  styleUrls: ['./admission-details.component.scss']
})
export class AdmissionDetailsComponent {
  admissionForm!: FormGroup;
  isEditMode = false;
  selectedAdmissionId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private admissionService: AdmissionService, // Replace with your service
    private router: Router
  ) {}

  ngOnInit(): void {
    this.initForm();

    const admissionId = this.route.snapshot.paramMap.get('id');
    if (admissionId) {
      this.selectedAdmissionId = +admissionId;
      this.isEditMode = true;
      this.loadAdmissionDetails();
    }
  }

  // Initialize the form
  initForm() {
    this.admissionForm = this.fb.group({
      patientId: [null, Validators.required],
      admissionDate: ['', Validators.required],
      dischargeDate: [''],
      status: ['', Validators.required],
      dischargeSummary: ['']
    });
  }

  // Load admission details for editing
  loadAdmissionDetails() {
    if (this.selectedAdmissionId) {
      this.admissionService.getAdmissionById(this.selectedAdmissionId).subscribe({
        next: (admissionData) => {
          this.admissionForm.get('patientId')?.setValue(admissionData.patient?.id);
          this.admissionForm.get('admissionDate')?.setValue(admissionData.admissionDate);
          this.admissionForm.get('dischargeDate')?.setValue(admissionData.dischargeDate);
          this.admissionForm.get('status')?.setValue(admissionData.status);
          this.admissionForm.get('dischargeSummary')?.setValue(admissionData.dischargeSummary);
        },
        error: (err) => console.error('Error loading admission details', err),
      });
    }
  }

  saveOrUpdateAdmission() {
    if (this.admissionForm.invalid) {
      this.admissionForm.markAllAsTouched();
      return;
    }

    const admissionData = {
      patient: {
        id: this.admissionForm.get('patientId')?.value,
      },
      admissionDate: this.admissionForm.get('admissionDate')?.value,
      dischargeDate: this.admissionForm.get('dischargeDate')?.value || null,
      status: this.admissionForm.get('status')?.value,
      dischargeSummary: this.admissionForm.get('dischargeSummary')?.value || null,
    };

    if (this.isEditMode && this.selectedAdmissionId) {
      // Update admission
      this.admissionService.discharge(this.admissionForm.get('patientId')?.value, admissionData).subscribe({
        next: () => {
          Swal.fire({
            title: 'Updated!',
            text: 'Admission details updated successfully.',
            icon: 'success',
            confirmButtonText: 'OK'
          }).then(() => {
            this.router.navigate(['/admissions/list']);
          });
        },
        error: (err) => {
          Swal.fire({
            title: 'Error!',
            text: 'There was an issue updating the admission.',
            icon: 'error',
            confirmButtonText: 'OK'
          });
          console.error('Error updating admission', err);
        },
      });
    } else {
      // Create new admission
      this.admissionService.addAdmission(admissionData).subscribe({
        next: () => {
          Swal.fire({
            title: 'Created!',
            text: 'Admission details created successfully.',
            icon: 'success',
            confirmButtonText: 'OK'
          }).then(() => {
            this.router.navigate(['/admissions/list']);
          });
        },
        error: (err) => {
          Swal.fire({
            title: 'Error!',
            text: 'There was an issue creating the admission.',
            icon: 'error',
            confirmButtonText: 'OK'
          });
          console.error('Error creating admission', err);
        },
      });
    }
  }

  // Reset form
  resetForm() {
    this.admissionForm.reset();
    this.isEditMode = false;
    this.selectedAdmissionId = null;
  }
}
