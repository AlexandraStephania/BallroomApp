import { SearchService } from './../../services/search-ballroom.service';
// tslint:disable-next-line:import-blacklist
import { Subject } from 'rxjs';
import { Ballroom } from './../../models/ballroom';

import { MatDialog } from '@angular/material';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent implements OnInit {
  results: Ballroom [];
  searchTerm$ = new Subject<string>();
  constructor(  private searchService: SearchService) {

    this.searchService.search(this.searchTerm$)
    .subscribe( data => this.results =  data);
   }

  ngOnInit() {
  }

}
