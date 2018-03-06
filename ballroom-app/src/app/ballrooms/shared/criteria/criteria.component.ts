import { Component, OnInit, ViewChild, ElementRef, AfterViewInit, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-criteria',
  templateUrl: './criteria.component.html',
  styleUrls: ['./criteria.component.scss']
})
export class CriteriaComponent implements OnInit, AfterViewInit {
   // tslint:disable-next-line:no-inferrable-types
   @Output() valueChange: EventEmitter<string> =
       new EventEmitter<string>();

   @ViewChild('filterElement') filterElementRef: ElementRef;

   private _listFilter: string;
   get listFilter(): string {
       return this._listFilter;
   }
   set listFilter(value: string) {
     this._listFilter = value;
     this.valueChange.emit(value);
   }
   constructor() { }

   ngOnInit() {
   }

  ngAfterViewInit(): void {
    if (this.filterElementRef) {
      this.filterElementRef.nativeElement.focus();
    }
  }

}
