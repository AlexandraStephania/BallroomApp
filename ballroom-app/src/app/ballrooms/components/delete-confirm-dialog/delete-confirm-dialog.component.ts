import { MainContentComponent } from './../main-content/main-content.component';
import { BallroomService } from './../../services/ballroom.service';
import { MatDialogRef } from '@angular/material';
import { Ballroom } from './../../models/ballroom';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-delete-confirm-dialog',
  templateUrl: './delete-confirm-dialog.component.html',
  styleUrls: ['./delete-confirm-dialog.component.scss']
})
export class DeleteConfirmDialogComponent implements OnInit {
   ballroomComponent: MainContentComponent;
   ballroom: Ballroom;
  constructor( private dialogRef:  MatDialogRef<DeleteConfirmDialogComponent>) { }

  ngOnInit() {
    this.ballroom = new Ballroom('', ' ', '');
  }

  delete() {
    this.ballroomComponent.deleteBallroom(this.ballroom.id);
    this.dialogRef.close(this.ballroomComponent);

  }
  dismiss() {
    this.dialogRef.close(null);
  }
}
