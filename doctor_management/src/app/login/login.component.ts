import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
username: string='';
password: string='';
errorMessage: string='';

constructor(private loginService: LoginService, private router:Router){}

login(){

  this.loginService.loginService1(this.username, this.password).subscribe(
    response =>{
      if(response.role === 'DOCTOR'){
        this.router.navigate(['/doctor-dashbord']);
      }else if (response.role === 'PATIENT'){
        this.router.navigate(['/patient-dashbord']);
      }else{
        this.errorMessage ='unknown role'
      }
    },
    error=>{
      this.errorMessage='Invalid credentials';
    }
  );
}

}
