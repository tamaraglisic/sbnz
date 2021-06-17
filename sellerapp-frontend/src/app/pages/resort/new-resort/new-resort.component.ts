import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { SkiResort } from 'src/app/core/model/SkiResort';
import { SkiResortService } from 'src/app/core/services/ski-resort/ski-resort.service';

@Component({
  selector: 'app-new-resort',
  templateUrl: './new-resort.component.html',
  styleUrls: ['./new-resort.component.scss']
})
export class NewResortComponent implements OnInit {

  form!: FormGroup;
  resort: SkiResort = {name: '', seasonStarts: new Date(), seasonEnds: new Date()};
  loading = false;

  constructor(
    private fb: FormBuilder,
    private skiResortService: SkiResortService,
    private toastr: ToastrService,
    public dialogRef: MatDialogRef<NewResortComponent>
  ) {
    this.createForm();
  }

  ngOnInit(): void {
    
  }
  createForm(): void{
    this.form = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      liftPrice: ['', Validators.required],
      country:['', Validators.required],
      gondolaPrice: ['', Validators.required],
      seasonStarts: ['', Validators.required],
      seasonEnds: ['' , Validators.required],
      groupCount: ['', Validators.required],
      ticketDeposit: ['', Validators.required],
      capacity: ['', Validators.required]
      });
  }
  saveChanges(): void{
    this.resort.name = this.form.value.name;
    this.resort.description = this.form.value.description;
    this.resort.liftPrice = this.form.value.liftPrice;
    this.resort.country = this.form.value.country;
    this.resort.gondolaPrice = this.form.value.gondolaPrice;
    this.resort.seasonStarts = this.form.value.seasonStarts;
    this.resort.seasonEnds = this.form.value.seasonEnds;
    this.resort.groupCount = this.form.value.groupCount;
    this.resort.ticketDeposit = this.form.value.ticketDeposit;
    this.resort.capacity = this.form.value.capacity;
    console.log(this.resort);
    this.skiResortService.addNew(this.resort).subscribe(
      res => {
        this.loading = false;
        this.toastr.success('Ski resort added!');
        this.form.reset();
        this.dialogRef.close();
      }
    )
  }
  cancel(): void{
    this.dialogRef.close();
  }

}
