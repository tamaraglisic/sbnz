import { NgModule } from '@angular/core';
import { MaterialModule } from '../material-module';
import { MyReservationsComponent } from './my-reservations/my-reservations.component';
import { NewReservationComponent } from './new-reservation/new-reservation.component';


@NgModule({
  declarations: [NewReservationComponent, MyReservationsComponent],
  imports: [ MaterialModule],
  exports: [NewReservationComponent, MyReservationsComponent],
  providers: []
})

export class ReservationsModule { }