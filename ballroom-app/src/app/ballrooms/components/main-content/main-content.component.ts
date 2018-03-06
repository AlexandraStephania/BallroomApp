import { SearchService } from './../../services/search-ballroom.service';
import { Ballroom } from './../../models/ballroom';
import { ActivatedRoute } from '@angular/router';
import { DeleteConfirmDialogComponent } from './../delete-confirm-dialog/delete-confirm-dialog.component';
import { MenuService } from './../../services/menu.service';
import { Menu } from './../../models/menu';
import { BallroomService } from './../../services/ballroom.service';
import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators, NgForm } from '@angular/forms';
import { MatPaginator, MatDialog, MatTableDataSource } from '@angular/material';
// tslint:disable-next-line:import-blacklist
import { Subject } from 'rxjs';

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent implements OnInit, AfterViewInit {

  allBallrooms: Ballroom [];
  statusCode: number;
  ballroom: Ballroom = new Ballroom('', '', '');
  requestProcessing = false;
  ballroomIdToUpdate = null;
  processValidation = false;
  ballroomForm: FormGroup;
  confirmDeleteDialog: DeleteConfirmDialogComponent;
  displayedColumns = ['Id', 'Name', 'Contact', 'Edit', 'Delete'];
  dataSource = new MatTableDataSource<Ballroom>();



    @ViewChild(MatPaginator) paginator: MatPaginator;

    ngAfterViewInit() {
      this.dataSource.paginator = this.paginator;
    }


  // Create constructor to get service instance
  constructor(private ballroomService: BallroomService,
             private dialog: MatDialog,
             private route: ActivatedRoute) { }

  // loading the ballrooms
  ngOnInit(): void {
   // this.getAllBallrooms();
   this.route.data.subscribe( data => {
       this.allBallrooms = data['ballroomsList'];
       this.dataSource = new MatTableDataSource<Ballroom>(this.allBallrooms);
    });
    this.ballroomForm = new FormGroup ({

            ballroomName: new FormControl ( '', [
            Validators.required,
            Validators.minLength(3)
          ]),
           description: new FormControl ( '', [
            Validators.required,
            Validators.minLength(5)
           ] )

        });
  }

  // Fetch all ballrooms
  getAllBallrooms() {
    this.ballroomService.getAllBallrooms()
    .subscribe(
      data => this.allBallrooms =  data,
      errorCode => this.statusCode = errorCode);

  }




  openDeleteDialog(id: string) {
    this.dialog.open(DeleteConfirmDialogComponent, {
      width: '450px',
      data: {id}
    });

  }

  // Delete ballroom
 deleteBallroom(id: string) {
   if (window.confirm('Are sure you want to delete this item ?')) {
       this.preProcessConfigurations();
    this.ballroomService.deleteBallroomById(id)
      .subscribe(successCode => {
        this.statusCode = successCode;
        this.getAllBallrooms();
        this.backToCreateBallroom();
     },
     errorCode => this.statusCode = errorCode);
    }
 }
  // Perform preliminary processing configurations
  preProcessConfigurations() {
    this.statusCode = null;
this.requestProcessing = true;
}
// Go back from update to create
backToCreateBallroom() {
    this.ballroomIdToUpdate = null;
    this.ballroomForm.reset();
this.processValidation = false;
}


}
