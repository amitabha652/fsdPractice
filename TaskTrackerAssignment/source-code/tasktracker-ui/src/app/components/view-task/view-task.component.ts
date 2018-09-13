import { Component, OnInit } from '@angular/core';

import { ApiService } from '../../shared/services/api.service';
import { UtilService } from '../../shared/services/util.service';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css'],
  providers: [ApiService]
})
export class ViewTaskComponent implements OnInit {
  tasks$: Observable<any>;
  searchText: string = '';
  taskName: string = '';
  parentTaskName: string = '';


  constructor(private apiService: ApiService,
              private utilService: UtilService) { }

  ngOnInit() {
    this.tasks$ = this.apiService.getAllTasks();
  }

  getDate(isoDate) {
    return this.utilService.getDate(isoDate);
  }

  endTask(taskId) {
    this.apiService.deleteTask(taskId).subscribe(() => {
      this.tasks$ = this.apiService.getAllTasks();
    });
  }

  searchTask(event) {
    if (event.target.placeholder === "Search By Task") {
      this.taskName = event.target.value + ":" + event.target.placeholder;
    } else if (event.target.placeholder === "Search By Parent Task") {
      this.parentTaskName = event.target.value + ":" + event.target.placeholder;
    }
    this.searchText = this.taskName + this.parentTaskName;
    console.log(event);
  }

}
