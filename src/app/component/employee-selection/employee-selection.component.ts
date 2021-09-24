import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/service/registration.service';
import { Employee } from 'src/app/model/Employee';
import { MissionActivityService } from 'src/app/service/mission-activity.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-employee-selection',
  templateUrl: './employee-selection.component.html',
  styleUrls: ['./employee-selection.component.css']
})
export class EmployeeSelectionComponent implements OnInit {

  public employees:any =[];
  public fail:boolean = false;
  public success : boolean = false;
  public failMessage:string ='';
  page = 1;
  page1 = 1;
  count = 0;
  
  tableSize = 3;
  tableSizes = [3, 6, 9, 12];
  
  public filterTerm: any;
  public selectEmployee:any =[];
  private missionId: number =0;
  public employeeSectedToMissions:any;
  
  constructor(private registerService: RegistrationService, private missionActivity: MissionActivityService, 
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.employeeList();
    const routeParams = this.route.snapshot.paramMap;
    this.missionId = Number(routeParams.get('missionId'));
    this.employeeSected();
  }
  select(event:any, employee:Employee) {
    if (event.target.checked) {
      this.selectEmployee.push(employee);
      console.log(this.selectEmployee);
    } else {
      this.selectEmployee.splice(this.selectEmployee.indexOf(employee), 1);
    }
    
  }
  employeeSected(){
    this.missionActivity.employeeSelectedToMission(this.missionId).subscribe(res=>{
      this.employeeSectedToMissions = res;
    },error=>{
      console.log(error);
    })
  }

  addEmployeeToList(){
  for(let em of this.selectEmployee){
    this.missionActivity.addEmployee(em.natinalityID, this.missionId).subscribe(res=>{
      
      this.selectEmployee = [];
      this.fail = false;
      this.success = true;
      this.employeeSected();

    },error=>{
      this.success = false;
      this.fail = true;
      console.log(error);
      this.failMessage = error.error.message;
    })
    }
  }
  back(){
    this.fail = false;
    this.success = false;
    window.location.reload();
  }
  employeeList(){
    this.registerService.employeeList().subscribe(res=>{
      for(let em of res){
        if(em.role.name !=='HR'){
          this.employees.push(em);
        }
      }
       
     
    },error=>{
      console.log(error);
    })
  }
  onTableDataChange($event: any){
    this.page = $event;

    //this.employeeList();
  }
  onTableSizeChange($event:any): void {
    this.tableSize = $event.target.value;
    this.page = 1;
    //this.employeeList();

  } 
  
}
