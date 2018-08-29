package com.cts.fsd.tasktracker.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TaskEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long taskId;
	
	private long parentId;
	
	private String task;
	
	private String startDate;
	
	private String endDate;
	
	private String priority;
	
	

}
