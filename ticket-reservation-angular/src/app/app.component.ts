import { Component } from '@angular/core';
import {AuthenticationService} from './service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentUser: any;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) {

    if (this.authenticationService.currentUser) {
      this.authenticationService.currentUser.subscribe(user => this.currentUser = user);
    }
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }
}
