package com.apress.spring.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Journal {
	private Long id;
	private String title;
	private Date created;
	private String summary;
	
	private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	public Journal(Long id, String title, String summary, Date created) {
		this.id = id;
		this.title = title;
		this.created = created;
		this.summary = summary;
	}
	
	public Journal() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getCreatedAsShort() {
		return format.format(created);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("* Journal (");
		builder.append("Id :");
		builder.append(id);
		builder.append("Title :");
		builder.append(title);
		builder.append("Summary :");
		builder.append(summary);
		builder.append(",Created :");
		builder.append(getCreatedAsShort());
		builder.append(")");
		return builder.toString();
	}
	

}
