import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HrReportFormComponent } from './hr-report-form.component';

describe('HrReportFormComponent', () => {
  let component: HrReportFormComponent;
  let fixture: ComponentFixture<HrReportFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HrReportFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HrReportFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
