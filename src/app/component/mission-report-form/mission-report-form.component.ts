import { Component, OnInit, Inject } from '@angular/core';
import {  ActivatedRoute, Router } from '@angular/router';
import { MissionListService } from 'src/app/service/mission-list.service';
import { MissionObjectif } from 'src/app/model/MissionObjectif';
import { RegistrationService } from 'src/app/service/registration.service';

import { MissionActivityService } from 'src/app/service/mission-activity.service';
import { MissionReport } from 'src/app/model/MissionReport';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { MissionDto } from 'src/app/model/missionDto';


@Component({
  selector: 'app-mission-report-form',
  templateUrl: './mission-report-form.component.html',
  styleUrls: ['./mission-report-form.component.css']
})
export class MissionReportFormComponent implements OnInit {

  public missionId:any;
  public longString:any;
  public missionObjectives:MissionObjectif[] =[];
  public mission:any ={};
  public missionObjectif:MissionObjectif = new MissionObjectif();
  public employees:any = [];
  public file: any;
  public missionReport:MissionReport = new MissionReport();
  public employeeId:string ='';
  public success: boolean = false;
  public failed: boolean = false;
  public message:string='';
  public reportList:any =[];

  constructor(private route: ActivatedRoute, private missionListService: MissionListService, private dialog: MatDialog,
    private registrationService: RegistrationService, private activityService: MissionActivityService) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    this.missionId = Number(routeParams.get('missionId'));
    this.missionListService.missionById(this.missionId).subscribe(res=>{
      this.mission = res;
    },error=>{
      console.log(error);
    })
    this.objectives();
    console.log(this.missionId);
    this.employeeList();
    this.missionReportList();
  }

  objectives(){
    this.missionListService.missionObjectif(this.missionId).subscribe(res=>{
      
      this.missionObjectives = res;
    },error=>{
      console.log(error);
    })
  }
employeeList(){
  this.registrationService.employeeList().subscribe(res=>{
    for(let em of res){
      if(em.role.name ==='ADVISOR'){
        this.employees.push(em);
      }
    }
   
  },error=>{
    console.log(error);
  })
}
fileChangeEvent(event:any) {
  this.file = event.target.files[0];
}
sendReport(){
  console.log(this.employeeId);
  const formData = new FormData();
  this.missionReport.comment = this.longString;
  formData.append('file', this.file);
  formData.append('employeeReport', JSON.stringify(this.missionReport));
  formData.append('employeeMissionId', this.missionId);
  formData.append('employeeId', this.employeeId);

  this.activityService.sendApplication(formData).subscribe(res=>{
    this.success = true;
    this.failed = false;
    this.missionReportList()
    this.file = [];
    this.missionReport = new MissionReport();
    this.employeeId = '';
    this.longString = '';
  },error=>{
    this.message = error.error.message;
    this.failed = true;
    this.success = false;
    console.log(error);
  })
}

missionReportList(){
  this.missionListService.missionReportByEmployee(this.missionId).subscribe(res=>{
    this.reportList = res;
  },error=>{
    console.log(error);
  })
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
openDropDiaolog(report: MissionReport){
  const dialogRef= this.dialog.open(missionDescription,{
     width:"800px",
     data:report
   });
}


}
@Component({
  selector:'missionDescription',
  templateUrl:'./missionDescription.html'
  
})
export class missionDescription{
  public comment?:string;
 
  
  constructor(public dialogRef: MatDialogRef<missionDescription>,
    @Inject(MAT_DIALOG_DATA) public data:any){
      this.comment = data.comment;
    }

   
}

