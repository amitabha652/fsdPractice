package com.cts.fsd.tasktracker.pojo;


import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TaskPOJO {
	
	private int taskId;
	
	private String task;
	
	private int parentId;

	private String parentTask;
	
	private Date startDate;
	
	private Date endDate;
	
	private int priority;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "TaskPOJO [taskId=" + taskId + ", task=" + task + ", parentId=" + parentId + ", parentTask=" + parentTask
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", priority=" + priority + "]";
	}

	
	
}
