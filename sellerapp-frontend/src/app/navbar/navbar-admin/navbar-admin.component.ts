import { Component, OnInit, ViewChild } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { MatMenuTrigger } from '@angular/material/menu';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/core/services/authentication/authentication.service';
import { NewResortComponent } from 'src/app/pages/resort/new-resort/new-resort.component';

@Component({
  selector: 'app-navbar-admin',
  templateUrl: './navbar-admin.component.html',
  styleUrls: ['./navbar-admin.component.scss']
})
export class NavbarAdminComponent implements OnInit {


  constructor(public dialog: MatDialog, private router: Router,
              private authenticationService: AuthenticationService) {}

  ngOnInit(): void {
  }
  

  newAdmin(): void{
    
  }
  newInstructor(): void{
    
  }

  newSkiResort(): void{
    console.log("adding new ski resort")
    const dialogRef = this.dialog.open(NewResortComponent);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      window.location.reload();
    });
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
