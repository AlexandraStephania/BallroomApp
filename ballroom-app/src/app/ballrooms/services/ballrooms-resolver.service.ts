import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { Observable } from 'rxjs/Observable';

import { BallroomService } from './ballroom.service';
import { Ballroom } from './../models/ballroom';

@Injectable()
export class BallroomsListResolver implements Resolve<any> {

    constructor(private ballroomService: BallroomService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Ballroom[]> {
        return this.ballroomService.getAllBallrooms();
    }
}
