import { Component, OnInit, } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label } from 'ng2-charts';

@Component({
  selector: 'app-resort-capacity',
  templateUrl: './resort-capacity.component.html',
  styleUrls: ['./resort-capacity.component.scss']
})
export class ResortCapacityComponent implements OnInit {
  lineChartData: ChartDataSets[] = [ { data: [85, 72, 78, 75, 77, 75], label: 'Capacity in %' },];

  lineChartLabels: Label[] = ['January', 'February', 'March', 'April', 'May', 'June'];

  lineChartOptions = {
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

  constructor() { }

  ngOnInit(): void {
    
  }

}
