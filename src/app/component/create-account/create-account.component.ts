import { Component, OnInit, Renderer2 } from '@angular/core';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { Signup } from 'src/app/model/Signup';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrls: ['./create-account.component.css']
})
export class CreateAccountComponent implements OnInit {

  public user:Signup = new Signup();
  public fail:boolean = false;
  public success:boolean = false;
  public failedMessage:string ='';
  public retypePassword :string = '';

  constructor(private authenticationService: AuthenticationService,private renderer:Renderer2) { }

  ngOnInit(): void {
  }
  ngAfterViewInit(){
    const backgroundImageUrl = '../../../assets/dist/img/ima.jpg';

    this.renderer.setStyle(document.body, 'backgroundImage', 'url('+backgroundImageUrl+')');
    this.renderer.setStyle(document.body, 'background-repeat', 'repeat-y');
    this.renderer.setStyle(document.body, 'background-size', 'cover');
    
    }

  createAccount():void{
    if(this.retypePassword == this.user.password){
    this.authenticationService.createNewAccount(this.user).subscribe(res=>{
      this.fail = false;
      this.success = true;
      this.user =new Signup();
      this.retypePassword = '';
    },error=>{
      console.log(error);
      this.fail = true;
      this.success = false;
      this.failedMessage = error.error.message;
    })
  }else{
    this.success = false;
    this.fail = true;
    this.failedMessage = 'please retype your password well';
  }
  
}
}