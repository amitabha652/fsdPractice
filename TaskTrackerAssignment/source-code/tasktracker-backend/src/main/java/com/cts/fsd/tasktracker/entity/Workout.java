package com.cts.fsd.tasktracker.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Workout implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long workoutId;
	
	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "categoryId" )
    private Category category;
	
	private String workoutTitle;
	
	private String workoutNote;
	
	private String workoutCalories;

	public long getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(long workoutId) {
		this.workoutId = workoutId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
		return "WorkoutEntity ["
				+ "workoutId=" + workoutId + ", category=" + category.toString()
				+ ", workoutTitle=" + workoutTitle + ", workoutNote="
				+ workoutNote + ", workoutCalories=" + workoutCalories + "]";
	}
	
	
	
	

}
