import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { StaffService } from 'src/app/services/staff.service';

@Component({
  selector: 'app-staff-details',
  templateUrl: './staff-details.component.html',
  styleUrls: ['./staff-details.component.scss']
})
export class StaffDetailsComponent implements OnInit {
  staffForm!: FormGroup;
  isEditMode = false;
  selectedStaffId: number | null = null;

  constructor(private fb: FormBuilder,
    private route: ActivatedRoute, 
    private staffService: StaffService) {}

  ngOnInit(): void {
    this.initForm();

    // Check if an 'id' exists in the route parameters
    const staffId = this.route.snapshot.paramMap.get('id');
    if (staffId) {
      this.selectedStaffId = +staffId; // convert to number
      this.isEditMode = true;
      this.loadStaffDetails();
    }
  }

  // Load staff details for editing
  loadStaffDetails() {
    if (this.selectedStaffId) {
      this.staffService.getStaffById(this.selectedStaffId).subscribe({
        next: (staffData) => {
          this.staffForm.patchValue(staffData); // Populate form with staff data
        },
        error: (err) => console.error('Error loading staff details', err),
      });
    }
  }

  // Initialize the form
  initForm() {
    this.staffForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      email: ['', [Validators.required, Validators.email]],
      role: ['', Validators.required],
      mobile: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      address: ['', Validators.required],
    });
  }

  // Save or Update staff details
  saveOrUpdateStaff() {
    if (this.staffForm.invalid) {
      this.staffForm.markAllAsTouched();
      return;
    }

    const staffData: any = this.staffForm.value;

    if (this.isEditMode && this.selectedStaffId) {
      // Update staff
      this.staffService.updateStaff(this.selectedStaffId, staffData).subscribe({
        next: () => {
          alert('Staff updated successfully');
          this.resetForm();
        },
        error: (err) => console.error('Error updating staff', err),
      });
    } else {
      // Create new staff
      this.staffService.addStaff(staffData).subscribe({
        next: () => {
          alert('Staff created successfully');
          this.resetForm();
        },
        error: (err) => console.error('Error creating staff', err),
      });
    }
  }

  // Reset form to initial state
  resetForm() {
    this.staffForm.reset();
    this.isEditMode = false;
    this.selectedStaffId = null;
  }
}