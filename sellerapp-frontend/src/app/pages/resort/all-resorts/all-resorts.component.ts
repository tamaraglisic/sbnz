import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { SkiResort } from 'src/app/core/model/SkiResort';
import { SkiResortService } from 'src/app/core/services/ski-resort/ski-resort.service';
import { EditResortComponent } from '../edit-resort/edit-resort.component';

@Component({
  selector: 'app-all-resorts',
  templateUrl: './all-resorts.component.html',
  styleUrls: ['./all-resorts.component.scss']
})
export class AllResortsComponent implements OnInit {
  resorts: SkiResort[] = [];
  
  constructor(
    private skiResortService: SkiResortService,
    public dialog: MatDialog,
 

  ) { }

  ngOnInit(): void {
    this.skiResortService.getAll().subscribe(
      res => {
        this.resorts = res.body as SkiResort[];
       
      });
      
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
    console.log("delete -> " + id);
    this.skiResortService.delete(id).subscribe(
      result => {
        console.log("DELETED")
        window.location.reload();
      }, error => {
        console.log("Error")

      }
    );
  }

}
