import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeMissionAccountComponent } from './employee-mission-account.component';

describe('EmployeeMissionAccountComponent', () => {
  let component: EmployeeMissionAccountComponent;
  let fixture: ComponentFixture<EmployeeMissionAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeMissionAccountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeMissionAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
