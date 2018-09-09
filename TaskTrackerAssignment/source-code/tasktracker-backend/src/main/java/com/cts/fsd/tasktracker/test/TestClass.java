/**
 * 
 */
package com.cts.fsd.tasktracker.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.cts.fsd.tasktracker.pojo.ParentTaskPOJO;
import com.cts.fsd.tasktracker.pojo.TaskPOJO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author Amitabha Das Local
 *
 */
public class TestClass {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		
		ParentTaskPOJO parentTaskPOJO = new ParentTaskPOJO();
		parentTaskPOJO.setParentId(101);
		parentTaskPOJO.setParentTask("ParentTaskName");
		
		TaskPOJO taskPOJO = new TaskPOJO();
		taskPOJO.setTaskId(1);
		taskPOJO.setTask("TaskName");
		taskPOJO.setParentId(101);
		taskPOJO.setParentTask("ParentTaskName");
		taskPOJO.setPriority(1);
		taskPOJO.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse("09/09/2018"));
		taskPOJO.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse("09/09/2018"));
		
		
		String jsonString = gson.toJson(parentTaskPOJO);
		System.out.println("jsonString = " + jsonString);
		
		
		List<TaskPOJO> taskPOJOList1 = new ArrayList<TaskPOJO>();
		taskPOJOList1.add(taskPOJO);
		String jsonString2 = gson.toJson(taskPOJOList1);
		System.out.println("jsonString2 = " + jsonString2);
		
		
		
		
		String parentTaskDump = "[{\"parentId\":101,\"parentTask\":\"ParentTask1\"},{\"parentId\":102,\"parentTask\":\"ParentTask2\"},{\"parentId\":103,\"parentTask\":\"ParentTask3\"},{\"parentId\":104,\"parentTask\":\"ParentTask4\"},{\"parentId\":105,\"parentTask\":\"ParentTask5\"},{\"parentId\":106,\"parentTask\":\"ParentTask6\"},{\"parentId\":107,\"parentTask\":\"ParentTask7\"}]";
		List<ParentTaskPOJO> parentTaskPOJOList = gson.fromJson(parentTaskDump, new TypeToken<List<ParentTaskPOJO>>(){}.getType());
		parentTaskPOJOList.forEach((pojo)-> {System.out.println(pojo);});
		
//		jsonString2 = jsonString2.replaceAll("\"", "\\\\\"");
//		System.out.println("jsonString2.replaceAll() = " + jsonString2);
		
		
		String taskDump = "[{\"taskId\":11,\"task\":\"TaskName1\",\"parentId\":101,\"parentTask\":\"ParentTaskName1\",\"startDate\":\"Sep 9, 2018 12:00:00 AM\",\"endDate\":\"Sep 9, 2018 12:00:00 AM\",\"priority\":1},{\"taskId\":12,\"task\":\"TaskName2\",\"parentId\":102,\"parentTask\":\"ParentTaskName2\",\"startDate\":\"Sep 9, 2018 12:00:00 AM\",\"endDate\":\"Sep 9, 2018 12:00:00 AM\",\"priority\":2},{\"taskId\":13,\"task\":\"TaskName3\",\"parentId\":101,\"parentTask\":\"ParentTaskName1\",\"startDate\":\"Sep 9, 2018 12:00:00 AM\",\"endDate\":\"Sep 9, 2018 12:00:00 AM\",\"priority\":3},{\"taskId\":14,\"task\":\"TaskName4\",\"parentId\":103,\"parentTask\":\"ParentTaskName3\",\"startDate\":\"Sep 9, 2018 12:00:00 AM\",\"endDate\":\"Sep 9, 2018 12:00:00 AM\",\"priority\":4},{\"taskId\":15,\"task\":\"TaskName5\",\"parentId\":105,\"parentTask\":\"ParentTaskName5\",\"startDate\":\"Sep 9, 2018 12:00:00 AM\",\"endDate\":\"Sep 9, 2018 12:00:00 AM\",\"priority\":5}]";
		List<TaskPOJO> taskPOJOList = gson.fromJson(taskDump, new TypeToken<List<TaskPOJO>>(){}.getType());
		taskPOJOList.forEach((pojo)-> {System.out.println(pojo);});
	}

}
