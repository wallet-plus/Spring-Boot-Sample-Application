import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDialogModule } from '@angular/material/dialog';
import {MatTabsModule} from '@angular/material/tabs';
import { MatCardModule } from '@angular/material/card'; 


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatDialogModule,
    MatTabsModule,
    MatCardModule
  ],
  exports:[
    MatTabsModule,
    MatCardModule
  ]
})
export class MaterialModule { }
