package com.cts.fsd.entity;

import java.io.Serializable;

public class SubjectEntity implements Serializable {
	
	/**
	 * Added serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private long subjectId;
	
	private String subtitle;
	
	private int durationInHours;
	
	private long bookId;

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

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	@Override
	public String toString() {
		return "SubjectEntity [subjectId=" + subjectId + ", subtitle="
				+ subtitle + ", durationInHours=" + durationInHours
				+ ", bookId=" + bookId + "]";
	}
	
}
