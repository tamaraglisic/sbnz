import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarInstructorComponent } from './navbar-instructor.component';

describe('NavbarInstructorComponent', () => {
  let component: NavbarInstructorComponent;
  let fixture: ComponentFixture<NavbarInstructorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavbarInstructorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarInstructorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
