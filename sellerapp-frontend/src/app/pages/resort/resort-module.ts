import { NgModule } from '@angular/core';
import { MaterialModule } from '../material-module';
import { ResortCapacityComponent } from './resort-capacity/resort-capacity.component';
import { ResortChooserComponent } from './resort-chooser/resort-chooser.component';
import { ChartsModule } from 'ng2-charts';


@NgModule({
  declarations: [ResortChooserComponent, ResortCapacityComponent],
  imports: [ MaterialModule, ChartsModule],
  exports: [ResortChooserComponent, ResortCapacityComponent],
  providers: []
})

export class ResortModule { }