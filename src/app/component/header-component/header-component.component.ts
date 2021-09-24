import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/service/storage.service';

@Component({
  selector: 'app-header-component',
  templateUrl: './header-component.component.html',
  styleUrls: ['./header-component.component.css']
})
export class HeaderComponentComponent implements OnInit {

  constructor(private storage: StorageService) { }

  ngOnInit() {
  }
  logout(){
    this.storage.signout();
    
  }

}
