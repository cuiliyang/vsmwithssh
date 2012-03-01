package com.sundp.search.dao;

import java.util.Map;
import java.util.Set;

public interface IIndexDao {
	public void out();
	public Map<Integer, String> getDocs();
	public Map<String, Double> getIdf();
	public Map<String, Map<Integer, Double>> getTerms();
	public String getDocContent(Integer doc);
	public Map<String,Double> getQueryTerms(String query);
	public Set<String> getQueryList(String query);
	public Map<Integer, Map<String, Double>> getDocTerms();
}
