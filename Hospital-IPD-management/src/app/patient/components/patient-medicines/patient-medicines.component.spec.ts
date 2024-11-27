import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientMedicinesComponent } from './patient-medicines.component';

describe('PatientMedicinesComponent', () => {
  let component: PatientMedicinesComponent;
  let fixture: ComponentFixture<PatientMedicinesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PatientMedicinesComponent]
    });
    fixture = TestBed.createComponent(PatientMedicinesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
