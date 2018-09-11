import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class ApiService {
  baseUrl: string

  constructor(private http: Http) {
    this.baseUrl = 'http://localhost:8080/tasktrackerbackend/';
   }

  createForm(form) {
    const payload = {
        taskId: 17,
        task: "TaskName71111",
        parentId: 105,
        startDate: "2018-09-08T18:30:00.000+0000",
        endDate: "2018-09-08T18:30:00.000+0000",
        priority: 10
    };
    return this.http.post(this.baseUrl + 'task/create', payload);
  }

  getParentTasks() {
    return this.http.get(this.baseUrl + '/parent/list');
  }
}
