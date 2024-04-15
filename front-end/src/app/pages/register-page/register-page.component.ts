import { Component } from '@angular/core';
import { IUser } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent {

  userModel: IUser = {
    firstName: '',
    email: '',
    mobile: '',
    password: '',
    username: ''
  }
  constructor(private authService: AuthService){}

  onSubmitHandler(){
    // console.log(this.userModel);
    this.authService.register(this.userModel).subscribe(
      (response: any) => {
        console.log(response);
      },
      (error: any) => {
        console.log(error);
      }
    );
  }
}
