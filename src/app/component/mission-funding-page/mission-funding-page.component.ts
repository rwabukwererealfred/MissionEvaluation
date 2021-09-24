import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/service/registration.service';
import { ActivatedRoute, Params } from '@angular/router';
import { Fundings } from 'src/app/model/Fundings';
import { MissionObjectif } from 'src/app/model/MissionObjectif';


@Component({
  selector: 'app-mission-funding-page',
  templateUrl: './mission-funding-page.component.html',
  styleUrls: ['./mission-funding-page.component.css']
})
export class MissionFundingPageComponent implements OnInit {

  public successMessage:boolean =false;
  public failMessage: boolean = false;
  public failedMessage:string ='';
  public newMissions:any;
  public filterTerm:any;
  public filterTerm1:any;
  page = 1;
  count = 0;
  tableSize = 3;
  tableSizes = [3, 6, 9, 12];
  private missionId: number =0;
  public fundings:any;
  public fund: Fundings = new Fundings();
  public missionDetails:any;
  public detail:MissionObjectif = new MissionObjectif();
  constructor(private registrationService: RegistrationService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    
    const routeParams = this.route.snapshot.paramMap;
    this.missionId = Number(routeParams.get('productId'));
    this.fundingsList();
    this.detailsList();
  }
  fundingsList(){
    this.registrationService.fundingsList(this.missionId).subscribe(res=>{
      this.fundings = res;
    }, error=>{
      console.log(error);
    })
  }

  detailsList(){
    this.registrationService.missionDetailsList(this.missionId).subscribe(res=>{
      this.missionDetails = res;
    },error=>{
      console.log(error);
    })
  }
  saveMissionObjectif(){
    this.registrationService.missionObjectifs(this.detail, this.missionId).subscribe(res=>{
      this.successMessage = true;
      this.failMessage = false;
      this.detailsList();
      this.detail = new MissionObjectif();
    },error=>{
      console.log(error);
      this.failMessage = true;
      this.successMessage = false;
      this.failedMessage = error.error.message;
    })
  }
  saveFundings(){
    this.registrationService.fundingsCreation(this.fund, this.missionId).subscribe(res=>{
      this.successMessage = true;
      this.failMessage = false;
      this.fundingsList();
      this.fund = new Fundings();
    },error=>{
      console.log(error);
      this.failMessage = true;
      this.successMessage = false;
      this.failedMessage = error.error.message;
    })
  }

  onTableDataChange($event: any){
    this.page = $event;
    this.fundingsList();
  }
  onTableSizeChange($event:any): void {
    this.tableSize = $event.target.value;
    this.page = 1;
    this.fundingsList();

  }
}
