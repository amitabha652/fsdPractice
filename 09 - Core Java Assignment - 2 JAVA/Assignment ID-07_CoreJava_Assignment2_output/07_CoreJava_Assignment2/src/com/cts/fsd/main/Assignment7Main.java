package com.cts.fsd.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.cts.fsd.exception.CustomException;


public class Assignment7Main {

	final static String RESOURCES_FOLDER = "./src/resources/requirements/";
	
	static String[] argsToMainMethod = {};

	public static void main(String[] args) {
		Assignment7Main mainObj = new Assignment7Main();
		BufferedReader buffer = null;
		String inputStr;
		boolean exitProgram = false;
		try {
			System.out.println("==========================================================");
			System.out.println("Please select one of the below options to proceed...");
			System.out.println("==========================================================");
			System.out.println("a.	Add a Subject");
			System.out.println("b.	Add a Book");
			System.out.println("c.	Delete a Subject");
			System.out.println("d.	Delete a book");
			System.out.println("e.	Search for a book");
			System.out.println("f.	Search for a subject");
			System.out.println("g.	Exit");
			System.out.println("==========================================================");
			System.out.print("Value = ");
			buffer = new BufferedReader(new InputStreamReader(System.in));
			inputStr = buffer.readLine();
			
			switch (inputStr) {
			
				case "a":
					System.out.println("Input value \"a\" received...\n\n\n");
					{
						System.out.println("==========================================================");
						System.out.println("Operation - Add a Subject");
						System.out.println("==========================================================");
						mainObj.addSubject(buffer);
					}
					break;
	
				case "b":
					System.out.println("Input value \"b\" received...\n\n\n");
					{
						System.out.println("==========================================================");
						System.out.println("Operation - Add a Book");
						System.out.println("==========================================================");
						mainObj.addBook(buffer);
					}
					break;
	
				case "c":
					System.out.println("Input value \"c\" received...\n\n\n");
					{
						System.out.println("==========================================================");
						System.out.println("Operation - Delete a Subject");
						System.out.println("==========================================================");
						mainObj.deleteSubject(buffer);
					}
					break;
	
				case "d":
					System.out.println("Input value \"d\" received...\n\n\n");
					{
						System.out.println("==========================================================");
						System.out.println("Operation - Delete a Book");
						System.out.println("==========================================================");
						mainObj.deleteBook(buffer);
					}
					break;
	
				case "e":
					System.out.println("Input value \"e\" received...\n\n\n");
					{
						System.out.println("==========================================================");
						System.out.println("Operation - Search a Book");
						System.out.println("==========================================================");
						mainObj.searchBook(buffer);
					}
					break;
	
				case "f":
					System.out.println("Input value \"f\" received...\n\n\n");
					{
						System.out.println("==========================================================");
						System.out.println("Operation - Search a Subject");
						System.out.println("==========================================================");
						mainObj.searchSubject(buffer);
					}
					break;
				
				case "g":
					System.out.println("Input value \"g\" received...\n\n\nExiting the program...");
					exitProgram = true;
					break;
				
				default:
					throw new CustomException("Invalid Input... Please try again...\n\n");
			}
			
			
			
			throw new CustomException("--------------------");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CustomException e) {
			System.out.println(e.getMessage());
			if(!exitProgram) {
				main(argsToMainMethod);
			}
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void searchBook(BufferedReader buffer) {}

	private void searchSubject(BufferedReader buffer) {}

	private void deleteBook(BufferedReader buffer) {}

	private void deleteSubject(BufferedReader buffer) {}

	private void addBook(BufferedReader buffer) {}

	private void addSubject(BufferedReader buffer) {}
	

}
