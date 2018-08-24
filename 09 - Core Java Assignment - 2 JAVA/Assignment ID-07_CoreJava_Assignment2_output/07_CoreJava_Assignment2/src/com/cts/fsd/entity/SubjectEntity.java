package com.cts.fsd.entity;

import java.io.Serializable;
import java.util.Set;

public class SubjectEntity implements Serializable {
	
	/**
	 * Added serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private long subjectId;
	
	private String subtitle;
	
	private int durationInHours;
	
	private Set<BookEntity> references;
	
	
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public int getDurationInHours() {
		return durationInHours;
	}
	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}
	public Set<BookEntity> getReferences() {
		return references;
	}
	public void setReferences(Set<BookEntity> references) {
		this.references = references;
	}
	
	@Override
	public String toString() {
		StringBuffer bookBuffer = new StringBuffer();
		references.forEach(book -> bookBuffer.append(book.getTitle() + "; "));
		return  String.format("%6s\t%20s\t%8s\t%s" , subjectId , subtitle , durationInHours , "["+new StringBuffer().append(bookBuffer.toString().substring(0 , bookBuffer.toString().lastIndexOf("; "))).toString()+"]");
		
//		return "Subject [subjectId=" + subjectId + ", subtitle=" + subtitle
//				+ ", durationInHours=" + durationInHours + ", references="
//				+ references + "]";
	}

}
