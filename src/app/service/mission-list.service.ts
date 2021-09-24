import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API = 'http://localhost:8080/api/mission/missionList/';
const OPTIONS = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class MissionListService {

  constructor(private http: HttpClient) { }

  myNewMission():Observable<any>{
    return this.http.get(API+"myNewAvailableList");
  }
  myExperiedMission():Observable<any>{
    return this.http.get(API+"myExperiedMissionList");
  }
  missionObjectif(missionId:number):Observable<any>{
    return this.http.get(API+"missionObjects/"+missionId);
  }
  missionObjectives(missionId:number):Observable<any>{
    return this.http.get(API+"objectives/"+missionId);
  }
  missionById(missionId:number){
    return this.http.get(API+"employeeMissionById/"+missionId);
  }

  missionReportByEmployee(employeeMissionId:number){
    return this.http.get(API+"employeeMissionReport/"+employeeMissionId);
  }
  fundings(missionId: number):Observable<any>{
    return this.http.get(API+"fundings/"+missionId);
  }
  funding(missionId: number):Observable<any>{
    return this.http.get(API+"funding/"+missionId);
  }
  
  missionReportedToMe(employeeMissionId:number){
    return this.http.get(API+"missionReportedToMe/"+employeeMissionId);
  }
  traineeList(missionId:number){
    return this.http.get(API+"traineeList/"+missionId);;
  }

  traineeCommentList(missionId:number){
    return this.http.get(API+"traineeComments/"+missionId);
  }
}
