import { Component } from '@angular/core';
import { StaffService } from 'src/app/services/staff.service';

@Component({
  selector: 'app-staff-list',
  templateUrl: './staff-list.component.html',
  styleUrls: ['./staff-list.component.scss']
})
export class StaffListComponent {
  staffList: any[] = []; 

  constructor(private staffService: StaffService) {}

  ngOnInit(): void {
    this.getStaff();
  }

  getStaff(): void {
    this.staffService.getStaffList().subscribe(
      (data) => {
        this.staffList = data; // Assign the employee data
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
  
  deleteStaff(id: number): void {
    this.staffService.deleteStaff(id).subscribe(() => {
      console.log('Employee deleted successfully');
      this.getStaff(); // Refresh the list after deletion
    });
  }
}
