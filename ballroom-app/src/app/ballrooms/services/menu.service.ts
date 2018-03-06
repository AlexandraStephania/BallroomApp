import { Menu } from './../models/menu';
import { Injectable } from '@angular/core';
import { Http, Response, Headers,
         URLSearchParams, RequestOptions } from '@angular/http';

// tslint:disable-next-line:import-blacklist
import { Observable } from 'rxjs';


@Injectable()
export class MenuService {

    // URL for CRUD operations
    menusUrl = 'http://localhost:8080/BallroomsApp/menus';
    menuUrl = 'http://localhost:8080/BallroomsApp/menu';
    addMenuUrl = 'http://localhost:8080/BallroomsApp/menu/add';
    updateMenuUrl = 'http://localhost:8080/BallroomsApp/menu/update';
    deleteMenuUrl = 'http://localhost:8080/BallroomsApp/menu/delete';
    // Create constructor to get Http instance
    constructor(private http: Http) { }

    // Fetch all menus
    getAllMenus():  Observable<Menu[]> {
       return this.http.get(this.menusUrl)
       .map(this.extractData)
       .catch(this.handleError);
    }
       // Create contact
       createMenu(menu: Menu): Observable<number> {
        // tslint:disable-next-line:prefer-const
        let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        // tslint:disable-next-line:prefer-const
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.post(this.addMenuUrl , menu, options)
               .map(success => success.status)
               .catch(this.handleError);
    }

    // Fetch menu by id
    getMenuById(id: string): Observable<Menu> {
        // tslint:disable-next-line:prefer-const
        let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
        // tslint:disable-next-line:prefer-const
        let cpParams = new URLSearchParams();
        cpParams.set('id', id );
        // tslint:disable-next-line:prefer-const
        let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
         return this.http.get(this.menuUrl, options)
          .map(this.extractData)
          .catch(this.handleError);
      }

      // Update menu
      updateMenu(menu: Menu): Observable<number> {
         // tslint:disable-next-line:prefer-const
         let cpHeaders = new Headers({ 'Content-Type': 'application/json' });
          // tslint:disable-next-line:prefer-const
          let options = new RequestOptions({ headers: cpHeaders });
          return this.http.put(this.updateMenuUrl, menu, options)
                 .map(success => success.status)
                 .catch(this.handleError);
      }
      // Delete menu
      deleteMenuById(id: string): Observable<number> {
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
          return this.http.delete(this.deleteMenuUrl, options)
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
