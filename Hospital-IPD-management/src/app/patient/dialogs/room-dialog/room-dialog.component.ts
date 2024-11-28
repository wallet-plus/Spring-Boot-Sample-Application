import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { MedicineService } from 'src/app/services/medicine.service';
import { PatientService } from 'src/app/services/patient.service';
import { RoomService } from 'src/app/services/room.service';

@Component({
  selector: 'app-room-dialog',
  templateUrl: './room-dialog.component.html',
  styleUrls: ['./room-dialog.component.scss'],
})
export class RoomDialogComponent implements OnInit {
  roomForm!: FormGroup;
  patientList: any[] = [];
  roomList: any[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private medicineService: MedicineService,
    private dialogRef: MatDialogRef<RoomDialogComponent>,
    private patientService: PatientService, 
    private roomService: RoomService
  ) {}

  ngOnInit(): void {
    this.initializeForm();
    this.loadPatients();
    this.loadRooms();
  }

  initializeForm(): void {
    this.roomForm = this.fb.group({
      roomId: [null, Validators.required],
      startDate: [null, Validators.required],
      endDate: [null],
      patientId: [null, Validators.required],
    });
  }

  loadPatients(): void {
    this.patientService.getPatientsList().subscribe(
      (patients : any) => {
        this.patientList = patients;
      },
      (error) => {
        console.error('Error fetching patients:', error);
      }
    );
  }

  loadRooms(): void {
    this.roomService.getRoomList().subscribe(
      (rooms) => {
        this.roomList = rooms;
      },
      (error) => {
        console.error('Error fetching rooms:', error);
      }
    );
  }

  onSubmit(): void {
    if (this.roomForm.valid) {
      const formData = this.roomForm.value;
      console.log('Form data:', formData);

      // this.roomService.assignRoom(formData).subscribe(
      //   (response) => {
      //     Swal.fire('Success!', 'Room assigned successfully.', 'success').then(() =>
      //       this.dialogRef.close()
      //     );
      //   },
      //   (error) => {
      //     Swal.fire('Error!', 'Failed to assign room.', 'error');
      //   }
      // );
    }
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
