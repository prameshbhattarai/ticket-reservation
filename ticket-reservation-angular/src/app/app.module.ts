import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
// import { APP_BASE_HREF } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { DashboardComponent } from './dashboard'
import { LoginComponent } from './login'
import { ReservationComponent } from './reservation'
import { PaymentComponent } from './payment'

import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent,
    ReservationComponent,
    PaymentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [
    // {
    //   provide: APP_BASE_HREF,
    //   useValue: '/' + (window.location.pathname.split('/')[1] || '') + ':8080'
    // }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
