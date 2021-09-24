import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MissionListService } from 'src/app/service/mission-list.service';
import { MatDialog } from '@angular/material/dialog';
import { MissionActivityService } from 'src/app/service/mission-activity.service';
import { MissionObjectif } from 'src/app/model/MissionObjectif';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-mission-evaluation-form',
  templateUrl: './mission-evaluation-form.component.html',
  styleUrls: ['./mission-evaluation-form.component.css']
})
export class MissionEvaluationFormComponent implements OnInit {

  public missionId:any;
  public reportList:any =[];
  filterTerm:any;
  public objectives:any =[];
  public checkObjectives :any=[];
  public percentege: number =0;
  public success:boolean = false;
  public fail : boolean = false;
  public message:string ='';
  constructor(private route: ActivatedRoute, private missionListService: MissionListService, private dialog: MatDialog,
    private activityService: MissionActivityService, private router: Router) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    this.missionId = Number(routeParams.get('missionId'));
    this.missionReportList();
    this.objectifList();
  }

  objectifList(){
    this.missionListService.missionObjectif(this.missionId).subscribe(res=>{
      this.objectives = res;
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
  missionReportList(){
    console.log("id: "+this.missionId);
    this.missionListService.missionReportedToMe(this.missionId).subscribe(res=>{
      this.reportList = res;
      console.log("result: "+this.reportList);
    },error=>{
      console.log(error);
    })
  }

  
  checkButton(){
    this.percentege = 0;
    const count = this.objectives.length;
    console.log(count);
    
    for(let obj of this.objectives){
      this.percentege = (+this.percentege + +obj.percentage);
    }
    this.percentege = this.percentege / count;
  }
  submit(){
    this.activityService.updateMissionPercentage(this.objectives).subscribe(res=>{
      this.percentege =0;
      this.success = true;
      this.fail = false;
      this.router.navigate(['/myMissionAccount']);
    },error=>{
      console.log(error);
      this.fail = true;
      this.success = false;
      this.message = error.error.message;
    })
  }

}
