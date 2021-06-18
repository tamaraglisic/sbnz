import { Component, OnInit, } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label } from 'ng2-charts';
import { Occupancy } from 'src/app/core/model/Occupancy';
import { SkiResort } from 'src/app/core/model/SkiResort';
import { SkiResortService } from 'src/app/core/services/ski-resort/ski-resort.service';
import { DatePipe } from '@angular/common';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Form, FormBuilder, FormGroup } from '@angular/forms';
import { TicketsService } from 'src/app/core/services/tickets/tickets.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-resort-capacity',
  templateUrl: './resort-capacity.component.html',
  styleUrls: ['./resort-capacity.component.scss']
})
export class ResortCapacityComponent implements OnInit {
  resorts: SkiResort[] = [];
  occ: Occupancy = {forDay: '', percent: 0};
  selected: any;
  occupancy: Occupancy[] = [];
  skiResort!: SkiResort;
  pipe = new DatePipe('en-US');

  inputDate!: Date;
  form!: FormGroup;
  role: any;
  lineChartData: ChartDataSets[] = [ { data: [85, 72, 78, 75, 77, 75], label: 'Capacity in %' },];

  lineChartLabels: Label[] = ['Today', 'Today', 'Today', 'Today', 'Today', 'Today'];

  lineChartOptions = {
    scales: {
      yAxes: [{
          display: true,
          ticks: {
            beginAtZero: true,
            steps: 10,
            stepValue: 5,
            max: 100
          }
      }]
  },
    // scales: {
    //     xAxes: [{
    //       type: 'time',
    //     }]
    // },
  responsive: true,
  maintainAspectRatio: false,  
};

  lineChartColors: Color[] = [
  {
    borderColor: 'black',
    backgroundColor: 'rgba(200, 119, 255, 0.3)',
  },
];

  lineChartLegend = true;
  lineChartPlugins = [];
  lineChartType: ChartType = 'line';

  constructor(
    public dialog: MatDialog,
    private ticketsService: TicketsService,
    public datepipe: DatePipe,
    private fb: FormBuilder,
    private skiResortService: SkiResortService
  ) { }

  ngOnInit(): void {
    this.checkRole();
    this.createForm();
    this.skiResortService.getAll().subscribe(
      res => {
        this.resorts = res.body as SkiResort[];
        console.log(this.resorts);
      }
    );

    this.skiResortService.getOccupancy(1).subscribe(
      res => {
        this.occupancy = res.body as Occupancy[];
        console.log(this.occupancy);
        this.lineChartData = [ { data: [this.occupancy[0].percent, this.occupancy[1].percent, this.occupancy[2].percent, this.occupancy[3].percent, this.occupancy[4].percent], label: 'Capacity in %' },];

        this.lineChartLabels= [this.occupancy[0].forDay, this.occupancy[1].forDay, this.occupancy[2].forDay, this.occupancy[3].forDay, this.occupancy[4].forDay];
      }
    );
    
  }
  createForm(): void{
    this.form = this.fb.group({
      inputDate: ['']
      });
  }
  checkRole():void{
    const item = localStorage.getItem('user');
    console.log(item);
	  if (!item) {
		  this.role = undefined;
		  return;
	  }

	  const jwt: JwtHelperService = new JwtHelperService();
	  this.role = jwt.decodeToken(item).role;
    console.log(this.role);
  }

  dateChanged(): void{
    if(this.selected){
      let dateeee = this.datepipe.transform(this.form.value.inputDate, 'dd/MM/yyyy');
      this.occ.forDay = dateeee !==null ? dateeee:"12/10/2020";
      this.occ.percent = 0;
      this.ticketsService.getOccupancy(this.selected, this.occ).subscribe(
        res=>{
          this.occ = res.body as Occupancy;
          alert("Occupancy for day " + this.occ.forDay + " is " + this.occ.percent);
        }
      )
    }
    console.log(this.datepipe.transform(this.form.value.inputDate, 'dd/MM/yyyy'));
  }

  onSelection(event: any): void{
    this.skiResortService.getOccupancy(this.selected).subscribe(
      res => {
        this.occupancy = res.body as Occupancy[];
        console.log(this.occupancy);
        this.lineChartData = [ { data: [this.occupancy[0].percent, this.occupancy[1].percent, this.occupancy[2].percent, this.occupancy[3].percent, this.occupancy[4].percent], label: 'Capacity in %' },];

        this.lineChartLabels= [this.occupancy[0].forDay, this.occupancy[1].forDay, this.occupancy[2].forDay, this.occupancy[3].forDay, this.occupancy[4].forDay];
      }
    );
    }

}
