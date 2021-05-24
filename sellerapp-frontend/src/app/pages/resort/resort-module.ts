import { NgModule } from '@angular/core';
import { MaterialModule } from '../material-module';
import { ResortChooserComponent } from './resort-chooser/resort-chooser.component';


@NgModule({
  declarations: [ResortChooserComponent],
  imports: [ MaterialModule],
  exports: [ResortChooserComponent],
  providers: []
})

export class ResortModule { }