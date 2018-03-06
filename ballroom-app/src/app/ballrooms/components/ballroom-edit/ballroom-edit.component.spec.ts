import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BallroomEditComponent } from './ballroom-edit.component';

describe('BallroomEditComponent', () => {
  let component: BallroomEditComponent;
  let fixture: ComponentFixture<BallroomEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BallroomEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BallroomEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
