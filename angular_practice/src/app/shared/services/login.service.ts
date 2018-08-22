import { Injectable } from '@angular/core';

@Injectable()
export class LoginService {

  username: string;

  constructor() { }

  setUserName (name: string) {
    this.username = name;
  }
  getUserName () {
    return this.username || '';
  }
}
