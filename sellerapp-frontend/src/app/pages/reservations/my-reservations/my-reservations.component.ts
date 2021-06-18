import { Component, OnInit } from '@angular/core';
import { Tickets } from 'src/app/core/model/Tickets';
import { TicketsService } from 'src/app/core/services/tickets/tickets.service';

@Component({
  selector: 'app-my-reservations',
  templateUrl: './my-reservations.component.html',
  styleUrls: ['./my-reservations.component.scss']
})
export class MyReservationsComponent implements OnInit {

  list: Array<Tickets> = [];

  constructor(
    private ticketsService: TicketsService
  ) { }

  ngOnInit(): void {
    this.ticketsService.myTickets().subscribe(
      res=>{
        this.list = res.body as Array<Tickets>;
      }
    )

  }

  cancelReservation(id:any): void{
    console.log(id);
  }
  archive(): void{

  }

  newRes(): void{
    
  }

  compareDates(startDate: any): boolean{
    let currentDate = new Date();

   return startDate-currentDate.getTime() > 432000000;
  }

}
