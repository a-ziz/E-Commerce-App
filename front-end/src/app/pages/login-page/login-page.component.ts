import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {

  message = ''
  alertClass = ''

  constructor (private fb: FormBuilder, private authService: AuthService, private router: Router){

  }

  // loginForm = new FormGroup({
  //   email: new FormControl('abc'),
  //   password: new FormControl()
  // })
  
  loginForm = this.fb.group({
    usernameOrEmail: [null, [Validators.required, Validators.email]],
    password: [null, [Validators.required, Validators.minLength(6)]]
  });

  get usernameOrEmail(){
    return this.loginForm.get('usernameOrEmail');
  }

  get password(){
    return this.loginForm.get('password');
  }

  onLoginHandler(){
    // console.log(this.loginForm.value);
    this.authService.login(this.loginForm.value).subscribe((response: any) => {
      this.message = response.message
      this.alertClass = 'alert alert-success'
      localStorage.setItem('token', response.token)

      this.router.navigateByUrl('home')

    }, (error) => {
      this.message = 'login failed, please try again';
      this.alertClass = 'alert alert-danger';
    })
  }
}
