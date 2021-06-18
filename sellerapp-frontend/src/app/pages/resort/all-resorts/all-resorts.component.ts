import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { JwtHelperService } from '@auth0/angular-jwt';
import { SkiResort } from 'src/app/core/model/SkiResort';
import { SkiResortService } from 'src/app/core/services/ski-resort/ski-resort.service';
import { ConfirmationComponent, ConfirmDialogModel } from '../../shared/confirmation/confirmation.component';
import { EditResortComponent } from '../edit-resort/edit-resort.component';

@Component({
  selector: 'app-all-resorts',
  templateUrl: './all-resorts.component.html',
  styleUrls: ['./all-resorts.component.scss']
})
export class AllResortsComponent implements OnInit {
  resorts: SkiResort[] = [];
  public role!: string| undefined;
  result: any;
  searchForm!: FormGroup;
  
  constructor(
    private skiResortService: SkiResortService,
    public dialog: MatDialog,
    private fb: FormBuilder,
 

  ) { }

  ngOnInit(): void {
    this.createForm()
    this.checkRole()
    this.skiResortService.getAll().subscribe(
      res => {
        this.resorts = res.body as SkiResort[];
       
      });
      
  }

  createForm():void{
    this.searchForm = this.fb.group({
      name: ['']
      });

  }

  search():void{
    this.skiResortService.search(this.searchForm.value.name).subscribe(
      res=>{
        this.resorts = res.body as SkiResort[];
      }
    )
  }
  checkRole(): void {
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

  edit(id: any): void {
    const dialogRef = this.dialog.open(EditResortComponent);
    dialogRef.componentInstance.resId = id;
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      window.location.reload();
    });
  }

  delete(id: any): void{
    const message = `Are you sure you want to delete resort?`
    const dialogData = new ConfirmDialogModel('Confirm Action', message);
    const dialogRef = this.dialog.open(ConfirmationComponent, {
        maxWidth: '400px',
        data: dialogData
    });

    dialogRef.afterClosed().subscribe(dialogResult => {
      this.result = dialogResult;
        if (this.result === true){
          this.skiResortService.delete(id).subscribe(
            result => {
              console.log("DELETED")
              window.location.reload();
            }, error => {
              console.log("Error")
      
            });
          }
      })
  }
}
