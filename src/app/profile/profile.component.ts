import { Component, OnInit } from '@angular/core';

import { LoginService } from '../shared/services/login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  userDetails: string = '';
  loginService: LoginService;

  constructor( _loginService: LoginService) {
    this.loginService = _loginService;
   }

  ngOnInit() {
    console.log(this.loginService.getUserName());
    this.userDetails = this.loginService.getUserName();
  }

}
