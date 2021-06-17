import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from 'src/app/core/services/authentication/authentication.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl!: string;

  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private authenticationService: AuthenticationService,
      private toastr: ToastrService

  ) {
      // redirect to home if already logged in
      if (this.authenticationService.isLoggedIn()) {
          this.router.navigate(['/']);
      }
  }

  ngOnInit() {
      this.loginForm = this.formBuilder.group({
          username: ['', Validators.required],
          password: ['', Validators.required]
      });

      // get return url from route parameters or default to '/'
      this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  onSubmit() {
    this.submitted = true;
    console.log(this.loginForm.value['username']);


      // stop here if form is invalid
    if (this.loginForm.invalid) {
        return;
    }

    this.loading = true;

    const auth: any = {};
    auth.username = this.loginForm.value.username;
    auth.password = this.loginForm.value.password;

    this.authenticationService.login(auth).subscribe(
        result => {
            this.toastr.success('Successful login!');
            localStorage.setItem('user', JSON.stringify(result));
            this.router.navigate(['/ski-resorts']);
        },
        error => {
            console.log(error);
            this.toastr.error('Wrong password or username');
        }
        
    );
      
  }

}
