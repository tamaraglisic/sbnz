import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarRegisteredUserComponent } from './navbar-registered-user.component';

describe('NavbarRegisteredUserComponent', () => {
  let component: NavbarRegisteredUserComponent;
  let fixture: ComponentFixture<NavbarRegisteredUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavbarRegisteredUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarRegisteredUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
