import { Component, OnInit } from '@angular/core';
import { AdmissionService } from 'src/app/services/admission.service'; // Adjust the path as necessary

@Component({
  selector: 'app-invoice-dialog',
  templateUrl: './invoice-dialog.component.html',
  styleUrls: ['./invoice-dialog.component.scss']
})
export class InvoiceDialogComponent implements OnInit {
  medicineData: any;

  constructor(private admissionService: AdmissionService) {}

  ngOnInit(): void {
    this.fetchInvoiceData();
  }

  fetchInvoiceData(): void {
    const admissionId = 1; // Replace with dynamic ID if needed

    this.admissionService.getMedicinesByAdmission(admissionId).subscribe(
      (response) => {
        this.medicineData = response;
        console.log('Invoice Data:', this.medicineData);
      },
      (error) => {
        console.error('Error fetching invoice data:', error);
      }
    );
  }
}
