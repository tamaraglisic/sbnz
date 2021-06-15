import { Component, OnInit, } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label } from 'ng2-charts';
import { Occupancy } from 'src/app/core/model/Occupancy';
import { SkiResort } from 'src/app/core/model/SkiResort';
import { SkiResortService } from 'src/app/core/services/ski-resort/ski-resort.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-resort-capacity',
  templateUrl: './resort-capacity.component.html',
  styleUrls: ['./resort-capacity.component.scss']
})
export class ResortCapacityComponent implements OnInit {
  resorts: SkiResort[] = [];
  selected: any;
  occupancy: Occupancy[] = [];
  skiResort!: SkiResort;
  pipe = new DatePipe('en-US');
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
    
    private skiResortService: SkiResortService
  ) { }

  ngOnInit(): void {
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
