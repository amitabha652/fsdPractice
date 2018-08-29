package com.cts.fsd.tasktracker.mapper;

import org.springframework.stereotype.Component;

import com.cts.fsd.tasktracker.entity.Category;
import com.cts.fsd.tasktracker.entity.Workout;
import com.cts.fsd.tasktracker.pojo.CategoryPOJO;
import com.cts.fsd.tasktracker.pojo.WorkoutPOJO;

@Component
public class ObjectMapper {
	
	public Category mapCategoryPojoToEntity (CategoryPOJO categoryPOJO){
		Category category = new Category();
		
		if( categoryPOJO != null ) {
			category.setCategoryId(categoryPOJO.getCategoryId());
			category.setCategoryName(categoryPOJO.getCategoryName());
		}
		
		return category;
	}
	
	public Workout mapWorkoutPojoToEntity (WorkoutPOJO workoutPOJO){
		Workout workout = new Workout();
		
		if( workoutPOJO != null ) {
			workout.setWorkoutId(workoutPOJO.getWorkoutId());
			workout.setWorkoutTitle(workoutPOJO.getWorkoutTitle());
			workout.setWorkoutNote(workoutPOJO.getWorkoutNote());
			workout.setWorkoutCalories(workoutPOJO.getWorkoutCalories());
		}
		
		return workout;
	}
	
	public CategoryPOJO mapCategoryEntityToPojo (Category category){
		CategoryPOJO categoryPOJO = new CategoryPOJO();
		
		if( category != null ) {
			categoryPOJO.setCategoryId(new Long(category.getCategoryId()).intValue());
			categoryPOJO.setCategoryName(category.getCategoryName());
		}
		
		return categoryPOJO;
	}
	
	
	public WorkoutPOJO mapWorkoutEntityToPojo (Workout workout){
		WorkoutPOJO workoutPOJO = new WorkoutPOJO();
		
		if( workout != null ) {
			workoutPOJO.setWorkoutId(new Long(workout.getWorkoutId()).intValue());
			workoutPOJO.setCategoryId((workout.getCategory() != null ? new Long(workout.getCategory().getCategoryId()).intValue() : 0));
			workoutPOJO.setWorkoutTitle(workout.getWorkoutTitle());
			workoutPOJO.setWorkoutNote(workout.getWorkoutNote());
			workoutPOJO.setWorkoutCalories(workout.getWorkoutCalories());
		}
		
		return workoutPOJO;
	}
}
