import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-medicine-dialog',
  templateUrl: './medicine-dialog.component.html',
  styleUrls: ['./medicine-dialog.component.scss']
})
export class MedicineDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {
    console.log('Received data: ', this.data);
  }
}
