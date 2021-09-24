import { Component, OnInit, Inject, ElementRef, Renderer2 } from '@angular/core';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { StorageService } from 'src/app/service/storage.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent implements OnInit {

  public user:any ={};
  public loginFail:boolean= false;
  public loginSuccessFull:boolean=false;
  
  public currentUser:any;
  public message:string='';

  constructor(private storage: StorageService, private route:Router, private authenticationService: AuthenticationService,
     private renderer:Renderer2) { }

  ngOnInit() {
  }
  ngAfterViewInit(){
    const backgroundImageUrl = '../../../assets/dist/img/ima.jpg';

    this.renderer.setStyle(document.body, 'backgroundImage', 'url('+backgroundImageUrl+')');
    this.renderer.setStyle(document.body, 'background-repeat', 'repeat-y');
    this.renderer.setStyle(document.body, 'background-size', 'cover');
    
    }

  login(){
    
    this.authenticationService.login(this.user).subscribe(res=>{
      this.loginFail = false;
      this.loginSuccessFull = true;
      this.storage.saveToken(res.token);
      this.storage.saveUser(res); 
      this.currentUser = this.storage.getUser();
      if(this.currentUser.employee.role.name ==='HR'){
      this.route.navigate(['/employeeRegistration']).then(()=>{
        window.location.reload();
      });
      
      }else{
        this.route.navigate(['/myMissionAccount']).then(()=>{
          window.location.reload();
        });
       
      }
      
      console.log(this.currentUser.username);
    },error=>{
      this.loginFail = true;
      this.loginSuccessFull = false;
      this.message = error.error.message;
    })
  }


}
