import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ApiService } from '../../shared/services/api.service';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css'],
  providers: [ApiService]
})
export class CreateTaskComponent implements OnInit {
  parentTasks: any;
  myForm: FormGroup;
  submitted: boolean = false;
  maxPriority: number = 30;
  minPriority: number = 0;

  constructor(private apiService: ApiService) { }

  ngOnInit() {
   this.apiService.getParentTasks().subscribe(data => {
    this.parentTasks = this.transform(JSON.parse(data['_body']));
    });

    this.myForm = new FormGroup({
      task: new FormControl('', [ Validators.required, Validators.maxLength(20),  Validators.minLength(2) ]),
      priority: new FormControl(0, [ Validators.required ]),
      parentTask: new FormControl('', [ Validators.required, Validators.maxLength(20),  Validators.minLength(2) ]),
      sdate: new FormControl('', Validators.required),
      edate: new FormControl('', Validators.required)
    });
  }

  transform(response) {
    const result = [];
    for (var key in response) {
      result.push(response[key]);
    }
    return result;
  }

  onSubmit() {
    this.submitted = true;
    console.log(this.myForm);
    this.apiService.createForm(this.myForm).subscribe();
  }

}
