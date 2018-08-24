package com.cts.fsd.controller;

import java.io.BufferedReader;
import java.util.List;

import com.cts.fsd.dao.ApplicationDAO;
import com.cts.fsd.dto.BookDTO;
import com.cts.fsd.dto.SubjectDTO;



public class ApplicationController {
	BufferedReader buffer = null;
	String inputStr = null;
	
	public static void addBook(BookDTO bookDTO) {
		System.out.println("Inside ApplicationController.addBook() start");
		boolean bookAdded = ApplicationDAO.addBookToDB(bookDTO);
		if(bookAdded) {
			System.out.println("Book Added...");
		} else {
			System.out.println("Book Not Added...");
		}
		System.out.println("Inside ApplicationController.addBook() end");
	}

	public static void addSubject(SubjectDTO subjectDTO) {
		System.out.println("Inside ApplicationController.addSubject() start");
		boolean subjectAdded = ApplicationDAO.addSubjectToDB(subjectDTO);
		if(subjectAdded) {
			System.out.println("Subject Added...");
		} else {
			System.out.println("Subject Not Added...");
		}
		System.out.println("Inside ApplicationController.addSubject() end");
	}
	
	public static void deleteBook(BookDTO bookDTO) {
		System.out.println("Inside ApplicationController.deleteBook()");
	}

	public static void deleteSubject(SubjectDTO subjectDTO) {
		System.out.println("Inside ApplicationController.deleteSubject()");
	}

	public static void searchBook(BookDTO bookDTO) {
		System.out.println("Inside ApplicationController.searchBook()");
	}

	public static void searchSubject(SubjectDTO subjectDTO) {
		System.out.println("Inside ApplicationController.searchSubject()");
	}
	
	public static List<BookDTO> getAllBooks() {
		System.out.println("Inside ApplicationController.getAllBooks()");
		
		List<BookDTO> bookDTOList = ApplicationDAO.getAllBooksFromDB();
		
		return bookDTOList;
	}
	
	public static List<SubjectDTO> getAllSubjects() {
		System.out.println("Inside ApplicationController.getAllSubjects()");
		
		return null;
	}
}
