import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/service/registration.service';
import { Employee } from 'src/app/model/Employee';


@Component({
  selector: 'app-employee-registration',
  templateUrl: './employee-registration.component.html',
  styleUrls: ['./employee-registration.component.css']
})
export class EmployeeRegistrationComponent implements OnInit {

  public employee: Employee = new Employee();
  public employees:Employee[]=[];
  public filterTerm: any;
  public fail : boolean = false;
  public success: boolean = false;
  public failedMessage : string = '';
  public role:string='';

  page = 1;
  count = 0;
  tableSize = 3;
  tableSizes = [3, 6, 9, 12];
  constructor(private registerService: RegistrationService) { 
   
  }
  

  ngOnInit(): void {
   this.employeeList();
  }

  registerEmployee():void{
    this.employee.role.name = this.role;
    this.registerService.employeeRegistration(this.employee).subscribe(res=>{
       this.employee =new Employee();
      this.employeeList();
      this.fail = false;
      this.success = true;
    }, error=>{
      console.log(error);
      this.fail = true;
      this.success = false;
      this.failedMessage = error.error.message;
      
    })
  }
  employeeList(){
    this.registerService.employeeList().subscribe(res=>{
      this.employees = res;
     
    },error=>{
      console.log(error);
    })
  }
  onTableDataChange($event: any){
    this.page = $event;
    this.employeeList();
  }
  onTableSizeChange($event:any): void {
    this.tableSize = $event.target.value;
    this.page = 1;
    this.employeeList();

  } 

}

