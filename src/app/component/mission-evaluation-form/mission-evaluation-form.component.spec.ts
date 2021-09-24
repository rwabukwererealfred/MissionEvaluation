import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MissionEvaluationFormComponent } from './mission-evaluation-form.component';

describe('MissionEvaluationFormComponent', () => {
  let component: MissionEvaluationFormComponent;
  let fixture: ComponentFixture<MissionEvaluationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MissionEvaluationFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MissionEvaluationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
