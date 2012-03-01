package com.sundp.search.entity;

public class DocContent {

	private Integer id;
	
	private String content;

	public DocContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DocContent(Integer id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
