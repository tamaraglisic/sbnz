import { NgModule } from '@angular/core';
import { MaterialModule } from '../material-module';
import { ConfirmationComponent } from './confirmation/confirmation.component';

@NgModule({
  declarations: [ConfirmationComponent],
  imports: [ MaterialModule],
  exports: [ConfirmationComponent],
  providers: []
})
export class SharedModule { }
