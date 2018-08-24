package com.cts.fsd.mapper;

import com.cts.fsd.dto.BookDTO;
import com.cts.fsd.dto.SubjectDTO;
import com.cts.fsd.entity.BookEntity;
import com.cts.fsd.entity.SubjectEntity;



public class ObjectMappper {

	public BookDTO mapEntityToPojo(BookEntity bookEntity) {
		BookDTO bookDTO = null;
		
		if(null != bookEntity) {
			bookDTO = new BookDTO();
			bookDTO.setBookId(bookEntity.getBookId());
			bookDTO.setTitle(bookEntity.getTitle());
			bookDTO.setPrice(bookEntity.getPrice());
			bookDTO.setVolume(bookDTO.getVolume());
			bookDTO.setPublishDate(bookEntity.getPublishDate().toLocalDate());
		}
		return bookDTO;
	}

	public BookEntity mapPojoToEntity(BookDTO bookDTO) {
		BookEntity bookEntity = null;
		
		if(null != bookDTO) {
			bookEntity = new BookEntity();
			bookEntity.setBookId(bookDTO.getBookId());
			bookEntity.setTitle(bookDTO.getTitle());
			bookEntity.setPrice(bookDTO.getPrice());
			bookEntity.setVolume(bookDTO.getVolume());
			bookEntity.setPublishDate(java.sql.Date.valueOf( bookDTO.getPublishDate() ));
		}
		return bookEntity;
		
	}

	public SubjectEntity mapPojoToEntity(SubjectDTO subjectDTO) {
		SubjectEntity subjectEntity = null;
		if (null != subjectDTO) {
			subjectEntity = new SubjectEntity();
			
//			TODO: subjectEntity.setBookId(bookId);
			
		}
		return subjectEntity;
	}
	
	
}
