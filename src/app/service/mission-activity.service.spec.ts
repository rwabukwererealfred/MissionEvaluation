import { TestBed } from '@angular/core/testing';

import { MissionActivityService } from './mission-activity.service';

describe('MissionActivityService', () => {
  let service: MissionActivityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MissionActivityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
