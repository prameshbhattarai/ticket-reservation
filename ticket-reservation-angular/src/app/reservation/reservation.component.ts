import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../service';
import {HttpClient} from '@angular/common/http';

@Component({
  templateUrl: './reservation.component.html',
})
export class ReservationComponent implements OnInit {
  reservationForm: FormGroup;
  loading = false;
  submitted = false;
  error: string;
  success: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private http: HttpClient
  ) {
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.reservationForm.controls;
  }

  ngOnInit() {
    this.reservationForm = this.formBuilder.group({
      reservationFrom: ['', Validators.required],
      reservationTo: ['', Validators.required],
      amount: ['', Validators.required],
    });
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.reservationForm.invalid) {
      return;
    }

    this.loading = true;
    return this.http.post<any>(`http://localhost:8080/reservation`, this.reservationForm.value,
    ).subscribe(() => {
      this.success = 'Reservation made successfully.';
      this.loading = false;
    }, (error) => {
      this.error = 'Unable to submit reservation.';
      this.loading = false;
      console.error(error);
    });
  }
}
