import { Component, OnInit, Inject } from '@angular/core';
import { RegistrationService } from 'src/app/service/registration.service';
import { Mission } from 'src/app/model/Mission';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { MissionActivityService } from 'src/app/service/mission-activity.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MissionListService } from 'src/app/service/mission-list.service';
import { MissionDto } from 'src/app/model/missionDto';

@Component({
  selector: 'app-mission-registration',
  templateUrl: './mission-registration.component.html',
  styleUrls: ['./mission-registration.component.css']
})
export class MissionRegistrationComponent implements OnInit {

  public mission: Mission = new Mission();
  public successMessage:boolean =false;
  public successMessages:boolean = false;
  public failMessage: boolean = false;
  public failedMessage:string ='';
  public newMissions:any =[];
  public experiedMissions:any =[];
  public filterTerm:any;
  public startedMissions :any =[];
  page = 1;
 
  count = 0;
  
  tableSize = 3;
  tableSizes = [3, 6, 9, 12];

  constructor(private registrationService: RegistrationService, private route: Router,
     private activity: MissionActivityService , private dialog: MatDialog) { }

  ngOnInit(): void {
    this.newMissionList();
    this.experiedMissionList();
    this.startedMissionList();
  }

  missionCreationMethod():void{
    this.registrationService.missionCreation(this.mission).subscribe(res=>{
     
      this.successMessage = true;
      this.failMessage = false;
      this.newMissionList();
      this.mission = new Mission();
    }, error=>{
      console.log(error);
      this.successMessage = false;
      this.failMessage = true;
      this.failedMessage = error.error.message;
    })
  }
  newMissionList(){
    this.registrationService.newMissionList().subscribe(res=>{
      this.newMissions = res;
    },error=>{
      console.log(error);
    })
  
  }

  experiedMissionList(){
    this.registrationService.missionExperiedList().subscribe(res=>{
      this.experiedMissions = res;
    },error=>{
      console.log(error);
    })
  }
  startedMissionList(){
    this.registrationService.missionStartedList().subscribe(res=>{
      this.startedMissions = res;
    },error=>{
      console.log(error);
    })
  }
  updateMissionExperied(id:number){
    console.log(id);
    this.activity.startMission(id).subscribe(res=>{
      this.failMessage = false;
      this.successMessages = true;
      this.successMessage = false;
      this.newMissionList();
      this.experiedMissionList();
      this.startedMissionList();
    },error=>{
      this.failMessage = true;
      this.successMessage = false;
      this.successMessages = false;
      this.failedMessage = error.error.message;
      console.log(error);
    })
  }
  onTableDataChange($event: any){
    this.page = $event;
    this.newMissionList();
    this.experiedMissionList();
    this.startedMissionList();
  }
  onTableSizeChange($event:any): void {
    this.tableSize = $event.target.value;
    this.page = 1;
    this.newMissionList();
    this.experiedMissionList();
    this.startedMissionList();

  } 
  openFundingDiaolog(mission: any){
    console.log("id: "+mission.id);
    const dialogRef= this.dialog.open(fundingsDialogs,{
       width:"800px",
       data:mission
     });
  }
  addCommentDiaolog(mission: any){
    console.log("id: "+mission.id);
    const dialogRef= this.dialog.open(addComment,{
       width:"800px",
       data:mission
     });
  }
  openObjectiveDiaolog(mission: any){
    const dialogRef= this.dialog.open(objectivesDialogs,{
       width:"800px",
       data:mission
     });
  }
  viewCommentDiaolog(mission: any){
    const dialogRef= this.dialog.open(viewComment,{
       width:"800px",
       data:mission
     });
  }

}
@Component({
  selector:'fundingsDialogs',
  templateUrl:'./fundingsDialogs.html'
  
})
export class fundingsDialogs{
  public missionId:number;
  public fundings:any =[];
  
  constructor(public dialogRef: MatDialogRef<fundingsDialogs>, private missionList: MissionListService,
    @Inject(MAT_DIALOG_DATA) public data:any){
      this.missionId = data.id;
      console.log('ids: '+this.missionId);
      this.missionList.funding(this.missionId).subscribe(res=>{
        this.fundings = res;
      },error=>{
        console.log(error);
      })
    }

   
}
@Component({
  selector:'objectivesDialogs',
  templateUrl:'./objectivesDialogs.html'
  
})
export class objectivesDialogs{
  public missionId:number;
  public objectives:any =[];
 
  
  constructor(public dialogRef: MatDialogRef<objectivesDialogs>,private missionList: MissionListService,
    @Inject(MAT_DIALOG_DATA) public data:any){
      this.missionId = data.id;

      this.missionList.missionObjectives(this.missionId).subscribe(res=>{
        this.objectives = res;
      },error=>{
        console.log(error);
      })
    }
  
  }
  @Component({
    selector:'viewComment',
    templateUrl:'./viewComment.html'
    
  })
  export class viewComment{
    public missionComment:String ="";
    constructor(public dialogRef: MatDialogRef<viewComment>,private missionList: MissionListService,
      @Inject(MAT_DIALOG_DATA) public data:any){

        this.missionComment = data.hrComment;
        console.log("comment "+this.missionComment+" "+data.comment);
  
      }
    
    }

  @Component({
    selector:'addComment',
    templateUrl:'./addComment.html'
    
  })
  export class addComment{
    public missionId:number;
    public longString:String ="";
    public missionDto:MissionDto= new MissionDto();
    public message: String ="";
    public success:boolean = false;
    public fail : boolean = false;
    
    
    constructor(public dialogRef: MatDialogRef<addComment>, private missionActivity: MissionActivityService,
      @Inject(MAT_DIALOG_DATA) public data:any){
        this.missionId = data.id;
        console.log('ids: '+this.missionId);
      }
      sendComment(){

        this.missionDto.missionId = this.missionId;
        this.missionDto.comment = this.longString;
        console.log("id "+ this.missionId+" "+ this.data.id+ this.missionDto.missionId);
       
        this.missionActivity.hrAddComment(this.missionDto).subscribe(res=>{
         // this.fundings = res;
         this.success= true;
         this.fail = false;
         this.longString ="";
        },error=>{
          this.fail = true;
          this.success = false;
          this.message =error.error.message;
          console.log(error);
        })
      }
      
  
     
  }