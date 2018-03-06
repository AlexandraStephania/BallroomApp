import { OfferRequests } from './../models/offerRequests';
import { Injectable } from '@angular/core';
import { Http, Response, Headers,
         URLSearchParams, RequestOptions } from '@angular/http';

// tslint:disable-next-line:import-blacklist
import { Observable } from 'rxjs';

@Injectable()
export class OfferRequestService {
    // URL for CRUD operations
    offerRequestsUrl = 'http://localhost:8080/BallroomsApp/offerRequests';

        // Create constructor to get Http instance
        constructor(private http: Http) { }

        // Fetch all salons
        getAllOfferRequests():  Observable<OfferRequests[]> {
           return this.http.get(this.offerRequestsUrl)
           .map(this.extractData)
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

}
