import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/core/services/authentication/authentication.service';

@Component({
  selector: 'app-navbar-registered-user',
  templateUrl: './navbar-registered-user.component.html',
  styleUrls: ['./navbar-registered-user.component.scss']
})
export class NavbarRegisteredUserComponent implements OnInit {

  constructor(public dialog: MatDialog, private router: Router,
    private authenticationService: AuthenticationService) {}

ngOnInit(): void {
}


myReservations(): void{
  this.router.navigate(['/my-reservations']);
}
newReservation(): void{
  this.router.navigate(['/new-reservation']);
}

profile(): void{

}

signOut(): void{
  this.authenticationService.signOut().subscribe(
    result => {
        localStorage.removeItem('user');
        this.router.navigate(['/login']);
    }
);
}
}
