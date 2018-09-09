package com.cts.fsd.tasktracker.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.fsd.tasktracker.entity.TaskEntity;


@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long>{

	@Transactional
    @Modifying
    @Query("delete from TaskEntity t where t.taskId=:id")
    public void deleteTaskById(@Param("id") Long taskId);
}
