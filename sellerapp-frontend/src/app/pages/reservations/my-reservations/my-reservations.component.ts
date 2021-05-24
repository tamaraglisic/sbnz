import { Component, OnInit } from '@angular/core';
import { Tickets } from 'src/app/core/model/Tickets';

@Component({
  selector: 'app-my-reservations',
  templateUrl: './my-reservations.component.html',
  styleUrls: ['./my-reservations.component.scss']
})
export class MyReservationsComponent implements OnInit {

  list: Array<Tickets> = [];

  constructor() { }

  ngOnInit(): void {
    let tick: Tickets;
    tick = {'id':1};
    let tick2: Tickets;
    tick2 = {'id':2};
    this.list.push(tick);
    this.list.push(tick2);

  }

  cancelReservation(id:any): void{
    console.log(id);
  }


}
