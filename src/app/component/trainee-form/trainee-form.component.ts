import { Component, OnInit, Renderer2 } from '@angular/core';
import { MissionListService } from 'src/app/service/mission-list.service';
import { RegistrationService } from 'src/app/service/registration.service';
import { MissionActivityService } from 'src/app/service/mission-activity.service';

@Component({
  selector: 'app-trainee-form',
  templateUrl: './trainee-form.component.html',
  styleUrls: ['./trainee-form.component.css']
})
export class TraineeFormComponent implements OnInit {
  public longString:any;
  public missions:any =[];
  public missionId:number =0;
  public traineeID:string='';
  public fail:boolean = false;
  public success:boolean = false;
  public message:string ='';

  constructor(private renderer:Renderer2, private missionListService:RegistrationService, 
    private missionActivityService:MissionActivityService) { }

  ngOnInit(): void {
    this.missionList();
  }

  ngAfterViewInit(){
    const backgroundImageUrl = '../../../assets/dist/img/ima.jpg';

    this.renderer.setStyle(document.body, 'backgroundImage', 'url('+backgroundImageUrl+')');
    this.renderer.setStyle(document.body, 'background-repeat', 'repeat-y');
    this.renderer.setStyle(document.body, 'background-size', 'cover');
    
    }
    missionList(){
      this.missionListService.missionExperiedList().subscribe(res=>{
       this.missions = res;
      },error=>{
        console.log(error);
      })
    }

    sendComment(){
      console.log(this.missionId+" "+ this.traineeID+" "+ this.longString)
      this.missionActivityService.traineeSendComment(this.missionId, this.traineeID, this.longString).subscribe(res=>{
        console.log(res);
        this.fail = false;
        this.success = true;
        this.longString ='';
        this.missionId =0;
        this.traineeID ='';
      },error=>{
        this.success = false;
        this.fail = true;
        console.log(error);
        this.message = error.error.message;
      })
    }

}
