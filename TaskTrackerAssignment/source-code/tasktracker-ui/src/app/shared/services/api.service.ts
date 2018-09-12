import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class ApiService {
  baseUrl: string

  constructor(private http: Http) {
    this.baseUrl = 'http://localhost:8080/tasktrackerbackend/';
   }

  createForm(form) {
    const formVal = form.value;
    const payload = {
        taskId: 16,
        task: formVal.task,
        parentId: formVal.parentTask,
        startDate: new Date(formVal.sdate).toISOString(),
        endDate: new Date(formVal.edate).toISOString(),
        priority: formVal.priority
    };
    return this.http.post(this.baseUrl + 'task/create', payload);
  }

  getParentTasks() {
    return this.http.get(this.baseUrl + '/parent/list').map(res => res.json());
  }
}
