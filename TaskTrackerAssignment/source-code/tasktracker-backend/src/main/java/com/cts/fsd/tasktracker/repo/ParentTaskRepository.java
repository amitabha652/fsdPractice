package com.cts.fsd.tasktracker.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.fsd.tasktracker.entity.ParentTaskEntity;


@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTaskEntity, Long>{
	
	
	@Transactional
    @Modifying
    @Query("delete from ParentTaskEntity p where p.parentId=:id")
    public void deleteParentTaskById(@Param("id") Long parentId);
}
