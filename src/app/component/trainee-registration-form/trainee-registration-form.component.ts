import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MissionActivityService } from 'src/app/service/mission-activity.service';
import { Trainee } from 'src/app/model/Trainee';
import { MissionListService } from 'src/app/service/mission-list.service';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-trainee-registration-form',
  templateUrl: './trainee-registration-form.component.html',
  styleUrls: ['./trainee-registration-form.component.css']
})
export class TraineeRegistrationFormComponent implements OnInit {

  public missionId: number =0;
  public trainee: Trainee =new Trainee();
  public message:any;
  public fail:boolean = false;
  public success:boolean = false;
  public trainees:any=[];
  public role:string='';
  public traineeComments:any=[];
  
  constructor(private route: ActivatedRoute, private missionActivity:MissionActivityService,
    private missionServiceList:MissionListService, private storage:StorageService) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    this.missionId = Number(routeParams.get('missionId'));
    this.traineeList();
    this.role = this.storage.getUser().employee.role.name;
    this.traineeCommentList();
  }

  traineeList(){
    this.missionServiceList.traineeList(this.missionId).subscribe(res=>{
      this.trainees = res;
    },error=>{
      console.log(error);
    })
  }

  registerTrainee(){
    this.missionActivity.traineeRegistration(this.missionId,this.trainee).subscribe(res=>{
    
      this.success = true;
      this.fail = false;
      this.trainee = new Trainee();
      console.log(res);
      this.traineeList();
    },error=>{
      this.fail = true;
      this.success = false;
      this.message = error.error.message;
    })
  }

  traineeCommentList(){
    this.missionServiceList.traineeCommentList(this.missionId).subscribe(res=>{
      this.traineeComments = res;
    },error=>{
      console.log(error);
    })
  }

}
