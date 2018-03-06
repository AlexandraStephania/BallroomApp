import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BallroomDetailsComponent } from './ballroom-details.component';

describe('BallroomDetailsComponent', () => {
  let component: BallroomDetailsComponent;
  let fixture: ComponentFixture<BallroomDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BallroomDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BallroomDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
