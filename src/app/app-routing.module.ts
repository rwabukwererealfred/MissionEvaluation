import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponentComponent } from './component/login-component/login-component.component';
import { ContentComponentComponent } from './component/content-component/content-component.component';
import { CreateAccountComponent } from './component/create-account/create-account.component';
import { EmployeeRegistrationComponent } from './component/employee-registration/employee-registration.component';
import { MissionRegistrationComponent } from './component/mission-registration/mission-registration.component';
import { MissionFundingPageComponent } from './component/mission-funding-page/mission-funding-page.component';
import { EmployeeSelectionComponent } from './component/employee-selection/employee-selection.component';
import { EmployeeMissionAccountComponent } from './component/employee-mission-account/employee-mission-account.component';
import { MissionReportFormComponent } from './component/mission-report-form/mission-report-form.component';
import { ViewMissionReportComponent } from './component/view-mission-report/view-mission-report.component';
import { MissionEvaluationFormComponent } from './component/mission-evaluation-form/mission-evaluation-form.component';
import { HrReportFormComponent } from './component/hr-report-form/hr-report-form.component';
import { TraineeFormComponent } from './component/trainee-form/trainee-form.component';
import { TraineeRegistrationFormComponent } from './component/trainee-registration-form/trainee-registration-form.component';



const routes: Routes = [
  { path: '',pathMatch:'full', redirectTo:'login'},
  { path: 'login', component:LoginComponentComponent},
  { path: 'employeeRegistration', component:EmployeeRegistrationComponent},
  { path: 'newAccount', component:CreateAccountComponent},
  { path: 'fundingDetails/:productId', component:MissionFundingPageComponent},
  { path: 'employeeSelection/:missionId', component:EmployeeSelectionComponent},
  { path: 'missionRegistration', component:MissionRegistrationComponent},
  { path: 'myMissionAccount', component:EmployeeMissionAccountComponent},
  { path: 'missionReport/:missionId', component:MissionReportFormComponent},
  { path: 'viewMissionReport/:missionId', component:ViewMissionReportComponent},
  { path: 'evaluationForm/:missionId', component:MissionEvaluationFormComponent},
  { path: 'hrReportForm', component:HrReportFormComponent},
  { path: 'trainee', component:TraineeFormComponent},
  { path: 'traineeForm/:missionId', component:TraineeRegistrationFormComponent}
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes , {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
