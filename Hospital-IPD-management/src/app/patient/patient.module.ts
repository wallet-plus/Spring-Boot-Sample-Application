import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PatientListComponent } from './components/patient-list/patient-list.component';
import { PatientDetailsComponent } from './components/patient-details/patient-details.component';
import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material.module';
import { MedicineDialogComponent } from './dialogs/medicine-dialog/medicine-dialog.component';

const routes: Routes = [
  {
    path: '',
    // component: RoomsComponent,
    children : [
      {
        path : '',
        redirectTo : 'list',
        pathMatch : 'full'
      },
      {
        path : 'list',
        component : PatientListComponent
      },
      {
        path : 'details',
        component : PatientDetailsComponent
      },
      {
        path : 'details/:id',
        component : PatientDetailsComponent
      }

    ]
  },

];

@NgModule({
  declarations: [
    PatientListComponent,
    PatientDetailsComponent,
    MedicineDialogComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    ReactiveFormsModule,
    MaterialModule
  ]
})
export class PatientModule { }
