import { Component, OnInit } from '@angular/core';

import { ApiService } from '../../shared/services/api.service';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css'],
  providers: [ApiService]
})
export class ViewTaskComponent implements OnInit {
  tasks$: Observable<any>;
  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.tasks$ = this.apiService.getAllTasks();
  }

  getDate(isoDate) {
    const date = new Date(isoDate);
    return date.getDate()+'/' + (date.getMonth()+1) + '/'+date.getFullYear();
  }

  endTask(taskId) {
    this.apiService.deleteTask(taskId).subscribe(() => {
      this.tasks$ = this.apiService.getAllTasks();
    });
  }

}
