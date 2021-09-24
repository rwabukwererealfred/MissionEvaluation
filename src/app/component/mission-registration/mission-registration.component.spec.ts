import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MissionRegistrationComponent } from './mission-registration.component';

describe('MissionRegistrationComponent', () => {
  let component: MissionRegistrationComponent;
  let fixture: ComponentFixture<MissionRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MissionRegistrationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MissionRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
