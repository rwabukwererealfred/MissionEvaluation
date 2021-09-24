import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Trainee } from '../model/Trainee';
import { MissionDto } from '../model/missionDto';

const API = 'http://localhost:8080/api/mission/activity/';
const APIS = 'http://localhost:8080/api/mission/report/';
const OPTIONS = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class MissionActivityService {

  constructor(private http:HttpClient) { }

  addEmployee(employeeId: string, missionId: number ){
    const data ={
      "mission": missionId,
      "employee":employeeId
    }
    return this.http.post(API+"addEmployee", JSON.stringify(data), OPTIONS);
  }

  employeeSelectedToMission(missionId:number):Observable<any>{
    return this.http.get(API+"employeeMission/"+missionId)
  }
  startMission(missionId:number){
    return this.http.put(API+"startMisson/"+missionId, OPTIONS);
  }
 
  sendApplication(formData: FormData){
    return this.http.post(API+"sendDailyReport", formData,{ observe: 'response' });
  }
  downloadFile(fileName:string): Observable<any>{
		return this.http.get(API+"download/"+fileName, { responseType: 'arraybuffer' as 'json'});
  }
  updateMissionPercentage(objectiver:any){
    return this.http.put(API+"updatePercentage", JSON.stringify(objectiver), OPTIONS);
  }

  dateBetweenReport(formData:FormData){
    let headers = new HttpHeaders();
    
    headers = headers.set('Accept', 'application/pdf');
    return this.http.post(APIS+"missionReport",formData,{headers: headers, responseType: 'blob'});
  }
  employeeReport(formData:FormData){
    console.log("res: "+ formData.get("missionId"));
    let header = new HttpHeaders();
    header = header.set('Accept', 'application/pdf');
    return this.http.post(APIS+"employeeReport",formData,{headers: header, responseType: 'blob'});
  }
  traineeRegistration(missionId:number, trainee:Trainee){
    const data ={
      "missionId":missionId,
      "trainee":trainee
    }
    return this.http.post(API+"registerTrainee",JSON.stringify(data), OPTIONS);
  }
  traineeSendComment(missionId:number, nationalId:string, comment:string){
    const data ={
      "missionId":missionId,
      "nationalId":nationalId,
      "comment":comment
    }
    return this.http.post(API+"traineeSendComment", JSON.stringify(data), OPTIONS);
  }
  hrAddComment(missionDto:MissionDto){
    return this.http.put(API+"hrAddComment", JSON.stringify(missionDto), OPTIONS);
  }
  advisorComment(missionDto:MissionDto){
    return this.http.put(API+"advisorAddComment", JSON.stringify(missionDto), OPTIONS);
  }
}
