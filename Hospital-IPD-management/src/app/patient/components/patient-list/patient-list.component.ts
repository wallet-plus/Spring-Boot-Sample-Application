import { Component } from '@angular/core';
import { PatientService } from 'src/app/services/patient.service';
import Swal from 'sweetalert2';
import { MedicineDialogComponent } from '../../dialogs/medicine-dialog/medicine-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.scss']
})
export class PatientListComponent {
  patientList: any[] = []; 

  constructor(private patientService: PatientService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.getStaff();
  }

  getStaff(): void {
    this.patientService.getPatientsList().subscribe(
      (data) => {
        this.patientList = data; // Assign the employee data
      },
      (error) => {
        console.error('Error fetching employee list:', error);
      }
    );
  }


  editEmployee(id: number): void {
    console.log('Edit employee with ID:', id);
    // Navigate to edit page or open a modal for editing
  }
  
  delete(staff: any) {
    Swal.fire({
      title: 'Are you sure?',
      text: `You are about to delete ${staff.firstName} ${staff.lastName}. This action cannot be undone.`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, keep it',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        // Proceed with deletion if confirmed
        this.patientService.deletePatients(staff.id).subscribe({
          next: () => {
            Swal.fire('Deleted!', `${staff.firstName} ${staff.lastName} has been deleted.`, 'success');
            // Optionally refresh the list or remove the staff from the UI
          },
          error: (err) => {
            Swal.fire('Error!', 'There was an issue deleting the staff.', 'error');
            console.error('Error deleting staff', err);
          }
        });
      }
    });
  }

  assignMedicine() {
    const dialogRef = this.dialog.open(MedicineDialogComponent, {
      width: '70%',    // Set the width to 70% of the screen width
      height: '70%',   // Set the height to 80% of the screen height
      data: { name: 'John Doe' }  // Optional data you want to pass to dialog
    });
  
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog closed with result: ', result);
      // You can process the result from the dialog here if needed
    });
  }
  
  
  
}
