import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from '../shared/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  route: Router;
  loginService: LoginService;
  username: string = '';

  constructor(_route: Router, _loginService: LoginService) {
    this.route = _route;
    this.loginService = _loginService;
   }

  ngOnInit() {
  }

  loginUser() {
    this.loginService.setUserName(this.username);
    this.route.navigate(['/profile']);
    console.log("User Successfully Logged in...");
  }

  holdUsername(eventVar: any) {
    this.username = eventVar.target.value;
  }
}
