import { TestBed } from '@angular/core/testing';

import { NtsMapService } from './nts-map.service';

describe('NtsMapService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NtsMapService = TestBed.get(NtsMapService);
    expect(service).toBeTruthy();
  });
});
