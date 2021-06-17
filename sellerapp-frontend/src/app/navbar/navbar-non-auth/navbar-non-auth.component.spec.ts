import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarNonAuthComponent } from './navbar-non-auth.component';

describe('NavbarNonAuthComponent', () => {
  let component: NavbarNonAuthComponent;
  let fixture: ComponentFixture<NavbarNonAuthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavbarNonAuthComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarNonAuthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
