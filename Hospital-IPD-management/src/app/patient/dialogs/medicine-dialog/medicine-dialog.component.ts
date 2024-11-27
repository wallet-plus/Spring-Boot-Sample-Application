import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder } from '@angular/forms';
import { MedicineService } from 'src/app/services/medicine.service';

@Component({
  selector: 'app-medicine-dialog',
  templateUrl: './medicine-dialog.component.html',
  styleUrls: ['./medicine-dialog.component.scss']
})
export class MedicineDialogComponent implements OnInit {
  medicines: { medicineId: number, quantity: number }[] = []; // Array for medicines
  medicineList: any[] = []; // Array to hold the medicine list fetched from the service
  patientId !: number;
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private medicineService: MedicineService // Inject the MedicineService
  ) {}

  ngOnInit(): void {
    console.log('Received data: ', this.data.patient);  // Log the data here
    this.loadMedicines(); // Call the service to load the list of medicines
  }

  // Load the list of medicines from the service
  loadMedicines(): void {
    this.medicineService.getMedicineList().subscribe(
      (data) => {
        this.medicineList = data; // Assign the medicine list to the component
      },
      (error) => {
        console.error('Error fetching medicine list:', error);
      }
    );
  }

  // Handle form submission
  onSubmit(): void {
    const patientId = this.data.patient.id; // Assume the patient ID is passed as part of the dialog data
    const medicinesToAssign = this.medicines.map(medicine => ({
      patient: { id: patientId },
      medicine: { id: medicine.medicineId },
      quantity: medicine.quantity
    }));

    // Call the service method to assign medicines
    this.medicineService.assignMedicines(medicinesToAssign).subscribe(
      (response) => {
        console.log('Medicine assignment successful', response);
      },
      (error) => {
        console.error('Error assigning medicine:', error);
      }
    );
  }

  // Add a new medicine entry
  addMedicine(): void {
    this.medicines.push({ medicineId: 0, quantity: 1 });
  }

  // Remove a medicine entry
  removeMedicine(index: number): void {
    this.medicines.splice(index, 1);
  }

  // Cancel the form and close the dialog
  cancel(): void {
    console.log('Form cancelled');
    // Close the dialog or reset the form as needed
  }
}
