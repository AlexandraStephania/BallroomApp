import { SearchService } from './services/search-ballroom.service';

import { BallroomsListResolver } from './services/ballrooms-resolver.service';
import { BallroomResolver } from './services/ballroom-resolver.service';
import { SalonService } from './services/salon.service';
import { ContactService } from './services/contact.service';
import { ContactComponent } from './components/contact/contact.component';
import { OfferRequestService } from './services/offerRequest.service';
import { MenuService } from './services/menu.service';
import { BallroomService } from './services/ballroom.service';
import { BallroomsAppComponent } from './ballrooms-app.component';
import { FormsModule,  ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MaterialModule } from './../shared/material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { MainContentComponent } from './components/main-content/main-content.component';
import { Routes, RouterModule } from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';
import { SalonComponent } from './components/salon/salon.component';
import { OfferRequestComponent } from './components/offer-request/offer-request.component';
import { DeleteConfirmDialogComponent } from './components/delete-confirm-dialog/delete-confirm-dialog.component';
import { BallroomDetailsComponent } from './components/ballroom-details/ballroom-details.component';
import { BallroomEditComponent } from './components/ballroom-edit/ballroom-edit.component';
import { CriteriaComponent } from './shared/criteria/criteria.component';



const routes: Routes = [
  {path: '', component: BallroomsAppComponent,
    children: [
      { path: '',
        component: MainContentComponent,
        resolve: { ballroomsList: BallroomsListResolver }
      },
      { path: 'ballrooms',
        component: MainContentComponent,
        resolve: { ballroomsList: BallroomsListResolver }
      },
      {path: 'offer', component:  OfferRequestComponent},
      {path: 'contact', component:  ContactComponent},
      {path: 'menus', component:  MenuComponent},
      {path: 'salons', component:  SalonComponent},
      { path: 'ballroom/:id',
        component: BallroomDetailsComponent,
        resolve: { ballroom: BallroomResolver }
      },
      { path: 'ballroom/:id/edit',
        component: BallroomEditComponent,
        resolve: { ballroom: BallroomResolver }
      },
      {path: 'ballroom/0/edit', component: BallroomEditComponent}


    ] },
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes)
  ],
  providers: [
    BallroomService,
    MenuService,
    OfferRequestService,
    ContactService,
    SalonService,
    BallroomResolver,
    BallroomsListResolver,
    SearchService
  ],
  declarations: [ToolbarComponent,
    MainContentComponent,
    BallroomsAppComponent,
    MenuComponent,
    ContactComponent,
    SalonComponent,
    OfferRequestComponent,
    DeleteConfirmDialogComponent,
    BallroomDetailsComponent,
    BallroomEditComponent,
    CriteriaComponent
  ],
    exports: [
      CriteriaComponent
    ],
    entryComponents: [
      DeleteConfirmDialogComponent
    ]
})
export class BallroomsModule { }
