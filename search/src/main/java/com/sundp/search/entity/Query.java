package com.sundp.search.entity;

public class Query {
	 
	public Query(String term, Double value) {
		super();
		this.term = term;
		this.value = value;
	}

	public Query() {
		super();
	}

	private String term;
	 
	private Double value;

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
