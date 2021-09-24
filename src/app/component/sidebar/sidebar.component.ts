import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  public check:any;
  public names:any;
  constructor(private storage: StorageService) { }

  ngOnInit() {
   
    this.check = this.storage.getUser().employee.role.name;
    this.names = this.storage.getUser().employee.firstName +" "+this.storage.getUser().employee.lastName ;
  }
logout(){
  this.storage.signout();
  
}
}
