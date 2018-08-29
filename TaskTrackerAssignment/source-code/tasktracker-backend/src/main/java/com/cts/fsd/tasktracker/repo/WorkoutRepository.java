package com.cts.fsd.tasktracker.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fsd.tasktracker.entity.Workout;


@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long>{
	
}
