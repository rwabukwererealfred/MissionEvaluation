import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMissionReportComponent } from './view-mission-report.component';

describe('ViewMissionReportComponent', () => {
  let component: ViewMissionReportComponent;
  let fixture: ComponentFixture<ViewMissionReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewMissionReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewMissionReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
