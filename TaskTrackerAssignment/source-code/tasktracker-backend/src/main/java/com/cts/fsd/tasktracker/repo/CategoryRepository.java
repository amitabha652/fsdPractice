package com.cts.fsd.tasktracker.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.fsd.tasktracker.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	
	@Transactional
    @Modifying
    @Query("delete from Category category where category.categoryId=:id")
    public void deleteCategoryById(@Param("id") Long categoryId);
}
