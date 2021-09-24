import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponentComponent } from './component/login-component/login-component.component';
import { HeaderComponentComponent } from './component/header-component/header-component.component';
import { SidebarComponent } from './component/sidebar/sidebar.component';
import { FooterComponentComponent } from './component/footer-component/footer-component.component';
import { ContentComponentComponent } from './component/content-component/content-component.component';
import { CreateAccountComponent } from './component/create-account/create-account.component';
import { EmployeeRegistrationComponent } from './component/employee-registration/employee-registration.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule} from '@angular/material/input';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import {MatRadioModule} from '@angular/material/radio';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';
import { MissionRegistrationComponent, fundingsDialogs, objectivesDialogs, addComment, viewComment } from './component/mission-registration/mission-registration.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { MissionFundingPageComponent } from './component/mission-funding-page/mission-funding-page.component';
import { EmployeeSelectionComponent } from './component/employee-selection/employee-selection.component';
import { authInterceptorProviders } from './service/auth.interceptor';
import { MatDialogModule, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { EmployeeMissionAccountComponent, fundingsDialog, objectivesDialog } from './component/employee-mission-account/employee-mission-account.component';
import { MissionReportFormComponent, missionDescription } from './component/mission-report-form/mission-report-form.component';
import { QuillModule } from 'ngx-quill';
import { ViewMissionReportComponent, commentDialog, addCommentDialog } from './component/view-mission-report/view-mission-report.component';
import { MissionEvaluationFormComponent } from './component/mission-evaluation-form/mission-evaluation-form.component';
import { HrReportFormComponent } from './component/hr-report-form/hr-report-form.component';
import { DatePipe } from '@angular/common';
import { TraineeFormComponent } from './component/trainee-form/trainee-form.component';
import { TraineeRegistrationFormComponent } from './component/trainee-registration-form/trainee-registration-form.component';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponentComponent,
    HeaderComponentComponent,
    SidebarComponent,
    FooterComponentComponent,
    ContentComponentComponent,
    CreateAccountComponent,
    EmployeeRegistrationComponent,
    MissionRegistrationComponent,
    MissionFundingPageComponent , addCommentDialog,
    EmployeeSelectionComponent,commentDialog,addComment , viewComment,
    EmployeeMissionAccountComponent,fundingsDialog, objectivesDialog , fundingsDialogs, objectivesDialogs,
    MissionReportFormComponent, missionDescription, ViewMissionReportComponent, MissionEvaluationFormComponent, HrReportFormComponent, TraineeFormComponent, TraineeRegistrationFormComponent
  ],
  entryComponents: [missionDescription, fundingsDialog, objectivesDialog, commentDialog, fundingsDialogs, objectivesDialogs,
    addComment, viewComment, addCommentDialog],
  imports: [
    FormsModule, QuillModule.forRoot(),
    BrowserModule,MatDialogModule, MatRadioModule,
    AppRoutingModule, MatTabsModule, HttpClientModule, BrowserAnimationsModule, MatFormFieldModule,MatInputModule,MatTableModule
    ,MatPaginatorModule,Ng2SearchPipeModule,
    MatSortModule,NgxPaginationModule,
    ReactiveFormsModule.withConfig({ warnOnNgModelWithFormControl: 'never' })
  ],
  providers: [
    authInterceptorProviders,DatePipe,
    { provide:MAT_DIALOG_DATA, useValue:{}},
    { provide:MatDialogRef, useValue:{}} 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
