import { Salon } from './../models/salon';
import { Injectable } from '@angular/core';
import { Http, Response, Headers,
         URLSearchParams, RequestOptions } from '@angular/http';

// tslint:disable-next-line:import-blacklist
import { Observable } from 'rxjs';

@Injectable()
export class SalonService {
    // URL for CRUD operations
    salonsUrl = 'http://localhost:8080/BallroomsApp/salons';
    salonUrl = 'http://localhost:8080/BallroomsApp/salon';
    addSalonUrl = 'http://localhost:8080/BallroomsApp/salon/add';
    updateSalonUrl = 'http://localhost:8080/BallroomsApp/salon/update';
    deleteSalonUrl = 'http://localhost:8080/BallroomsApp/salon/delete';

        // Create constructor to get Http instance
        constructor(private http: Http) { }

        // Fetch all salons
        getAllSalons():  Observable<Salon[]> {
           return this.http.get(this.salonsUrl)
           .map(this.extractData)
           .catch(this.handleError);
        }

         // Create salon
       createSalon(salon: Salon): Observable<number> {
        // tslint:disable-next-line:prefer-const
        let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        // tslint:disable-next-line:prefer-const
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.post(this.addSalonUrl , salon, options)
               .map(success => success.status)
               .catch(this.handleError);
    }

    // Fetch salon by id
    getSalonById(id: string): Observable<Salon> {
        // tslint:disable-next-line:prefer-const
        let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        // tslint:disable-next-line:prefer-const
        let cpParams = new URLSearchParams();
        cpParams.set('id', id );
        // tslint:disable-next-line:prefer-const
        let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
         return this.http.get(this.salonUrl, options)
          .map(this.extractData)
          .catch(this.handleError);
      }

      // Update salon
      updateSalon(salon: Salon): Observable<number> {
         // tslint:disable-next-line:prefer-const
         let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
          // tslint:disable-next-line:prefer-const
          let options = new RequestOptions({ headers: cpHeaders });
          return this.http.put(this.updateSalonUrl, salon, options)
                 .map(success => success.status)
                 .catch(this.handleError);
      }
      // Delete salon
      deleteSalonById(id: string): Observable<number> {
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
          return this.http.delete(this.deleteSalonUrl, options)
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

}
