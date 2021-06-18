import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Tickets } from 'src/app/core/model/Tickets';
import { TicketsService } from 'src/app/core/services/tickets/tickets.service';
import { ConfirmationComponent, ConfirmDialogModel } from '../../shared/confirmation/confirmation.component';

@Component({
  selector: 'app-my-reservations',
  templateUrl: './my-reservations.component.html',
  styleUrls: ['./my-reservations.component.scss']
})
export class MyReservationsComponent implements OnInit {

  list: Array<Tickets> = [];
  result: any;

  constructor(
    public dialog: MatDialog,
    private toastr: ToastrService,
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

    const message = `Are you sure you want to cancel reservation?`
    const dialogData = new ConfirmDialogModel('Confirm Action', message);
    const dialogRef = this.dialog.open(ConfirmationComponent, {
        maxWidth: '400px',
        data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      this.result = dialogResult;
        if (this.result === true){
          this.ticketsService.delete(id).subscribe(
            result => {
              this.toastr.success('Reservetaion successfully canceld.');
              window.location.reload();
            }, error => {
              this.toastr.error('Cannot cancel reservation');
      
            }
          );
          }
      })
    
  }
  archive(): void{

  }

  newRes(): void{
    
  }

  compareDates(startDate: any): boolean{
    let currentDate = new Date();

   return startDate-currentDate.getTime() > 172800000;
  }

}
