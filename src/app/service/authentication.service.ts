import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Signup } from '../model/Signup';
import { Observable } from 'rxjs';

const API = 'http://localhost:8080/api/mission/authentication/';
const OPTIONS = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http:HttpClient) { }

  createNewAccount(signUp:Signup){
    return this.http.put(API+"updateUserAcccount", JSON.stringify(signUp), OPTIONS);
  }
  

  login(user:any):Observable<any>{
    return this.http.post( API+ 'signin',{
      username: user.username,
      password : user.password
    },OPTIONS);
  }

}
