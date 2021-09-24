import { Component, OnInit } from '@angular/core';
import { MissionActivityService } from 'src/app/service/mission-activity.service';
import { DatePipe } from '@angular/common';
import { RegistrationService } from 'src/app/service/registration.service';
import { Employee } from 'src/app/model/Employee';
import { Mission } from 'src/app/model/Mission';


@Component({
  selector: 'app-hr-report-form',
  templateUrl: './hr-report-form.component.html',
  styleUrls: ['./hr-report-form.component.css']
})
export class HrReportFormComponent implements OnInit {

  startDate?: Date;
  endDate?: Date;
  public missionId:string ='';
  public missions:Mission[]=[];
 
  constructor(private activityService: MissionActivityService, public datepipe: DatePipe,
     private registrationService: RegistrationService) { }

  ngOnInit(): void {
    this.missionList();
  }
  generateReport() {
     let sDate =this.datepipe.transform(this.startDate, 'yyyy-MM-dd');
    let edate =this.datepipe.transform(this.endDate, 'yyyy-MM-dd');
    const formData = new FormData();
    formData.append('startDate',sDate||'');
    formData.append('endDate', edate ||'');
    console.log("start date "+sDate+" endDate "+ edate);
    this.activityService.dateBetweenReport(formData).subscribe(res => {
      let pdfUrl = window.URL.createObjectURL(res);
      window.open(pdfUrl);
      console.log(res);
    }, error => console.log(error));
  }
  generateEmployeeReport(){
    const formData = new FormData();
    formData.append('missionId',this.missionId);
    this.activityService.employeeReport(formData).subscribe(res => {
      let pdfUrl = window.URL.createObjectURL(res);
      window.open(pdfUrl);
      console.log(res);
    }, error => console.log(error));
  }

  missionList(){
    let mission1 :Mission[]=[];
   
    this.registrationService.missionStartedList().subscribe(res=>{
     for(let miss of res){
       mission1.push(miss);
     }
    }, error=>{
      console.log("mission1 "+error);
    })
    this.registrationService.missionExperiedList().subscribe(res=>{
      for(let miss of res){
        mission1.push(miss);
      }
    }, error=>{
      console.log("mission2 "+error);
    })
    this.missions = mission1;
  }

}
