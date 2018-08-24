package com.cts.fsd.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.cts.fsd.controller.ApplicationController;
import com.cts.fsd.dto.BookDTO;
import com.cts.fsd.dto.SubjectDTO;
import com.cts.fsd.exception.CustomException;
import com.cts.fsd.util.ApplicationUtility;


public class Assignment8Main {

	final static String RESOURCES_FOLDER = "./src/resources/requirements/";
	
	static String[] argsToMainMethod = {};

	public static void main(String[] args) {
		Assignment8Main mainObj = new Assignment8Main();
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
					ApplicationUtility.displayOptionReceived(inputStr);
					{
						ApplicationUtility.displayHorizontalLine();
						System.out.println("Operation - Add a Subject");
						ApplicationUtility.displayHorizontalLine();
						SubjectDTO subjectDTO = new SubjectDTO();
						subjectDTO = getSubjectFromUser(buffer);
						
						ApplicationController.addSubject(subjectDTO);
					}
					break;
	
				case "b":
					ApplicationUtility.displayOptionReceived(inputStr);
					{
						ApplicationUtility.displayHorizontalLine();
						System.out.println("Operation - Add a Book");
						ApplicationUtility.displayHorizontalLine();
						
						BookDTO bookDTO = null;
						bookDTO = getBookFromUser(buffer);
						ApplicationController.addBook(bookDTO);
					}
					break;
	
				case "c":
					ApplicationUtility.displayOptionReceived(inputStr);
					{
						ApplicationUtility.displayHorizontalLine();
						System.out.println("Operation - Delete a Subject");
						ApplicationUtility.displayHorizontalLine();
//						TODO: ApplicationController.deleteSubject(buffer);
					}
					break;
	
				case "d":
					ApplicationUtility.displayOptionReceived(inputStr);
					{
						ApplicationUtility.displayHorizontalLine();
						System.out.println("Operation - Delete a Book");
						ApplicationUtility.displayHorizontalLine();
//						TODO: ApplicationController.deleteBook(buffer);
					}
					break;
	
				case "e":
					ApplicationUtility.displayOptionReceived(inputStr);
					{
						ApplicationUtility.displayHorizontalLine();
						System.out.println("Operation - Search a Book");
						ApplicationUtility.displayHorizontalLine();
//						TODO: ApplicationController.searchBook(buffer);
					}
					break;
	
				case "f":
					ApplicationUtility.displayOptionReceived(inputStr);
					{
						ApplicationUtility.displayHorizontalLine();
						System.out.println("Operation - Search a Subject");
						ApplicationUtility.displayHorizontalLine();
//						TODO: ApplicationController.searchSubject(buffer);
					}
					break;
				
				case "g":
					ApplicationUtility.displayOptionReceived(inputStr);
					System.out.println("\nExiting the program...");
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

	
	private static SubjectDTO getSubjectFromUser(BufferedReader buffer) throws IOException , NumberFormatException {
		SubjectDTO subjectDTO = null;
		String inputStr = null;
		
		List<BookDTO> bookDTOList = null;
		Set<BookDTO> references = null;
		
		String[] bookIdArray = null;
		String booksNotAvailable = "";
		boolean booksFound = false;
		
		try {
			subjectDTO = new SubjectDTO();
			
			// 1. Enter the Subject details :: 
			System.out.println("Enter the Subject details :: ");
			do {
				System.out.print("Enter the Subject Id (only numbers) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("[0-9]{1,}"));
			subjectDTO.setSubjectId(Long.parseLong(inputStr));

			// 2. Enter the Subject sub-title :: 
			System.out.print("Enter the Subject sub-title :: ");
			inputStr = buffer.readLine();
			inputStr = inputStr.trim();
			subjectDTO.setSubtitle(inputStr);
			
			// 3. Enter the Subject Duration in Hours (only numbers) :: 
			do {
				System.out.print("Enter the Subject Duration in Hours (only numbers) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("[0-9]{1,}"));
			subjectDTO.setDurationInHours(Integer.parseInt(inputStr));
			
			
			System.out.println("Select the Book references(Book Id) from the menu below (Seperated by semi-colon \";\") :: ");
			bookDTOList = ApplicationController.getAllBooks();
			if (bookDTOList != null) {
				System.out.println("Book Details : ");
				System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "ID" , "TITLE" , "PRICE" , "VOLUME" , "PUBLISH DATE"));
				System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "------" , "--------------------" , "------" , "----------" , "------------"));
				
				bookDTOList.forEach(bookDTO -> System.out.println(
						String.format("%6s\t%20s\t%6s\t%10s\t%s", 
						bookDTO.getBookId(),
						bookDTO.getTitle(),
						bookDTO.getPrice(),
						bookDTO.getVolume(),
						bookDTO.getPublishDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
						));
				
				do {
					System.out.print("Enter Book ID (only numbers seperated by semi-colon \";\") :- ");
					inputStr = buffer.readLine();
					inputStr = inputStr.trim();
					
					if (inputStr.matches("[0-9;]{1,}")) {
						booksNotAvailable = "";
						bookIdArray = inputStr.split(";");
						for(int i=0; i < bookIdArray.length; i++){
							booksFound = false;
							for(BookDTO bookDTO : bookDTOList) {
								if(Long.parseLong(bookIdArray[i]) == bookDTO.getBookId()) {
									booksFound = true;
								}
							}
							if (!booksFound) {
								booksNotAvailable += ""+bookIdArray[i] + "  " ;
							}
						}
						if (!booksNotAvailable.equals("")) {
							System.out.println("Book id's that are not found = [ "+booksNotAvailable+"], please select correct books again...");
							inputStr = "loopAgain";
						}
					}
					
				} while (!inputStr.matches("[0-9;]{1,}"));
				
				
				references = new LinkedHashSet<BookDTO>();
				
				for(int i=0; i < bookIdArray.length; i++){
					for(BookDTO bookDTO : bookDTOList) {
						if(Long.parseLong(bookIdArray[i]) == bookDTO.getBookId()) {
							references.add(bookDTO);
						}
					}
				}
			} else {
				references = null;
			}
			subjectDTO.setReferences(references);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStr = null;
		}
		
		return subjectDTO;
	}
	private static BookDTO getBookFromUser(BufferedReader buffer) throws IOException , NumberFormatException {
		BookDTO bookDTO = null;
		String inputStr = null;
		
		try {
			bookDTO = new BookDTO();
			System.out.println("Enter details of Book to be added...");
			// 1. Enter the Book Id (only numbers) :: 
			do {
				System.out.print("Enter the Book Id (only numbers) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("[0-9]{1,}"));
			bookDTO.setBookId(Long.parseLong(inputStr));
			
			// 2. Enter the Book title :: 
			System.out.print("Enter the Book title :: ");
			inputStr = buffer.readLine();
			inputStr = inputStr.trim();
			bookDTO.setTitle(inputStr);
			
			// 3. Enter the Book price (only numbers [decimal]) :: 
			do {
				System.out.print("Enter the Book price (only numbers [decimal]) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("^[0-9]+(\\.[0-9]+)?$"));
			bookDTO.setPrice(Double.parseDouble(inputStr));
			
			// 4. Enter the Book Volume (only numbers) :: 
			do {
				System.out.print("Enter the Book Volume (only numbers) :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
			} while (!inputStr.matches("[0-9]{1,}"));
			bookDTO.setVolume(Integer.parseInt(inputStr));
			
			do {
				System.out.print("Enter the Book Publish Date (format = \"dd-MM-yyyy\") :: ");
				inputStr = buffer.readLine();
				inputStr = inputStr.trim();
				
				if (inputStr.matches("[0-3][0-9]-[0-1][0-9]-[0-9][0-9][0-9][0-9]")) {
					String[] temp = inputStr.split("-");
					if (!ApplicationUtility.isDateValid(temp[2], temp[1], temp[0])) {
						inputStr = "loopAgain";
					}
				}
				
			} while (!inputStr.matches("[0-3][0-9]-[0-1][0-9]-[0-9][0-9][0-9][0-9]") );
			bookDTO.setPublishDate(LocalDate.parse(inputStr , DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStr = null;
		}
		return bookDTO;
	}

}
