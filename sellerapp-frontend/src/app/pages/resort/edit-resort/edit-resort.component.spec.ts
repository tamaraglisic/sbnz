import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditResortComponent } from './edit-resort.component';

describe('EditResortComponent', () => {
  let component: EditResortComponent;
  let fixture: ComponentFixture<EditResortComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditResortComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditResortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
