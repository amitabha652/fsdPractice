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

import com.cts.fsd.tasktracker.entity.Category;
import com.cts.fsd.tasktracker.entity.Workout;
import com.cts.fsd.tasktracker.mapper.ObjectMapper;
import com.cts.fsd.tasktracker.pojo.AllWorkoutsPOJO;
import com.cts.fsd.tasktracker.pojo.WorkoutPOJO;
import com.cts.fsd.tasktracker.repo.CategoryRepository;
import com.cts.fsd.tasktracker.repo.WorkoutRepository;
import com.cts.fsd.tasktracker.service.CategoryService;
import com.cts.fsd.tasktracker.service.WorkoutService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/workout")
@CrossOrigin("*")
public class WorkoutTrackerController {
	
	@Autowired
	protected ObjectMapper objectMapper;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	WorkoutRepository workoutRepository;
	
	@Autowired
	WorkoutService workoutService;

	@Autowired
	CategoryService categoryService;
	
	
	@RequestMapping(value = "/create/dump", method = RequestMethod.GET)
	public ResponseEntity<String> createWorkoutDump() {
		String allWorkoutsJsonString = "{\"workoutResponse\":[{\"workoutId\":\"1\",	\"categoryId\":\"1\",\"workoutTitle\":\"MorningRunfor5minutes\",\"workoutNote\":\"FastRun\",\"workoutCalories\":\"10\"},{\"workoutId\":\"2\",	\"categoryId\":\"2\",\"workoutTitle\":\"Boxingpracticefor10minutes\",\"workoutNote\":\"StaminaIncrease\",\"workoutCalories\":\"20\"},{\"workoutId\":\"3\",	\"categoryId\":\"3\",\"workoutTitle\":\"ChestandTricepworkoutfor10minutes\",\"workoutNote\":\"StaminaIncrease\u0026MuscleGain\",\"workoutCalories\":\"50\"},{\"workoutId\":\"4\",	\"categoryId\":4,\"workoutTitle\":\"Morningwalkfor10minutes\",\"workoutNote\":\"WarmUp\",\"workoutCalories\":\"10\"},{\"workoutId\":\"5\",	\"categoryId\":\"4\",\"workoutTitle\":\"Eveningwalkfor10minutes\",\"workoutNote\":\"CalorieBurn\",\"workoutCalories\":\"10\"}]}";
		Gson gson = new Gson();
		AllWorkoutsPOJO allWorkoutsPOJO = gson.fromJson(allWorkoutsJsonString , AllWorkoutsPOJO.class);
		
		System.out.println("creating data dump in db = " + allWorkoutsPOJO.toString());
		List<Workout> workoutsEntityList = new ArrayList<Workout>();
		
		if(allWorkoutsPOJO != null 
				&& allWorkoutsPOJO.getWorkoutResponse() != null 
				&& !allWorkoutsPOJO.getWorkoutResponse().isEmpty()) {
			
			for(WorkoutPOJO workoutPOJO : allWorkoutsPOJO.getWorkoutResponse()) {
				
				Category categoryFromDB =  categoryService.getCategoryById(workoutPOJO.getCategoryId());
				System.out.println("categoryFromDB = " + categoryFromDB.toString());
				
				Workout workout = objectMapper.mapWorkoutPojoToEntity(workoutPOJO);
				if(workout != null ) {
					workout.setCategory(categoryFromDB);
				}
				
				workoutsEntityList.add(workout);
				
			}
		}
		
		List<Workout> dbResponse = workoutService.createWorkout(workoutsEntityList);
		
		return new ResponseEntity<String>("Workouts Saved to Database..." + dbResponse , HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<AllWorkoutsPOJO> listWorkouts() {
		System.out.println("getting all the workouts from database...");
		List<Workout> workoutsFromDB = workoutService.getAllWorkouts();
		List<WorkoutPOJO> workoutList = new ArrayList<WorkoutPOJO>();
		
		if(workoutsFromDB != null) {
			for(Workout workout : workoutsFromDB) {
				WorkoutPOJO workoutPOJO = objectMapper.mapWorkoutEntityToPojo(workout);
				workoutList.add(workoutPOJO);
			}
		}
		
		AllWorkoutsPOJO allWorkoutsPOJO = new AllWorkoutsPOJO();
		allWorkoutsPOJO.setWorkoutResponse(workoutList);
		
		return new ResponseEntity<AllWorkoutsPOJO>(allWorkoutsPOJO , HttpStatus.OK);
    }
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> createWorkout(@RequestBody AllWorkoutsPOJO allWorkoutsPOJO) {
		System.out.println("allWorkouts coming in request = " + allWorkoutsPOJO.toString());
		
		List<Workout> workoutsEntityList = new ArrayList<Workout>();
		
		if(allWorkoutsPOJO != null 
				&& allWorkoutsPOJO.getWorkoutResponse() != null 
				&& !allWorkoutsPOJO.getWorkoutResponse().isEmpty()) {
			
			for(WorkoutPOJO workoutPOJO : allWorkoutsPOJO.getWorkoutResponse()) {
				
				Category categoryFromDB =  categoryService.getCategoryById(workoutPOJO.getCategoryId());
				System.out.println("categoryFromDB = " + categoryFromDB.toString());
				
				Workout workout = objectMapper.mapWorkoutPojoToEntity(workoutPOJO);
				if(workout != null ) {
					workout.setCategory(categoryFromDB);
				}
				
				workoutsEntityList.add(workout);
				
			}
		}
		
		List<Workout> dbResponse = workoutService.createWorkout(workoutsEntityList);
		
		return new ResponseEntity<String>("Workouts Saved to Database..." + dbResponse , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<String> updateWorkout(@PathVariable(value = "id") int workoutId , 
												@RequestBody WorkoutPOJO newWorkoutPOJO) {
		System.out.println("Workout to be updated which is coming in request = " + newWorkoutPOJO.toString());
		Workout dbResponse = null;
		
		if (workoutId == newWorkoutPOJO.getWorkoutId()) {
//			dbResponse = 
			// write logic in service layer and repo to update values.
		}
		
		
		
		
		
		
		
		
		
		
		
		return new ResponseEntity<String>("Workouts Saved to Database..." + dbResponse , HttpStatus.OK);
	}
	
}
