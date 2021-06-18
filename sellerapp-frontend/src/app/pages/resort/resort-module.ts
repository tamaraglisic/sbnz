import { NgModule } from '@angular/core';
import { MaterialModule } from '../material-module';
import { ResortCapacityComponent } from './resort-capacity/resort-capacity.component';
import { ResortChooserComponent } from './resort-chooser/resort-chooser.component';
import { ChartsModule } from 'ng2-charts';
import { AllResortsComponent } from './all-resorts/all-resorts.component';
import { NewResortComponent } from './new-resort/new-resort.component';
import { EditResortComponent } from './edit-resort/edit-resort.component';
import { DatePipe } from '@angular/common';


@NgModule({
  declarations: [ResortChooserComponent, ResortCapacityComponent, AllResortsComponent, EditResortComponent, NewResortComponent],
  imports: [ MaterialModule, ChartsModule],
  exports: [ResortChooserComponent, ResortCapacityComponent, AllResortsComponent, EditResortComponent, NewResortComponent],
  providers: [DatePipe]
})

export class ResortModule { }