package com.cts.fsd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cts.fsd.dto.BookDTO;
import com.cts.fsd.dto.SubjectDTO;
import com.cts.fsd.entity.BookEntity;
import com.cts.fsd.entity.SubjectEntity;
import com.cts.fsd.mapper.ObjectMappper;
import com.cts.fsd.util.DBUtility;



public class ApplicationDAO {
	
	static ObjectMappper objectMappper = new ObjectMappper();
	
	public static List<BookDTO> getAllBooksFromDB(){
		System.out.println("Inside ApplicationDAO.getAllBooksFromDB() start");
		
		List<BookDTO> bookDTOList = null;
		BookDTO bookDTO = null;
		
		BookEntity bookEntity = null;
		Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        
        String queryString = null;
        
        try {
        	bookDTOList = new ArrayList<BookDTO>();
        	conn = DBUtility.getConnection();
        	stmt = conn.createStatement();
        	
        	queryString = "SELECT * FROM BOOK_TABLE";
        	resultSet = stmt.executeQuery(queryString);
        	
        	while (resultSet.next()) {
        		bookDTO = new BookDTO();
        		
        		bookEntity = new BookEntity();
        		bookEntity.setBookId(resultSet.getLong("bookId"));
        		bookEntity.setTitle(resultSet.getString("title"));
        		bookEntity.setPrice(resultSet.getDouble("price"));
        		bookEntity.setVolume(resultSet.getInt("volume"));
        		bookEntity.setPublishDate(resultSet.getDate("publishDate"));
        		
        		bookDTO = objectMappper.mapEntityToPojo(bookEntity);
        		
        		bookDTOList.add(bookDTO);
        	}
        	
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			queryString = null;
			try {
				DBUtility.closeConnection(conn);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
        System.out.println("Inside ApplicationDAO.getAllBooksFromDB() end");
		return bookDTOList;
	}

	public static boolean addBookToDB(BookDTO bookDTO) {
		System.out.println("Inside ApplicationDAO.addBookToDB() start");
		boolean status = false;
		
		Connection conn = null;
        PreparedStatement pstmt = null;
        String queryString = null;
        
        BookEntity bookEntity = objectMappper.mapPojoToEntity(bookDTO);
        
        try {
        	conn = DBUtility.getConnection();
        	queryString = "INSERT into BOOK_TABLE values (?, ?, ?, ?, ?);";
        	
        	pstmt = conn.prepareStatement(queryString);
        	
        	pstmt.setLong(1, bookEntity.getBookId());
        	pstmt.setString(2, bookEntity.getTitle());
        	pstmt.setDouble(3, bookEntity.getPrice());
        	pstmt.setInt(4, bookEntity.getVolume());
        	pstmt.setDate(5, bookEntity.getPublishDate());
        	
        	int updatedRows = pstmt.executeUpdate(queryString);
        	
        	if (updatedRows > 0) {
        		status = true;
			}
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			queryString = null;
			try {
				DBUtility.closeConnection(conn);
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
        System.out.println("Inside ApplicationDAO.addBookToDB() end");
		return status;
	}

	public static boolean addSubjectToDB(SubjectDTO subjectDTO) {
		System.out.println("Inside ApplicationDAO.addSubjectToDB() start");
		boolean status = false;
		Connection conn = null;
        PreparedStatement pstmt = null;
        String queryString = null;
        
        SubjectEntity subjectEntity = objectMappper.mapPojoToEntity(subjectDTO);
        
		
		
		System.out.println("Inside ApplicationDAO.addSubjectToDB() end");
		return status;
	}
}
