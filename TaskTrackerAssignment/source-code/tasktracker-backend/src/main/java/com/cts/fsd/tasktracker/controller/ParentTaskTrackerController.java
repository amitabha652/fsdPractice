package com.cts.fsd.tasktracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fsd.tasktracker.pojo.ParentTaskPOJO;
import com.cts.fsd.tasktracker.service.ParentTaskService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/parent")
@CrossOrigin("*")
public class ParentTaskTrackerController {
	
	@Autowired
	ParentTaskService parentTaskService;
	
	
	@RequestMapping(value = "/create/dump", method = RequestMethod.GET)
	public ResponseEntity<String> createParentTaskDump() {
		
		Gson gson = new Gson();
		String parentTaskDump = "[{\"parentId\":101,\"parentTask\":\"ParentTask1\"},{\"parentId\":102,\"parentTask\":\"ParentTask2\"},{\"parentId\":103,\"parentTask\":\"ParentTask3\"},{\"parentId\":104,\"parentTask\":\"ParentTask4\"},{\"parentId\":105,\"parentTask\":\"ParentTask5\"},{\"parentId\":106,\"parentTask\":\"ParentTask6\"},{\"parentId\":107,\"parentTask\":\"ParentTask7\"}]";
		List<ParentTaskPOJO> parentTaskPOJOList = gson.fromJson(parentTaskDump, new TypeToken<List<ParentTaskPOJO>>(){}.getType());
		
		// display ParentTask Dump in console
		parentTaskPOJOList.forEach((pojo)-> {System.out.println(pojo);});
		System.out.println("creating Parent Task dump in db = " + parentTaskPOJOList.toString());
		
		List<ParentTaskPOJO> dbResponse = parentTaskService.createParentTasks(parentTaskPOJOList);
		
		return new ResponseEntity<String>("Parent Task Dumps Saved to Database..." + dbResponse , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ParentTaskPOJO>> listParentTasks() {
		
		System.out.println("getting all the parent tasks from database...");
		
		List<ParentTaskPOJO> parentTasksFromDB = parentTaskService.getAllParentTasks();
		
		return new ResponseEntity<List<ParentTaskPOJO>>(parentTasksFromDB , HttpStatus.OK);
    }
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> createParentTask(
							@RequestBody ParentTaskPOJO parentTaskPOJO	) {
		
		System.out.println("Parent Task to be added to DB = " + parentTaskPOJO.toString());
		
		List<ParentTaskPOJO> parentTaskPOJOList = new ArrayList<ParentTaskPOJO>();
		parentTaskPOJOList.add(parentTaskPOJO);
		
		// display Parent Task To be Created in console
		parentTaskPOJOList.forEach((pojo)-> {System.out.println(pojo);});
		System.out.println("adding new Parent Task to db = " + parentTaskPOJOList.toString());
		
		List<ParentTaskPOJO> dbResponse = parentTaskService.createParentTasks(parentTaskPOJOList);
		
		return new ResponseEntity<String>("New Parent Task Saved to Database..." + dbResponse , HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> updateParentTask( 
			@PathVariable(value = "id") int parentId ,
			@RequestBody ParentTaskPOJO parentTaskPOJO	) {
		
		System.out.println("ParentTask to be updated which is coming in request = " + parentTaskPOJO.toString());
		
		
		ParentTaskPOJO dbResponse = null;
		if(parentId == parentTaskPOJO.getParentId()) {
			dbResponse = parentTaskService.editParentTaskById(parentId , parentTaskPOJO);
		}
		
		if(dbResponse != null) {
			return new ResponseEntity<String>("ParentTask[parent id = "+parentId+"] Updated in Database..." + dbResponse , HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("ParentTask[parent id = "+parentId+"] NOT Updated in Database as it does not exist..."  + dbResponse , HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteParentTask( 
			@PathVariable(value = "id") int parentId ) {

		boolean dbResponse = parentTaskService.removeParentTaskById(parentId);
		
		if(dbResponse) {
			return new ResponseEntity<String>("ParentTask[parent id = "+parentId+"] Deleted from database..." , HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("ParentTask[parent id = "+parentId+"] Not Deleted from database as it does not exist..." , HttpStatus.OK);
		}
	}
	
}
