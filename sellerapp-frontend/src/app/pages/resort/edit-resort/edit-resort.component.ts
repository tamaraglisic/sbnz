import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { SkiResort } from 'src/app/core/model/SkiResort';
import { SkiResortService } from 'src/app/core/services/ski-resort/ski-resort.service';

@Component({
  selector: 'app-edit-resort',
  templateUrl: './edit-resort.component.html',
  styleUrls: ['./edit-resort.component.scss']
})
export class EditResortComponent implements OnInit {
  resId: any;
  form!: FormGroup;
  resort!: SkiResort;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private skiResortService: SkiResortService,
    private toastr: ToastrService,
    public dialogRef: MatDialogRef<EditResortComponent>
  ) {
    this.createForm();
  }

  ngOnInit(): void {
    this.skiResortService.getOne(this.resId).subscribe(
      res => {
        this.resort = res.body as SkiResort;
        this.form = this.fb.group({
          name: [this.resort.name],
          description: [this.resort.description],
          country: [this.resort.country],
          liftPrice: [this.resort.liftPrice],
          gondolaPrice: [this.resort.gondolaPrice],
          seasonStarts: [new Date(this.resort.seasonStarts)],
          seasonEnds: [new Date(this.resort.seasonEnds)],
          groupCount: [this.resort.groupCount],
          ticketDeposit: [this.resort.ticketDeposit],
          capacity: [this.resort.capacity]
           });
      }
    );
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
    this.resort.country = this.form.value.country;
    this.resort.id = this.resId;
    this.resort.liftPrice = this.form.value.liftPrice;
    this.resort.gondolaPrice = this.form.value.gondolaPrice;
    this.resort.seasonStarts = this.form.value.seasonStarts;
    this.resort.seasonEnds = this.form.value.seasonEnds;
    this.resort.groupCount = this.form.value.groupCount;
    this.resort.ticketDeposit = this.form.value.ticketDeposit;
    this.resort.capacity = this.form.value.capacity;
    console.log(this.resort);
    this.skiResortService.edit(this.resort).subscribe(
      res => {
        this.loading = false;
        this.toastr.success('Ski resort information saved!');
        this.form.reset();
        this.dialogRef.close();
      }
    )
  }
  cancel(): void{

    this.dialogRef.close();
  }
}
