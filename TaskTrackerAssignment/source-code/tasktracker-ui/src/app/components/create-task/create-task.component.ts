import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css']
})
export class CreateTaskComponent implements OnInit {
  parentTasks: any;
  myForm: FormGroup;
  submitted: boolean = false;
  maxPriority: number = 30;
  minPriority: number = 0;

  constructor() { }

  ngOnInit() {
    this.parentTasks = [{
      parentId: 101,
      parentTask: "ParentTask1"
    },
    {
      parentId: 102,
      parentTask: "ParentTask2"
    },
    {
      parentId: 103,
      parentTask: "ParentTask3"
    }];

    this.myForm = new FormGroup({
      task: new FormControl('', [ Validators.required, Validators.maxLength(20),  Validators.minLength(2) ]),
      priority: new FormControl(0, [ Validators.required ]),
      parentTask: new FormControl('', [ Validators.required, Validators.maxLength(20),  Validators.minLength(2) ]),
      sdate: new FormControl('', Validators.required),
      edate: new FormControl('', Validators.required)
    });
  }
  onSubmit() {
    this.submitted = true;
    console.log(this.myForm);
  }

}
