import { Component, OnInit } from '@angular/core';
import { MatIconRegistry } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-ballrooms-app',
  template: `
  <app-toolbar></app-toolbar>
  `,
  styles: []
})
export class BallroomsAppComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
