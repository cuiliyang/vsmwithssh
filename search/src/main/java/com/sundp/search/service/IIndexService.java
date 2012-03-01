package com.sundp.search.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sundp.search.entity.Query;

public interface IIndexService {

	public String out();

	public Map<String, Double> getQueryList(String query);

	public Map<Integer, Double> getResult(Map<String, Double> queryList);

	public Map<String, Double> getQueryList(Map<String, Double> queryList,
			Set<Integer> relationDocs, Set<Integer> unRelationDocs);

	public String getDocument(Integer doc);

	public Set<String> getQueryTermSet(String query);
	
	public Map<Integer, String> getDocs();
	
	public List<Query> getQueryLists(Map<String, Double> queryList);

	public Map<String, Double> getQueryList(List<Query> queryLists);

}
