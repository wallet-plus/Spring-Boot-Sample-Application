import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MedicineService } from 'src/app/services/medicine.service';
import Swal from 'sweetalert2'; 

@Component({
  selector: 'app-patient-medicines',
  templateUrl: './patient-medicines.component.html',
  styleUrls: ['./patient-medicines.component.scss']
})
export class PatientMedicinesComponent {
  medicineList: any[] = []; 
  selectedPatientId: number | null = null;

  constructor(private medicineService: MedicineService,
    private route: ActivatedRoute,

  ) {}

  ngOnInit(): void {

    const patientId = this.route.snapshot.paramMap.get('id');
debugger;
    if (patientId) {
      this.selectedPatientId = +patientId;
      this.getPatientMedicines();
    }
  }

  getPatientMedicines(): void {
    this.medicineService.getPatientMedicines(this.selectedPatientId).subscribe(
      (data) => {
        this.medicineList = data; // Assign the employee data
      },
      (error) => {
        console.error('Error fetching employee list:', error);
      }
    );
  }


  // editEmployee(id: number): void {
  //   console.log('Edit employee with ID:', id);
  //   // Navigate to edit page or open a modal for editing
  // }
  
  // delete(staff: any) {
  //   Swal.fire({
  //     title: 'Are you sure?',
  //     text: `You are about to delete ${staff.firstName} ${staff.lastName}. This action cannot be undone.`,
  //     icon: 'warning',
  //     showCancelButton: true,
  //     confirmButtonText: 'Yes, delete it!',
  //     cancelButtonText: 'No, keep it',
  //     reverseButtons: true
  //   }).then((result) => {
  //     if (result.isConfirmed) {
  //       // Proceed with deletion if confirmed
  //       this.medicineService.deleteMedicine(staff.id).subscribe({
  //         next: () => {
  //           Swal.fire('Deleted!', `${staff.firstName} ${staff.lastName} has been deleted.`, 'success');
  //           // Optionally refresh the list or remove the staff from the UI
  //         },
  //         error: (err) => {
  //           Swal.fire('Error!', 'There was an issue deleting the staff.', 'error');
  //           console.error('Error deleting staff', err);
  //         }
  //       });
  //     }
  //   });
  // }
}

