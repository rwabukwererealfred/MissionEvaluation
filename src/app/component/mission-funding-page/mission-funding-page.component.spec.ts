import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MissionFundingPageComponent } from './mission-funding-page.component';

describe('MissionFundingPageComponent', () => {
  let component: MissionFundingPageComponent;
  let fixture: ComponentFixture<MissionFundingPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MissionFundingPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MissionFundingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
