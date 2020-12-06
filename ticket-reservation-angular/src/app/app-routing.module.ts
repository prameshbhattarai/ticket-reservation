import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from './dashboard';
import {LoginComponent} from './login';
import {ReservationComponent} from './reservation';
import {PaymentComponent} from './payment';
import {AuthGuardService} from './service';

const routes: Routes = [
  {path: 'login', component: LoginComponent},

  {path: '', component: DashboardComponent, canActivate: [AuthGuardService]},
  {path: 'reservation', component: ReservationComponent, canActivate: [AuthGuardService]},
  {path: 'payment', component: PaymentComponent, canActivate: [AuthGuardService]},

  // default redirect to dashboard component
  {path: '', component: DashboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
