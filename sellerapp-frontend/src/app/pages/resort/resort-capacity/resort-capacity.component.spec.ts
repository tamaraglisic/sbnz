import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResortCapacityComponent } from './resort-capacity.component';

describe('ResortCapacityComponent', () => {
  let component: ResortCapacityComponent;
  let fixture: ComponentFixture<ResortCapacityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResortCapacityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResortCapacityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
