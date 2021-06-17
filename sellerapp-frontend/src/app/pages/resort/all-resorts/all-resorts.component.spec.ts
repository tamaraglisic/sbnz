import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllResortsComponent } from './all-resorts.component';

describe('AllResortsComponent', () => {
  let component: AllResortsComponent;
  let fixture: ComponentFixture<AllResortsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllResortsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllResortsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
