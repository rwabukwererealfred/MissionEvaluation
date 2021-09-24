import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MissionReportFormComponent } from './mission-report-form.component';

describe('MissionReportFormComponent', () => {
  let component: MissionReportFormComponent;
  let fixture: ComponentFixture<MissionReportFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MissionReportFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MissionReportFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
