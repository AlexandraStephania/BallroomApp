import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { Observable } from 'rxjs/Observable';

import { BallroomService } from './ballroom.service';
import { Ballroom } from './../models/ballroom';

@Injectable()
export class BallroomResolver implements Resolve<Ballroom> {

    constructor(private ballroomService: BallroomService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Ballroom> {
        // tslint:disable-next-line:prefer-const
        let id = route.params['id'];
        return this.ballroomService.getBallroomById(id);
    }
}
