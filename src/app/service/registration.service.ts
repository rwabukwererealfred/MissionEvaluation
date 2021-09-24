import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Employee } from '../model/Employee';
import { Mission } from '../model/Mission';
import { Fundings } from '../model/Fundings';
import { Observable } from 'rxjs';
import { MissionObjectif } from '../model/MissionObjectif';

const API = 'http://localhost:8080/api/mission/registration/';
const OPTIONS = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http:HttpClient) { }

  employeeRegistration(employee:Employee){
    return this.http.post(API+"employeeRegistration", JSON.stringify(employee), OPTIONS);
  }

  missionCreation(mission: Mission){
    return this.http.post(API+"missionCreation", JSON.stringify(mission), OPTIONS);
  }
  fundingsCreation(fundings: Fundings, missionId:number){
    const data={
      "fundings":fundings,
      "missionId":missionId
    }
    return this.http.post(API+"createFundings", JSON.stringify(data), OPTIONS);
  }
  missionObjectifs(details: MissionObjectif,missionId:number){
    const data={
      "details":details,
      "missionId":missionId
    }
    return this.http.post(API+"createMissionDetails", JSON.stringify(data), OPTIONS);
  }

  employeeList():Observable<any>{
    return this.http.get(API+"employeeList");
  }
  fundingsList(missionId:number){
    return this.http.get(API+"missionFundings/"+missionId);
  }
  missionDetailsList(missionId:number){
    return this.http.get(API+"missionDetails/"+missionId);
  }
  newMissionList():Observable<any>{
    return this.http.get(API+"newMission");
  }
  missionExperiedList():Observable<any>{
    return this.http.get(API+"missionExperied");
  }
  missionStartedList():Observable<any>{
    return this.http.get(API+"missionStarted");
  }

  


}
