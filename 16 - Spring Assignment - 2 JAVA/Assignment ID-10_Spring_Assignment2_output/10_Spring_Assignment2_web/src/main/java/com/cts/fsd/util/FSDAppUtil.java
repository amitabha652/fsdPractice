package com.cts.fsd.util;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.cts.fsd.Book;
import com.cts.fsd.Subject;
import com.cts.fsd.exception.CustomException;


@SuppressWarnings("unchecked")
public class FSDAppUtil {
	
	private static String realContextPath;
	
	public static String getRealContextPath() {
		return realContextPath;
	}

	public static void setRealContextPath(String realContextPath) {
		FSDAppUtil.realContextPath = realContextPath;
	}
	
	final static String RESOURCE_LOCATION = File.separator + "WEB-INF" + File.separator + "classes" + File.separator;

//	static AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	
	@Autowired
	Book newBook;
	
	@Autowired
	Subject newSubject;
	
	private static FileOutputStream FOS;
	private static ObjectOutputStream OBJ_OUT;
	private static FileInputStream FIS;
	private static ObjectInputStream OBJ_IN;

	
	private void searchBook(BufferedReader buffer) {
		List<Book> allBooks;
		
		String searchString = null;
		String searchOption = null;
		
		String inputStr = null;
		boolean bookFound = false;
		List<String> inputCheckList = new ArrayList<String>();
		inputCheckList.add("1");
		inputCheckList.add("2");
		inputCheckList.add("3");
		inputCheckList.add("4");
		
		try {
			do {
				System.out.println("Select [1] to search by book id");
				System.out.println("Select [2] to search by book title");
				System.out.println("Select [3] to display all books");
				System.out.println("Select [4] to exit search...");
				inputStr = buffer.readLine();
				
				if (!inputCheckList.contains(inputStr)) {
					System.out.println("\n\nPlease select correct option...\n\n");
					inputStr = "loopAgain";
				}
			} while(!inputStr.matches("[1-4]{1}"));
			
			searchOption = inputStr;
			
			// Get All books and Display them
			allBooks = (List<Book>) readObjectFromFile("AllBooks");
			
			if (searchOption.equals("1")) {						// to search by book id
				do {
					System.out.print("Enter the Book Id to search (only numbers) ::");
					inputStr = buffer.readLine();
				} while (!inputStr.matches("[0-9]{1,}"));
				searchString = inputStr;
				System.out.println("Searching...");	
				System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "ID" , "TITLE" , "PRICE" , "VOLUME" , "PUBLISH DATE"));
				System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "------" , "--------------------" , "------" , "----------" , "------------"));
				for(Book book : allBooks) {
					if (book.getBookId() == Long.parseLong(searchString)) {
						System.out.println(book.toString());
						bookFound = true;
					}
				}
				
			} else if (searchOption.equals("2")) {				// to search by book title
				System.out.print("Enter the Book title :: ");
				inputStr = buffer.readLine();
				searchString = inputStr;
				System.out.println("Searching...");
				System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "ID" , "TITLE" , "PRICE" , "VOLUME" , "PUBLISH DATE"));
				System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "------" , "--------------------" , "------" , "----------" , "------------"));
				for(Book book : allBooks) {
					if (book.getTitle().toLowerCase().contains(searchString.toLowerCase())) {
						System.out.println(book.toString());
						bookFound = true;
					}
				}
			} else if (searchOption.equals("3")) {				// to display all books
				System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "ID" , "TITLE" , "PRICE" , "VOLUME" , "PUBLISH DATE"));
				System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "------" , "--------------------" , "------" , "----------" , "------------"));
				for(Book book : allBooks) {
					System.out.println(book.toString());
					bookFound = true;
				}
			} else if (searchOption.equals("4")) {				// to exit search
				throw new CustomException("Exiting Search Books...\n\n");
			}
			
			if (!bookFound) {
				System.out.println("\n\nBook Not found...\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CustomException e) {
			System.out.println(e.getMessage());
		}
	}

	private void searchSubject(BufferedReader buffer) {
		List<Subject> allSubjects;
		
		String searchString = null;
		String searchOption = null;
		
		String inputStr = null;
		boolean subjectFound = false;
		List<String> inputCheckList = new ArrayList<String>();
		inputCheckList.add("1");
		inputCheckList.add("2");
		inputCheckList.add("3");
		inputCheckList.add("4");
		
		
		try {
			do {
				System.out.println("Select [1] to search by subject id");
				System.out.println("Select [2] to search by subject title");
				System.out.println("Select [3] to display all subjects");
				System.out.println("Select [4] to exit search...");
				inputStr = buffer.readLine();
				
				if (!inputCheckList.contains(inputStr)) {
					System.out.println("\n\nPlease select correct option...\n\n");
					inputStr = "loopAgain";
				}
			} while(!inputStr.matches("[1-4]{1}"));
			
			searchOption = inputStr;
			
			// Get All Subjects and Display them
			allSubjects = (List<Subject>) readObjectFromFile("AllSubjects");
			
			if (searchOption.equals("1")) {						// to search by subject id
				do {
					System.out.print("Enter the Subject Id to search (only numbers) ::");
					inputStr = buffer.readLine();
				} while (!inputStr.matches("[0-9]{1,}"));
				searchString = inputStr;
				System.out.println("Searching...");
				System.out.println("Subject Details : ");
				System.out.println(String.format("%6s\t%20s\t%8s\t%s" , "ID" , "TITLE" , "Duration" , "Books To Refer"));
				System.out.println(String.format("%6s\t%20s\t%8s\t%s" , "------" , "--------------------" , "--------" , "--------------"));
				for(Subject subject : allSubjects) {
					if (subject.getSubjectId() == Long.parseLong(searchString)) {
						System.out.println(subject.toString());
						subjectFound = true;
					}
				}
				
			} else if (searchOption.equals("2")) {				// to search by subject title
				System.out.print("Enter the Subject title :: ");
				inputStr = buffer.readLine();
				searchString = inputStr;
				System.out.println("Searching...");
				System.out.println("Subject Details : ");
				System.out.println(String.format("%6s\t%20s\t%8s\t%s" , "ID" , "TITLE" , "Duration" , "Books To Refer"));
				System.out.println(String.format("%6s\t%20s\t%8s\t%s" , "------" , "--------------------" , "--------" , "--------------"));
				for(Subject subject : allSubjects) {
					if (subject.getSubtitle().toLowerCase().contains(searchString.toLowerCase())) {
						System.out.println(subject.toString());
						subjectFound = true;
					}
				}
			} else if (searchOption.equals("3")) {				// to display all subjects
				System.out.println("Subject Details : ");
				System.out.println(String.format("%6s\t%20s\t%8s\t%s" , "ID" , "TITLE" , "Duration" , "Books To Refer"));
				System.out.println(String.format("%6s\t%20s\t%8s\t%s" , "------" , "--------------------" , "--------" , "--------------"));
				for(Subject subject : allSubjects) {
					System.out.println(subject.toString());
					subjectFound = true;
				}
			} else if (searchOption.equals("4")) {				// to exit search
				throw new CustomException("Exiting Search Books...\n\n");
			}
			if (!subjectFound) {
				System.out.println("\n\nSubject Not found...\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CustomException e) {
			System.out.println(e.getMessage());
		}
	}

	private void deleteBook(BufferedReader buffer) {
		
		List<Book> allBooks;
		long bookId;
		String inputStr = null;
		boolean bookFound = false;
		
		try {
			// Get All books and Display them
			allBooks = (List<Book>) readObjectFromFile("AllBooks");
			System.out.println("Showing all existing Books...");
			System.out.println("Book Details : ");
			System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "ID" , "TITLE" , "PRICE" , "VOLUME" , "PUBLISH DATE"));
			System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "------" , "--------------------" , "------" , "----------" , "------------"));
			for(Book book : allBooks) {
				System.out.println(book.toString());
			}
			
			do {
				System.out.print("Select the Book Id to delete (only numbers) ::");
				inputStr = buffer.readLine();
			} while (!inputStr.matches("[0-9]{1,}"));
			bookId = Long.parseLong(inputStr);
			
			for (Iterator<Book> iter = allBooks.listIterator(); iter.hasNext(); ) {
				Book bookToBeDeleted = iter.next();
				
				if (bookToBeDeleted.getBookId()==bookId) {
					iter.remove();
					bookFound = true;
					break;
				} else {
					bookFound = false;
				}
				
			}
			if (bookFound) {
				writeObjectToFile(allBooks , "AllBooks");
				System.out.println("Book with book id ["+bookId+"] deleted...");
			} else {
				System.out.println("Book with book id ["+bookId+"] is NOT found... Nothing Deleted");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void deleteSubject(BufferedReader buffer) {
		List<Subject> allSubjects;
		
		long subjectId;
		String inputStr = null;
		boolean subjectFound = false;
		
		try {
			allSubjects = (List<Subject>) readObjectFromFile("AllSubjects");
			System.out.println("Showing all existing Subjects...");
			System.out.println("Subject Details : ");
			System.out.println(String.format("%6s\t%20s\t%8s\t%s" , "ID" , "TITLE" , "Duration" , "Books To Refer"));
			System.out.println(String.format("%6s\t%20s\t%8s\t%s" , "------" , "--------------------" , "--------" , "--------------"));
			for(Subject subject : allSubjects) {
				System.out.println(subject.toString());
			}
			
			do {
				System.out.print("Select the Subject Id to delete (only numbers) ::");
				inputStr = buffer.readLine();
			} while (!inputStr.matches("[0-9]{1,}"));
			subjectId = Long.parseLong(inputStr);
			
			
			for (Iterator<Subject> iter = allSubjects.listIterator(); iter.hasNext(); ) {
				Subject subjectToBeDeleted = iter.next();
				
				if (subjectToBeDeleted.getSubjectId() == subjectId) {
					iter.remove();
					subjectFound = true;
					break;
				} else {
					subjectFound = false;
				}
				
			}
			if (subjectFound) {
				writeObjectToFile(allSubjects , "AllSubjects");
				System.out.println("Book with subject Id ["+subjectId+"] deleted...");
			} else {
				System.out.println("Book with subject Id ["+subjectId+"] is NOT found... Nothing Deleted");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addBook(BufferedReader buffer) {
		List<Book> allBooks;
		
		long bookId;
		String title;
		double price;
		int volume;
		Date publishDate;
		
		String inputStr = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			System.out.println("Enter the Book details :: ");
			
			do {
				System.out.print("Enter the Book Id (only numbers) :: ");
				inputStr = buffer.readLine();
			} while (!inputStr.matches("[0-9]{1,}"));
			bookId = Long.parseLong(inputStr);
			newBook.setBookId(bookId);
			
			System.out.print("Enter the Book title :: ");
			inputStr = buffer.readLine();
			title = inputStr;
			newBook.setTitle(title);
			
			do {
				System.out.print("Enter the Book price (only numbers [decimal]) :: ");
				inputStr = buffer.readLine();
			} while (!inputStr.matches("^[0-9]+(\\.[0-9]+)?$"));
			price = Double.parseDouble(inputStr);
			newBook.setPrice(price);
			
			
			do {
				System.out.print("Enter the Book Volume (only numbers) :: ");
				inputStr = buffer.readLine();
			} while (!inputStr.matches("[0-9]{1,}"));
			volume = Integer.parseInt(inputStr);
			newBook.setVolume(volume);
			
			do {
				System.out.print("Enter the Book Publish Date (format = \"dd-MM-yyyy\") :: ");
				inputStr = buffer.readLine();
			} while (!inputStr.matches("[0-3][0-9]-[0-1][0-9]-[0-9][0-9][0-9][0-9]") && (dateFormat.format(dateFormat.parse(inputStr))).equals(inputStr));
			publishDate = dateFormat.parse(inputStr);
			newBook.setPublishDate(publishDate);
			
			// Read from Books File
			allBooks = (List<Book>) readObjectFromFile("AllBooks");
			if (allBooks != null) {
				allBooks.add(newBook);
			} else {
				allBooks = new ArrayList<Book>();
			}
			
			writeObjectToFile(allBooks , "AllBooks");
			System.out.println("Books Added = ");
			System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "ID" , "TITLE" , "PRICE" , "VOLUME" , "PUBLISH DATE"));
			System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "------" , "--------------------" , "------" , "----------" , "------------"));
			System.out.println(newBook.toString());
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addSubject(BufferedReader buffer) {
		
		List<Book> allBooks;
		List<Subject> allSubjects;
		long subjectId;
		String subtitle;
		int durationInHours;
		Set<Book> references;
		
		String[] bookIdArray = null;
		
		String booksNotAvailable = "";
		boolean booksFound = false;
		
		String inputStr = null;
		try {
			System.out.println("Enter the Subject details :: ");
			do {
				System.out.print("Enter the Subject Id (only numbers) :: ");
				inputStr = buffer.readLine();
			} while (!inputStr.matches("[0-9]{1,}"));
			subjectId = Long.parseLong(inputStr);
			newSubject.setSubjectId(subjectId);
			
			System.out.print("Enter the Subject subtitle :: ");
			inputStr = buffer.readLine();
			subtitle = inputStr;
			newSubject.setSubtitle(subtitle);
			
			do {
				System.out.print("Enter the Subject Duration in Hours (only numbers) :: ");
				inputStr = buffer.readLine();
			} while (!inputStr.matches("[0-9]{1,}"));
			durationInHours = Integer.parseInt(inputStr);
			newSubject.setDurationInHours(durationInHours);
			
			
			System.out.println("Select the Book references(Book Id) from the menu below (Seperated by semi-colon \";\") :: ");
			// Read from Books File
			allBooks = (List<Book>) readObjectFromFile("AllBooks");
			
			if (allBooks != null) {
				System.out.println("Book Details : ");
				System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "ID" , "TITLE" , "PRICE" , "VOLUME" , "PUBLISH DATE"));
				System.out.println(String.format("%6s\t%20s\t%6s\t%10s\t%s", "------" , "--------------------" , "------" , "----------" , "------------"));
				for(Book book : allBooks) {
					System.out.println(book.toString());
				}
				
				do {
					System.out.print("Enter Book ID (only numbers seperated by semi-colon \";\") :- ");
					inputStr = buffer.readLine();
					inputStr = inputStr.trim();
					if (inputStr.matches("[0-9;]{1,}")) {
						booksNotAvailable = "";
						bookIdArray = inputStr.split(";");
						for(int i=0; i < bookIdArray.length; i++){
							booksFound = false;
							for(Book book : allBooks) {
								if(Long.parseLong(bookIdArray[i]) == book.getBookId()) {
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
				
				
				references = new LinkedHashSet<Book>();
				
				for(int i=0; i < bookIdArray.length; i++){
					for(Book book : allBooks) {
						if(Long.parseLong(bookIdArray[i]) == book.getBookId()) {
							references.add(book);
						}
					}
				}
			} else {
				references = null;
			}
			newSubject.setReferences(references);
			
			// Read from Subjects File
			allSubjects = (List<Subject>) readObjectFromFile("AllSubjects");
			if(allSubjects != null) {
				allSubjects.add(newSubject);
			} else {
				allSubjects = new ArrayList<Subject>();
				allSubjects.add(newSubject);
			}
			
			writeObjectToFile(allSubjects , "AllSubjects");
			
			System.out.println("The Subject Details entered by the User is ::\n" + newSubject.toString() );
		} catch (IOException e) {
			System.out.println("IOException encountered... Please try again..." + e.getMessage());
		} finally {
			inputStr = null;
			allBooks = null;
			allSubjects = null;
		}
	}
	
	public static void writeObjectToFile(Object objectToWrite , String fileName) {
		try {
			String fileNameWithPath = getRealContextPath() + RESOURCE_LOCATION + fileName + ".ser";
			initializeFileSerializer(fileNameWithPath);
			OBJ_OUT.writeObject(objectToWrite);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				FOS.close();
				OBJ_OUT.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	public static Object readObjectFromFile(String fileName) {
		Object existingObjects = null;
		try {
			String fileNameWithPath = getRealContextPath() + RESOURCE_LOCATION + fileName + ".ser";
			initializeFileDeserializer(fileNameWithPath);
			existingObjects = OBJ_IN.readObject();
		} catch(EOFException e){
			existingObjects= null;
		} catch (IOException e) {
			existingObjects= null;
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			existingObjects= null;
			e.printStackTrace();
		} finally {
			try {
				FIS.close();
				OBJ_IN.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return existingObjects;
	}
	
	public static void initializeFileDeserializer(String fileNameWithPath) throws IOException {
		
		File file = new File(fileNameWithPath);
		if (!file.exists() || !file.canRead()) {
			initializeFileSerializer(fileNameWithPath);
			List<Book> allBooks = new ArrayList<Book>();
			List<Subject> allSubjects = new ArrayList<Subject>();
			if(fileNameWithPath.contains("AllSubjects.ser")) {
				writeObjectToFile(allSubjects, "AllSubjects");
			} else if (fileNameWithPath.contains("AllBooks.ser")) {
				writeObjectToFile(allBooks, "AllBooks");
			}
		}
		FIS = new FileInputStream(file);
		OBJ_IN = new ObjectInputStream(FIS);
	}
	
	public static void initializeFileSerializer(String fileNameWithPath) throws IOException {
		
		System.out.println("Alone fileNameWithPath = "  +  fileNameWithPath);
		FOS = new FileOutputStream(fileNameWithPath);
		OBJ_OUT = new ObjectOutputStream(FOS);
	}
}
