import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewResortComponent } from './new-resort.component';

describe('NewResortComponent', () => {
  let component: NewResortComponent;
  let fixture: ComponentFixture<NewResortComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewResortComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewResortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
