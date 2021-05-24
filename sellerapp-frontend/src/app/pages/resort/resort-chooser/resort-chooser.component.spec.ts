import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResortChooserComponent } from './resort-chooser.component';

describe('ResortChooserComponent', () => {
  let component: ResortChooserComponent;
  let fixture: ComponentFixture<ResortChooserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResortChooserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResortChooserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
