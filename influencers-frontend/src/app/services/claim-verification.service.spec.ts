import { TestBed } from '@angular/core/testing';

import { ClaimVerificationService } from './claim-verification.service';

describe('ClaimVerificationService', () => {
  let service: ClaimVerificationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClaimVerificationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
