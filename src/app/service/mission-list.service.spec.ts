import { TestBed } from '@angular/core/testing';

import { MissionListService } from './mission-list.service';

describe('MissionListService', () => {
  let service: MissionListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MissionListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
