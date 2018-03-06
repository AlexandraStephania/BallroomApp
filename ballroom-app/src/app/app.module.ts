import { ToolbarComponent } from './ballrooms/components/toolbar/toolbar.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule } from '@angular/forms';
import { MaterialModule } from './shared/material.module';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
// tslint:disable-next-line:import-blacklist
import 'rxjs/Rx';
import { AppComponent } from './app.component';
import { BallroomsModule } from './ballrooms/ballrooms.module';



const routes: Routes = [
   {path: 'ballrooms', loadChildren: './ballrooms/ballrooms.module#BallroomsModule'},
   {path: '**', redirectTo: 'ballrooms'}
   ];

@NgModule({

  imports: [
    BrowserModule,
    HttpModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes, { enableTracing: true}),
    MaterialModule,
    FlexLayoutModule,
    FormsModule,
    BallroomsModule


  ],
  declarations: [
    AppComponent

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
