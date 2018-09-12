import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ApiService } from '../../shared/services/api.service';
import { Observable } from 'rxjs/Observable';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css'],
  providers: [ApiService]
})
export class CreateTaskComponent implements OnInit {
  parentTasks$: Observable<any>;
  myForm: FormGroup;
  submitted: boolean = false;
  maxPriority: number = 30;
  minPriority: number = 0;
  editedTaskId: number;
  taskName: any;
  parentTask: any;
  startDate: any;
  endDate: any;
  priority: any;

  constructor(
    private apiService: ApiService,
    route: ActivatedRoute) {
      this.editedTaskId = route.snapshot.params['id'];
    }

  ngOnInit() {
    this.myForm = new FormGroup({
      task: new FormControl('', [ Validators.required, Validators.maxLength(20),  Validators.minLength(2) ]),
      priority: new FormControl(0, [ Validators.required ]),
      parentTask: new FormControl('', [ Validators.required, Validators.maxLength(20),  Validators.minLength(2) ]),
      sdate: new FormControl('', Validators.required),
      edate: new FormControl('', Validators.required)
    });
    this.parentTasks$ = this.apiService.getParentTasks();
    if (this.editedTaskId) {
      this.apiService.getAllTasks().subscribe(tasks => {
        const editedTask = tasks.find(task => task.taskId.toString() === this.editedTaskId);
        this.taskName = editedTask.task;
        this.priority = editedTask.priority;
        this.parentTask = editedTask.parentId;
        this.startDate = this.getDate(editedTask.startDate);
        this.endDate =  this.getDate(editedTask.endDate);
        console.log(this.startDate);
      });
    }
  }

  onSubmit() {
    this.submitted = true;
    if (this.editedTaskId) {
      this.apiService.updateTask(this.myForm, this.editedTaskId).subscribe();
    } else {
      this.apiService.createTask(this.myForm).subscribe();
    }
  }

  getDate(isoDate) {
    const date = new Date(isoDate);
    //yyyy-MM-dd
    const month = date.getMonth() + 1;
    let newMonth = '';
    const taskDate = date.getDate();
    let newDate = '';
    if(month < 10) {
      newMonth = '0' + month;
    }

    if(taskDate < 10) {
      newDate = '0' + taskDate;
    }
    return date.getFullYear()+'-' + newMonth + '-'+newDate;
  }

}
