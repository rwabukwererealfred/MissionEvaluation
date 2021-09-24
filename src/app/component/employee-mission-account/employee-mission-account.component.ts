import { Component, OnInit, Inject } from '@angular/core';
import { MissionListService } from 'src/app/service/mission-list.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { StorageService } from 'src/app/service/storage.service';
import { MissionActivityService } from 'src/app/service/mission-activity.service';


@Component({
  selector: 'app-employee-mission-account',
  templateUrl: './employee-mission-account.component.html',
  styleUrls: ['./employee-mission-account.component.css']
})
export class EmployeeMissionAccountComponent implements OnInit {

  public newMissionList:any;
  public experiedMissionList:any;
  public filterTerm: any;
  public fail:boolean = false;
  public success:boolean= false;
  public message:string='';
  page = 1;
  count = 0;
  tableSize = 3;
  tableSizes = [3, 6, 9, 12];
  public role:string='';
  constructor(private listService: MissionListService, private dialog: MatDialog, private storage:StorageService,
    private activity: MissionActivityService) { }

  ngOnInit(): void {
    this.newMission();
    this.experiedMission();
    this.role = this.storage.getUser().employee.role.name;
  }

  
  newMission(){
    this.listService.myNewMission().subscribe(res=>{
      this.newMissionList = res;
    },error=>{
      console.log(error);
    })
  }
  experiedMission(){
    this.listService.myExperiedMission().subscribe(res=>{
      this.experiedMissionList = res;
    },error=>{
      console.log(error);
    })
  }
  onTableDataChange($event: any){
    this.page = $event;
    this.experiedMission();
    this.newMission();
  }
  onTableSizeChange($event:any): void {
    this.tableSize = $event.target.value;
    this.page = 1;
    this.experiedMission();
    this.newMission();

  } 
  openFundingDiaolog(mission: any){
    const dialogRef= this.dialog.open(fundingsDialog,{
       width:"800px",
       data:mission
     });
  }
  openObjectiveDiaolog(mission: any){
    const dialogRef= this.dialog.open(objectivesDialog,{
       width:"800px",
       data:mission
     });
  }
}

@Component({
  selector:'fundingsDialog',
  templateUrl:'./fundingsDialog.html'
  
})
export class fundingsDialog{
  public missionId:number;
  public fundings:any =[];
  
  constructor(public dialogRef: MatDialogRef<fundingsDialog>, private missionList: MissionListService,
    @Inject(MAT_DIALOG_DATA) public data:any){
      this.missionId = data.id;
      this.missionList.fundings(this.missionId).subscribe(res=>{
        this.fundings = res;
      },error=>{
        console.log(error);
      })
    }

   
}
@Component({
  selector:'objectivesDialog',
  templateUrl:'./objectivesDialog.html'
  
})
export class objectivesDialog{
  public missionId:number;
  public objectives:any =[];
 
  
  constructor(public dialogRef: MatDialogRef<objectivesDialog>,private missionList: MissionListService,
    @Inject(MAT_DIALOG_DATA) public data:any){
      this.missionId = data.id;
      this.missionList.missionObjectif(this.missionId).subscribe(res=>{
        this.objectives = res;
      },error=>{
        console.log(error);
      })
    }

   
}
