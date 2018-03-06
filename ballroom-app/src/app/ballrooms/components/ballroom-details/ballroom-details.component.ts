import { Ballroom } from './../../models/ballroom';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ballroom-details',
  templateUrl: './ballroom-details.component.html',
  styleUrls: ['./ballroom-details.component.scss']
})
export class BallroomDetailsComponent implements OnInit {
  // tslint:disable-next-line:no-inferrable-types
  pageTitle: string= 'Ballroom Detail';
  ballroom: Ballroom;
  errorMessage: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
     this.ballroom = this.route.snapshot.data['ballroom'];
  }

}
