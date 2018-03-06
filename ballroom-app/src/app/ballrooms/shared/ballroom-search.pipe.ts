import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'ballroomSearch'
})
export class BallroomSearchPipe implements PipeTransform {

  transform(value: any, args?: any): any {
     // tslint:disable-next-line:prefer-const
     let filter = args[0].toLocaleLowerCase();
     return filter ? value.filter(ballroom => ballroom.ballroomName.toLocaleLowerCase().indexOf(filter) !== -1) : value;
  }

}
