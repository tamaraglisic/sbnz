import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { SkiResort } from 'src/app/core/model/SkiResort';
import { Tickets } from 'src/app/core/model/Tickets';
import { SkiResortService } from 'src/app/core/services/ski-resort/ski-resort.service';
import { TicketsService } from 'src/app/core/services/tickets/tickets.service';
import { ConfirmationComponent, ConfirmDialogModel } from '../../shared/confirmation/confirmation.component';
import { TicketUser } from 'src/app/core/model/TicketUser';

@Component({
  selector: 'app-new-reservation',
  templateUrl: './new-reservation.component.html',
  styleUrls: ['./new-reservation.component.scss']
})
export class NewReservationComponent implements OnInit {

  resorts: SkiResort[] = [];
  selected: any;
  skiResort!: SkiResort;
  minDate: Date = new Date();
  maxDate!: Date;
  ticketForm!: FormGroup;
  result: any;
  res: Tickets = {};
  user!: TicketUser;
  bill!: number;

  constructor(
    private fb: FormBuilder,
    private skiResortService: SkiResortService,
    public dialog: MatDialog,
    private ticketsService: TicketsService

  ) { }

  ngOnInit(): void {
    this.createForm();
    this.skiResortService.getAll().subscribe(
      res => {
        this.resorts = res.body as SkiResort[];
       
      });
      
  }

  createForm(): void {
    this.ticketForm = this.fb.group({
      usingStart: [''],
      usingEnd: [''],
      adult:[''],
      child: [''],
      senior: [''],
      usingPeriod:[''],
      transportType:[''],
      loyalty:[''],
      student: ['']
 });
  }

  onSelection(event: any): void{
    this.skiResort = this.selected;
    this.minDate = new Date(this.skiResort.seasonStarts);
    // uporedi sa danasnjim datumom
    this.maxDate = new Date(this.skiResort.seasonEnds);
  }

  makeReservation(): void{
    console.log("Make reservation");
    // izracunaj cenu rezervisanih karata
    this.res.skiResort = this.skiResort;
    this.res.usingPeriod = this.ticketForm.get('usingPeriod')?.value;
    this.res.usingStart = this.ticketForm.get('usingStart')?.value;
    this.res.usingEnd = this.ticketForm.get('usingEnd')?.value;
    this.res.transportType = this.ticketForm.get('transportType')?.value;

    this.res.ticketUsers = [];
    this.user = {id: 1, count: this.ticketForm.get('adult')?.value, userType: "ODRASLI", singleTicketPrice: this.skiResort.liftPrice};
    this.res.ticketUsers.push(this.user);
    this.user = {id: 2, count: this.ticketForm.get('child')?.value, userType: "DECA", singleTicketPrice: this.skiResort.liftPrice};
    this.res.ticketUsers.push(this.user);
    this.user = {id: 3, count: this.ticketForm.get('senior')?.value, userType: "SENIOR", singleTicketPrice: this.skiResort.liftPrice};
    this.res.ticketUsers.push(this.user);

    // setuj privilegije!!!!!!!!

    this.ticketsService.finalPrice(this.res).subscribe(
       res => {
         this.res = res.body as Tickets;
         console.log(res.body);
    });
    const message = `Your bill is ` + this.res.bill;
    const dialogData = new ConfirmDialogModel('Confirm Reservation', message);
    const dialogRef = this.dialog.open(ConfirmationComponent, {
      maxWidth: '400px',
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      this.result = dialogResult;
      if (this.result === true){
        console.log("Confirm");
        // sacuvaj rezervaciju
        this.ticketsService.add(this.res).subscribe(
          res =>{
            console.log("Reservation saved");
          }
        )
      }
    });
  }

  confirmDialog(catId: number): void {
    
  }

}
