import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing/app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Interceptor } from './interceptors/intercept.service';
import { AuthModule } from './pages/auth/auth.module';
import { MaterialModule } from './pages/material-module';
import { ResortChooserComponent } from './pages/resort/resort-chooser/resort-chooser.component';
import { NavbarAdminComponent } from './navbar/navbar-admin/navbar-admin.component';
import { NavbarRegisteredUserComponent } from './navbar/navbar-registered-user/navbar-registered-user.component';
import { NavbarInstructorComponent } from './navbar/navbar-instructor/navbar-instructor.component';
import { ResortModule } from './pages/resort/resort-module';
import { NewReservationComponent } from './pages/reservations/new-reservation/new-reservation.component';
import { ReservationsModule } from './pages/reservations/reservations-module';

@NgModule({
  declarations: [
    AppComponent,
    NavbarAdminComponent,
    NavbarRegisteredUserComponent,
    NavbarInstructorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    //AuthModule,
    ResortModule,
    ReservationsModule,
    MaterialModule,
    HttpClientModule,
  ],
  providers: [ {provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
