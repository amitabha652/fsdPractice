package com.cts.fsd.tasktracker.pojo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AllWorkoutsPOJO {
	List<WorkoutPOJO> workoutResponse;

	public List<WorkoutPOJO> getWorkoutResponse() {
		return workoutResponse;
	}

	public void setWorkoutResponse(List<WorkoutPOJO> workoutResponse) {
		this.workoutResponse = workoutResponse != null ? workoutResponse : new ArrayList<WorkoutPOJO>();
	}

	@Override
	public String toString() {
		return "AllWorkoutsPOJO [workoutResponse=" + workoutResponse + "]";
	}
	
}
