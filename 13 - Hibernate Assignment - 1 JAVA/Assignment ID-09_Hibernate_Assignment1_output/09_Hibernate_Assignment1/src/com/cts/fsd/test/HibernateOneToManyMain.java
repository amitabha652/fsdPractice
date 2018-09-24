package com.cts.fsd.test;

import java.text.SimpleDateFormat;
import java.util.LinkedHashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cts.fsd.entity.BookEntity;
import com.cts.fsd.entity.SubjectEntity;
import com.cts.fsd.hibernate.util.HibernateUtil;

public class HibernateOneToManyMain {

	public static void main(String[] args) throws Exception {
		Set<BookEntity> setHindiLiteratureBooks = new LinkedHashSet<BookEntity>();
		Set<BookEntity> setGeographyPrimaryBooks = new LinkedHashSet<BookEntity>();
		
		BookEntity bookEntity1 = new BookEntity();
		bookEntity1.setTitle("Hindi Prose-II");
		bookEntity1.setPrice(88);
		bookEntity1.setVolume(1);
		bookEntity1.setPublishDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1999-05-04").getTime()));
		
		BookEntity bookEntity2 = new BookEntity();
		bookEntity2.setTitle("Hindi Prose-I");
		bookEntity2.setPrice(77);
		bookEntity2.setVolume(2);
		bookEntity2.setPublishDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1987-07-01").getTime()));
		
		
		
		BookEntity bookEntity3 = new BookEntity();
		bookEntity3.setTitle("Geography for Newbies Vol.I");
		bookEntity3.setPrice(113);
		bookEntity3.setVolume(1);
		bookEntity3.setPublishDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1998-09-01").getTime()));
		
		BookEntity bookEntity4 = new BookEntity();
		bookEntity4.setTitle("Geography for Beginers vol2");
		bookEntity4.setPrice(79);
		bookEntity4.setVolume(2);
		bookEntity4.setPublishDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse("1999-07-01").getTime()));
		
		
		
		SubjectEntity subjectEntity1 = new SubjectEntity();
		subjectEntity1.setSubtitle("Hindi-Literature");
		subjectEntity1.setDurationInHours(100);
		
		bookEntity1.setSubjectEntity(subjectEntity1);
		bookEntity2.setSubjectEntity(subjectEntity1);
		setHindiLiteratureBooks.add(bookEntity1);
		setHindiLiteratureBooks.add(bookEntity2);
		subjectEntity1.setReferences(setHindiLiteratureBooks);

		SubjectEntity subjectEntity2 = new SubjectEntity();
		subjectEntity2.setSubtitle("Geography-Primary");
		subjectEntity2.setDurationInHours(50);
		
		bookEntity3.setSubjectEntity(subjectEntity2);
		bookEntity4.setSubjectEntity(subjectEntity2);
		setGeographyPrimaryBooks.add(bookEntity3);
		setGeographyPrimaryBooks.add(bookEntity4);
		subjectEntity1.setReferences(setGeographyPrimaryBooks);
		
		
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try{
		//Get Session
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		
		System.out.println("Session created");
		
		//start transaction
		tx = session.beginTransaction();
		
		//Save the Model objects
		session.save(subjectEntity1);
		session.save(subjectEntity2);
		session.save(bookEntity1);
		session.save(bookEntity2);
		session.save(bookEntity3);
		session.save(bookEntity4);
		
		//Commit transaction
		tx.commit();
		System.out.println("subjectEntity1 ID = " + subjectEntity1.getSubjectId());
		System.out.println("subjectEntity2 ID = " + subjectEntity2.getSubjectId());
		
		}catch(Exception e){
			System.out.println("Exception occured. "+e.getMessage());
			e.printStackTrace();
		}finally{
			if(!sessionFactory.isClosed()){
				System.out.println("Closing SessionFactory");
				sessionFactory.close();
			}
		}
	}}
