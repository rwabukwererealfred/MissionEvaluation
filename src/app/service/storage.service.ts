import { Injectable } from '@angular/core';


const KEY_TOKEN='auth-token';
const USER_TOKEN='user-token';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }
  public saveToken(token:string){
    window.sessionStorage.removeItem(KEY_TOKEN);
    window.sessionStorage.setItem(KEY_TOKEN,token);
  }
  public saveUser(user:any){
    window.sessionStorage.removeItem(USER_TOKEN);
    window.sessionStorage.setItem(USER_TOKEN, JSON.stringify(user));
  }

  public getToken(){
    return window.sessionStorage.getItem(KEY_TOKEN);
  }
  public getUser(){
    return JSON.parse(window.sessionStorage.getItem(USER_TOKEN)!);
  }
  public signout(){
    window.sessionStorage.clear();
  }
}
