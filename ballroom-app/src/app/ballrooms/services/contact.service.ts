import { Contact } from './../models/contact';
import { Injectable } from '@angular/core';
import { Http, Response, Headers,
         URLSearchParams, RequestOptions } from '@angular/http';

// tslint:disable-next-line:import-blacklist
import { Observable } from 'rxjs';

@Injectable()
export class ContactService {
    // URL for CRUD operations
    contactUrl = 'http://localhost:8080/BallroomsApp/contact';
    contactsUrl = 'http://localhost:8080/BallroomsApp/contacts';
    addContactUrl = 'http://localhost:8080/BallroomsApp/contact/add';
    updateContactUrl = 'http://localhost:8080/BallroomsApp/contact/update';
    deleteContactUrl= 'http://localhost:8080/BallroomsApp/contact/delete';
        // Create constructor to get Http instance
        constructor(private http: Http) { }

        // Fetch all salons
        getAllContacts():  Observable<Contact[]> {
           return this.http.get(this.contactsUrl)
           .map(this.extractData)
           .catch(this.handleError);
        }


        // Create contact
      createContact(contact: Contact): Observable<number> {
        // tslint:disable-next-line:prefer-const
        let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        // tslint:disable-next-line:prefer-const
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.post(this.addContactUrl , contact, options)
               .map(success => success.status)
               .catch(this.handleError);
    }

     // Fetch contact by id
     getContactById(id: string): Observable<Contact> {
        // tslint:disable-next-line:prefer-const
        let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        // tslint:disable-next-line:prefer-const
        let cpParams = new URLSearchParams();
        cpParams.set('id', id );
        // tslint:disable-next-line:prefer-const
        let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
         return this.http.get(this.contactUrl, options)
          .map(this.extractData)
          .catch(this.handleError);
      }

      // Update contact
      updateContact(contact: Contact): Observable<number> {
         // tslint:disable-next-line:prefer-const
         let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
          // tslint:disable-next-line:prefer-const
          let options = new RequestOptions({ headers: cpHeaders });
          return this.http.put(this.updateContactUrl, contact, options)
                 .map(success => success.status)
                 .catch(this.handleError);
      }
      // Delete contact
      deleteContactById(id: string): Observable<number> {
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
          return this.http.delete(this.deleteContactUrl, options)
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
