import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BallroomService } from './../../services/ballroom.service';
import { Ballroom } from './../../models/ballroom';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-ballroom-edit',
  templateUrl: './ballroom-edit.component.html',
  styleUrls: ['./ballroom-edit.component.scss']
})
export class BallroomEditComponent implements OnInit {
  // tslint:disable-next-line:no-inferrable-types
  pageTitle: string= 'Edit Ballroom';
  ballroom: Ballroom;
  errorMessage: string;
  ballroomForm: FormGroup;
  statusCode: Ballroom;
  requestProcessing = false;
  processValidation = false;
  message = false;



  constructor(private ballroomService: BallroomService,
              private route: ActivatedRoute,
              private router: Router,
              public snackBar: MatSnackBar) { }

  ngOnInit() {

    this.route.data.subscribe( data => {
      this.onBallroomRetrieved(data['ballroom']);
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

  getBallroom(id: string) {
    this.ballroomService.getBallroomById(id).subscribe(
      (ballroom: Ballroom) => this.onBallroomRetrieved(ballroom),
      error => this.errorMessage = <any>error);

}

   onBallroomRetrieved(ballroom: Ballroom): void {
       this.ballroom = ballroom;

       if (this.ballroom.id === '0') {
           this.pageTitle = 'Add Ballroom';
       }
       // tslint:disable-next-line:one-line
       else {
         this.pageTitle = `Edit Ballroom : ${this.ballroom.ballroomName}`;
       }
   }

   deleteBallroom(): void {
     if (this.ballroom.id === '0') {

            // Don't delete, it was never saved.

          } else {
               if (confirm(`Really delete the ballroom: ${this.ballroom.ballroomName}?`)) {
                   this.ballroomService.deleteBallroomById(this.ballroom.id)
                       .subscribe(
                           (error: any) => this.errorMessage = <any>error
                       );
               }
           }
       }

       saveBallroom(ballroom: Ballroom): void {
        console.log('you submitted value: ', ballroom);
        this.processValidation = true;
        this.openSnackBar();
        if (this.ballroomForm.invalid) {
             return; // Validation failed, exit from method.
        }
        // Form is valid, now perform create or update
              this.preProcessConfigurations();

        if (ballroom.id === null) {
          // Handle create ballroom
          this.ballroomService.saveBallroom(ballroom)
            .subscribe(successCode => {
                      this.statusCode = successCode;

          },
                errorCode => this.statusCode = errorCode);
        }else {
         // Handle update ballroom
       ballroom.id = this.ballroom.id;
       this.ballroomService.saveBallroom(ballroom)
        .subscribe(successCode => {
            this.statusCode = successCode;
            this.backToCreateBallroom();
      },
            errorCode => this.statusCode = errorCode);
     }
       }


       preProcessConfigurations() {
        this.statusCode = null;
        this.requestProcessing = true;
       }

       openSnackBar() {
         this.snackBar.open('Succesfully saved.', ' ', {
          duration: 5000
         });
       }

       // Go back to create ballroom
       backToCreateBallroom() {
           this.ballroomForm.reset();
           this.processValidation = false;
      }

}
