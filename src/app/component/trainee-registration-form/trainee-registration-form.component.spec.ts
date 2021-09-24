import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TraineeRegistrationFormComponent } from './trainee-registration-form.component';

describe('TraineeRegistrationFormComponent', () => {
  let component: TraineeRegistrationFormComponent;
  let fixture: ComponentFixture<TraineeRegistrationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TraineeRegistrationFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TraineeRegistrationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
