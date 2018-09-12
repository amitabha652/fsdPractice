import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ApiService } from '../../shared/services/api.service';
import { Observable } from 'rxjs/Observable';

@Component({
	selector: 'app-update-task',
	templateUrl: './update-task.component.html',
	styleUrls: ['./update-task.component.css'],
	providers: [ApiService]
})
export class UpdateTaskComponent implements OnInit {
	parentTasks$: Observable<any>;
	myForm: FormGroup;
	submitted: boolean = false;
	maxPriority: number = 30;
	minPriority: number = 0;
	editTask: any = {};
	
	constructor(private apiService: ApiService) { }

	ngOnInit() {
		this.parentTasks$ = this.apiService.getParentTasks();


		// Hard Coded Values to check update service call
		this.editTask = this.apiService.getEditTask();
		console.log(this.editTask);



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
    this.apiService.udpateTask(this.myForm).subscribe();
  }

}
