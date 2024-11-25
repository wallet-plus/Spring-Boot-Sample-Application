import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StaffComponent } from './staff.component';
import { RouterModule, Routes } from '@angular/router';
import { StaffListComponent } from './components/staff-list/staff-list.component';
import { StaffDetailsComponent } from './components/staff-details/staff-details.component';


const routes: Routes = [
  {
    path: '',
    component: StaffComponent,
    children : [
      {
        path : '',
        redirectTo : 'list',
        pathMatch : 'full'
      },
      {
        path : 'list',
        component : StaffListComponent
      },
      {
        path : '',
        component : StaffDetailsComponent
      }

    ]
  },

];
@NgModule({
  declarations: [
    StaffComponent,
    StaffListComponent,
    StaffDetailsComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)

  ]
})
export class StaffModule { }
