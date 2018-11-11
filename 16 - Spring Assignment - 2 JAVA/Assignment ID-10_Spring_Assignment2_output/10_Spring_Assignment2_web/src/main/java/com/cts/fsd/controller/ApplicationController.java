package com.cts.fsd.controller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cts.fsd.Book;
import com.cts.fsd.Subject;
import com.cts.fsd.util.FSDAppUtil;


@RequestMapping("/fsd")
@Controller
public class ApplicationController {
	String message = "Welcome to Spring MVC!";
	
	@Autowired
	private HttpServletRequest httpServletRequest;
 
	@RequestMapping("/subject/search")
	public ModelAndView displayAllSubjects(
			@RequestParam(value = "criteria", required = false, defaultValue = "all")
			String criteria
			) {
		System.out.println("ApplicationController :: inside displayAllSubjects()");
		buildRealContextPathOfApplication();
		
		ModelAndView modelViewObject = displaySubjects(null , criteria);
		
		return modelViewObject;
	}
	
	
	@RequestMapping("/subject/search/{searchparam}")
	public ModelAndView searchSubject(
			
			@PathVariable(value = "searchparam", required = false)
			String searchParam,
			
			@RequestParam(value = "criteria", required = false, defaultValue = "all")
			String criteria
			) {
		
		System.out.println("ApplicationController :: inside searchSubject()");
		buildRealContextPathOfApplication();
		
		ModelAndView modelViewObject = displaySubjects(searchParam , criteria);

		return modelViewObject;
	}
	
	
	
	@RequestMapping("/book/search")
	public ModelAndView displayAllBooks(
			@RequestParam(value = "criteria", required = false, defaultValue = "all")
			String criteria) {
		
		System.out.println("ApplicationController :: inside displayAllBooks()");
		buildRealContextPathOfApplication();

		ModelAndView modelViewObject = displayBooks(null , criteria);

		return modelViewObject;
	}
	

	@RequestMapping("/book/search/{searchparam}")
	public ModelAndView searchBook(
			
			@PathVariable(value = "searchparam", required = false)
			String searchParam,
			
			@RequestParam(value = "criteria", required = false, defaultValue = "all")
			String criteria) {
		
		System.out.println("ApplicationController :: inside searchBook()");
		buildRealContextPathOfApplication();

		ModelAndView modelViewObject = displayBooks(searchParam , criteria);

		return modelViewObject;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/book/delete/{bookid}")
	public ModelAndView deleteBook(
			@PathVariable(value = "bookid", required = false)
			String bookId 
			) {
		
		System.out.println("ApplicationController :: inside deleteBook()");
		buildRealContextPathOfApplication();
		
		ModelAndView modelViewObject = new ModelAndView("showBooks");
		List<Book> allBooks = (List<Book>) FSDAppUtil.readObjectFromFile("AllBooks");
		
		boolean bookFound = false;
		for (Iterator<Book> iter = allBooks.listIterator(); iter.hasNext(); ) {
			Book bookToBeDeleted = iter.next();
			if (Long.valueOf(bookToBeDeleted.getBookId()).equals(Long.valueOf(bookId))) {
				iter.remove();
				bookFound = true;
				break;
			} else {
				bookFound = false;
			}
		}
		
		if (bookFound) {
			FSDAppUtil.writeObjectToFile(allBooks , "AllBooks");
			System.out.println("Book with book id ["+bookId+"] deleted...");
		} else {
			System.out.println("Book with book id ["+bookId+"] is NOT found... Nothing Deleted");
		}
		
		List<Book> allBooksAfterDeletion = (List<Book>) FSDAppUtil.readObjectFromFile("AllBooks");
		modelViewObject.addObject("booksFromFile", allBooksAfterDeletion);
		modelViewObject.addObject("operation", "delete");
		modelViewObject.addObject("id", bookId);
		
		return modelViewObject;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/subject/delete/{subjectid}")
	public ModelAndView deleteSubject(
			@PathVariable(value = "subjectid", required = false)
			String subjectId
			) {
		
		System.out.println("ApplicationController :: inside deleteSubject()");
		buildRealContextPathOfApplication();
		
		ModelAndView modelViewObject = new ModelAndView("showSubjects");
		List<Subject> allSubjects = (List<Subject>) FSDAppUtil.readObjectFromFile("AllSubjects");
		
		boolean subjectFound = false;
		for (Iterator<Subject> iter = allSubjects.listIterator(); iter.hasNext(); ) {
			Subject subjectToBeDeleted = iter.next();
			
			if (Long.valueOf(subjectToBeDeleted.getSubjectId()).equals(Long.valueOf(subjectId))) {
				iter.remove();
				subjectFound = true;
				break;
			} else {
				subjectFound = false;
			}
			
		}
		if (subjectFound) {
			FSDAppUtil.writeObjectToFile(allSubjects , "AllSubjects");
			System.out.println("Book with subject Id ["+subjectId+"] deleted...");
		} else {
			System.out.println("Book with subject Id ["+subjectId+"] is NOT found... Nothing Deleted");
		}
		
		List<Subject> allSubjectsAfterDeletion = (List<Subject>) FSDAppUtil.readObjectFromFile("AllSubjects");
		modelViewObject.addObject("subjectsFromFile", allSubjectsAfterDeletion);
		modelViewObject.addObject("operation", "delete");
		modelViewObject.addObject("id", subjectId);
		
		return modelViewObject;
	}
	
	
	@RequestMapping("/subject/add")
	public ModelAndView addSubject(
			@ModelAttribute("contactForm")
			Subject subject) {
		
				return null;
		
	}
	
	
	@RequestMapping("/book/search")
	public ModelAndView addBook(
			@ModelAttribute("contactForm")
			String criteria) {
		
				return null;
		
	}
	
	
	
	// All Helper Methods are given below
	
	
	@SuppressWarnings("unchecked")
	private ModelAndView displaySubjects( String searchParam , String criteria ) {
		
		ModelAndView modelViewObject = new ModelAndView("showSubjects");
		List<Subject> searchedSubjects = new ArrayList<>();
		
		List<Subject> allSubjects = (List<Subject>) FSDAppUtil.readObjectFromFile("AllSubjects");
		
		String searchValue = null;

		if (null != searchParam && !searchParam.equals("")) {
			searchValue = searchParam;
		}
		
		if (null != searchValue) {
			
			if ( criteria.equalsIgnoreCase("byTitle") ) {
				for(Subject subject : allSubjects) {
					if (subject.getSubtitle().toLowerCase().contains(searchValue.toLowerCase())) {
						searchedSubjects.add(subject);
					}
				}
			}
			
			if ( criteria.equalsIgnoreCase("byId") ) {
				for(Subject subject : allSubjects) {
					if (Long.valueOf(subject.getSubjectId()).equals(Long.valueOf(searchValue))) {
						searchedSubjects.add(subject);
					}
				}
			}
			
			modelViewObject.addObject("criteria", "For Showing Subjects containing \"" + searchValue + "\" text in it");
			modelViewObject.addObject("subjectsFromFile", searchedSubjects);
		} else {
			modelViewObject.addObject("criteria", "For Showing All Subjects");
			modelViewObject.addObject("subjectsFromFile", allSubjects);
		}
		
		return modelViewObject;
	}

	@SuppressWarnings("unchecked")
	private ModelAndView displayBooks( String searchParam , String criteria ) {
		
		ModelAndView modelViewObject = new ModelAndView("showBooks");
		List<Book> searchedBooks = new ArrayList<>();
		
		List<Book> allBooks = (List<Book>) FSDAppUtil.readObjectFromFile("AllBooks");
		
		String searchValue = null;

		if (null != searchParam && !searchParam.equals("")) {
			searchValue = searchParam;
		}
		
		if (null != searchValue) {
			
			if ( criteria.equalsIgnoreCase("byTitle") ) {
				for(Book book : allBooks) {
					if (book.getTitle().toLowerCase().contains(searchValue.toLowerCase())) {
						searchedBooks.add(book);
					}
				}
			}
			
			if ( criteria.equalsIgnoreCase("byId") ) {
				for(Book book : allBooks) {
					if (Long.valueOf(book.getBookId()).equals(Long.valueOf(searchValue))) {
						searchedBooks.add(book);
					}
				}
			}
			
			modelViewObject.addObject("criteria", "For Showing Books containing \"" + searchValue + "\" text in it");
			modelViewObject.addObject("booksFromFile", searchedBooks);
		} else {
			modelViewObject.addObject("criteria", "For Showing All Books");
			modelViewObject.addObject("booksFromFile", allBooks);
		}
		
		return modelViewObject;
	}
	
	
	
	private String buildRealContextPathOfApplication() {
		
		System.out.println("ApplicationController :: inside getRealContextPathOfApplication()");

		ServletContext context = httpServletRequest.getSession().getServletContext();
		String realContextPath = context.getRealPath("/");
		
		if (null == FSDAppUtil.getRealContextPath() || FSDAppUtil.getRealContextPath().equals("")) {
			FSDAppUtil.setRealContextPath(realContextPath);
		}
		
		return FSDAppUtil.getRealContextPath();
	}
}