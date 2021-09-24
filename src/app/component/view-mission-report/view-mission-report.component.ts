import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MissionListService } from 'src/app/service/mission-list.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RegistrationService } from 'src/app/service/registration.service';
import { MissionActivityService } from 'src/app/service/mission-activity.service';
import { MissionReport } from 'src/app/model/MissionReport';
import { MissionDto } from 'src/app/model/missionDto';

@Component({
  selector: 'app-view-mission-report',
  templateUrl: './view-mission-report.component.html',
  styleUrls: ['./view-mission-report.component.css']
})
export class ViewMissionReportComponent implements OnInit {
  public missionId:any;
  public reportList:any =[];
  filterTerm:any;

  constructor(private route: ActivatedRoute, private missionListService: MissionListService, private dialog: MatDialog,
     private activityService: MissionActivityService) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    this.missionId = Number(routeParams.get('missionId'));
    this.missionReportList();
  }
  readFile(fileName:string){
 
    this.activityService.downloadFile(fileName).subscribe(res=>{
      let file = new Blob([res], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      window.open(fileURL);
    },error=>{
      console.log(error);
    })
  }

  missionReportList(){
    console.log("id: "+this.missionId);
    this.missionListService.missionReportedToMe(this.missionId).subscribe(res=>{
      this.reportList = res;
      console.log("result: "+this.reportList);
    },error=>{
      console.log(error);
    })
  }
  openCommentDialog(report: MissionReport){
    const dialogRef= this.dialog.open(commentDialog,{
       width:"800px",
       data:report
     });
     
      
  }
  addCommentDiaolog(mission: any){
    console.log("id: "+mission.id);
    const dialogRef= this.dialog.open(addCommentDialog,{
       width:"800px",
       data:mission
     });
  }
}

@Component({
  selector:'commentDialog',
  templateUrl:'./commentDialog.html'
  
})
export class commentDialog{
  public comment?:string;
 
  
  constructor(public dialogRef: MatDialogRef<commentDialog>,
    @Inject(MAT_DIALOG_DATA) public data:any){
      this.comment = data.comment;
    }

   
}
@Component({
  selector:'addCommentDialog',
  templateUrl:'./addCommentDialog.html'
  
})
export class addCommentDialog{
  public missionId:number;
  public longString:String ="";
  public missionDto:MissionDto= new MissionDto();
  public message: String ="";
  public success:boolean = false;
  public fail : boolean = false;
  
  
  constructor(public dialogRef: MatDialogRef<addCommentDialog>, private missionActivity: MissionActivityService,
    @Inject(MAT_DIALOG_DATA) public data:any){
      this.missionId = data.id;
      console.log('ids: '+this.missionId);
    }
    sendComment(){

      this.missionDto.missionId = this.missionId;
      this.missionDto.comment = this.longString;
      console.log("id "+ this.missionId+" "+ this.data.id+ this.missionDto.missionId);
     
      this.missionActivity.advisorComment(this.missionDto).subscribe(res=>{
       // this.fundings = res;
       window.location.reload();
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