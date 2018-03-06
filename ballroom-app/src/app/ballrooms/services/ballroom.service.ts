import { Ballroom } from './../models/ballroom';
import { Injectable } from '@angular/core';
import { Http, Response, Headers,
         URLSearchParams, RequestOptions } from '@angular/http';

// tslint:disable-next-line:import-blacklist
import { Observable } from 'rxjs';


@Injectable()
export class BallroomService {

    // URL for CRUD operations
    ballroomsUrl = 'http://localhost:8080/BallroomsApp/ballrooms';
    ballroomUrl = 'http://localhost:8080/BallroomsApp/ballroom';
    deleteBallroomUrl = 'http://localhost:8080/BallroomsApp/ballroom/delete';
    createBallroomUrl = 'http://localhost:8080/BallroomsApp/ballroom/add';
    updateBallroomUrl = 'http://localhost:8080/BallroomsApp/ballroom/update';
    searchUrl = 'http://localhost:8080/BallroomsApp/ballroom/search';
    // Create constructor to get Http instance
    constructor(private http: Http) { }

    // Fetch all ballrooms
    getAllBallrooms():  Observable<Ballroom[]> {
       return this.http.get(this.ballroomsUrl)
       .map(this.extractData)
       .catch(this.handleError);
    }


        saveBallroom(ballroom: Ballroom): Observable<Ballroom> {
            // tslint:disable-next-line:prefer-const
            let headers = new Headers({ 'Content-Type': 'application/json' });
            // tslint:disable-next-line:prefer-const
            let options = new RequestOptions({ headers: headers });

            if (ballroom.id === '0') {
                return this.createBallroom(ballroom, options);
            }
            return this.updateBallroom(ballroom, options);
        }

      // Create ballroom
      private createBallroom(ballroom: Ballroom, options: RequestOptions): Observable<Ballroom> {
          return this.http.post(this.createBallroomUrl , ballroom, options)
               .map(this.extractData)
               .do( data => console.log('createBallroom:' + JSON.stringify(data)))
               .catch(this.handleError);
    }

    // Fetch ballroom by id
    getBallroomById(id: string): Observable<Ballroom> {

        if (id === '0') {
          return Observable.of(this.initializeBallroom());
        }
      // tslint:disable-next-line:prefer-const
      let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
      // tslint:disable-next-line:prefer-const
      let cpParams = new URLSearchParams();
      cpParams.set('id', id );
      // tslint:disable-next-line:prefer-const
      let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
       return this.http.get(this.ballroomUrl, options)
        .map(this.extractData)
        .catch(this.handleError);
    }


    // Update ballroom
    private updateBallroom(ballroom: Ballroom, options: RequestOptions): Observable<Ballroom> {

        return this.http.put(this.updateBallroomUrl, ballroom, options)
               .map(() => ballroom)
               .do( data => console.log('updateBallroom:' + JSON.stringify(data)))
               .catch(this.handleError);
    }
    // Delete ballroom
    deleteBallroomById(id: string): Observable<number> {
        // tslint:disable-next-line:prefer-const
        let cpHeaders = new Headers;
        cpHeaders.append('Content-Type', 'application/json' );
        cpHeaders.append('Access-Control-Allow-Origin' , '*');
        cpHeaders.append('Access-Control-Allow-Methods', '*');

        // tslint:disable-next-line:prefer-const
        let cpParams = new URLSearchParams();
        cpParams.set('id', id );
        // tslint:disable-next-line:prefer-const
        let options = new RequestOptions({ params: cpParams });
        return this.http.delete(this.deleteBallroomUrl, options)
              .map(success => success.status)
              .catch(this.handleError);
        }
    private extractData(res: Response) {
        // tslint:disable-next-line:prefer-const
        let body = res.json();    // it automatically turn our json body in java object
        return body || [];
    }

    private handleError (error: Response | any) {
        console.error(error.message || error);
         return Observable.throw(error.status);
        }

    initializeBallroom(): Ballroom {
            // Return an initialized object
            return{
             id: '0',
             ballroomName: null,
             description: null
            };

    }
}
