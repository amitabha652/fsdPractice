package com.cts.fsd.tasktracker.pojo;

import org.springframework.stereotype.Component;

@Component
public class WorkoutPOJO {
	private int workoutId;

	private int categoryId;

	private String workoutTitle;

	private String workoutNote;

	private String workoutCalories;

	public int getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(int workoutId) {
		this.workoutId = workoutId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getWorkoutTitle() {
		return workoutTitle;
	}

	public void setWorkoutTitle(String workoutTitle) {
		this.workoutTitle = workoutTitle;
	}

	public String getWorkoutNote() {
		return workoutNote;
	}

	public void setWorkoutNote(String workoutNote) {
		this.workoutNote = workoutNote;
	}

	public String getWorkoutCalories() {
		return workoutCalories;
	}

	public void setWorkoutCalories(String workoutCalories) {
		this.workoutCalories = workoutCalories;
	}

	@Override
	public String toString() {
		return "WorkoutPOJO [workoutId=" + workoutId + ", categoryId="
				+ categoryId + ", workoutTitle=" + workoutTitle
				+ ", workoutNote=" + workoutNote + ", workoutCalories="
				+ workoutCalories + "]";
	}
	
	
}
