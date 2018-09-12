import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class ApiService {
  baseUrl: string

  constructor(private http: Http) {
    this.baseUrl = 'http://localhost:8080/tasktrackerbackend/';
   }

  /******* Service Call To CREATE New Task Details *******/
  createTask(form) {
    const formVal = form.value;
    const payload = {
        // taskId is not sent from UI it is being created at backend application
        // taskId: 16,
        task: formVal.task,
        parentId: formVal.parentTask,
        startDate: new Date(formVal.sdate).toISOString(),
        endDate: new Date(formVal.edate).toISOString(),
        priority: formVal.priority
    };
    return this.http.post(this.baseUrl + 'task/create', payload);
  }


  /******* Service Call To Update Existing Task Details *******/
  udpateTask(form) {
    const formVal = form.value;
    const payload = {
        taskId: formVal.taskId,
        task: formVal.task,
        parentId: formVal.parentTask,
        startDate: new Date(formVal.sdate).toISOString(),
        endDate: new Date(formVal.edate).toISOString(),
        priority: formVal.priority
    };
    return this.http.put(this.baseUrl + 'task/edit/'+payload.taskId, payload);
  }

  getParentTasks() {
    return this.http.get(this.baseUrl + '/parent/list').map(res => res.json());
  }

  getEditTask() {
    return {
        taskId: 1,
        task: "task1",
        parentId: 105,
        startDate: new Date().toISOString(),
        endDate: new Date().toISOString(),
        priority: 30
    };
  }
}
