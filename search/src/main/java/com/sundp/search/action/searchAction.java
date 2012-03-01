package com.sundp.search.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;
import com.sundp.search.entity.Query;
import com.sundp.search.service.IIndexService;
import com.sundp.search.service.impl.IndexService;

public class searchAction extends ActionSupport {

	private static final long serialVersionUID = 7439491799699265473L;
	private IIndexService indexService = new IndexService();
	private String out;
	private String queryString;
	private Map<Integer, Double> result;
	private Map<Integer, Double> oldresult;
	private Map<String, Double> queryList;
	private List<Query> queryLists;
	private Map<String, Double> oldqueryList;
	private final Map<Integer, String> docs = indexService.getDocs();
	private Set<Integer> relationDocs;
	private Set<Integer> unRelationDocs;
	private long time;

	public String search() {
		time = System.currentTimeMillis();
		queryList = indexService.getQueryList(queryString);
		result = indexService.getResult(queryList);
		queryLists = indexService.getQueryLists(queryList);
		relationDocs = null;
		unRelationDocs = null;
		time = System.currentTimeMillis() - time;
		return "search";
	}

	public String relevanceSearch() {
		time = System.currentTimeMillis();
		queryList = indexService.getQueryList(queryLists);
		oldresult = indexService.getResult(queryList);
		queryList = indexService.getQueryList(queryList, relationDocs,
				unRelationDocs);
		result = indexService.getResult(queryList);
		queryLists = indexService.getQueryLists(queryList);
		relationDocs = null;
		unRelationDocs = null;
		time = System.currentTimeMillis() - time;
		return "search";
	}

	public String fist() {
		System.out.println("这是Action中的输出：" + indexService.out());
		out = indexService.out();
		return "index";
	}

	public IIndexService getIndexService() {
		return indexService;
	}

	public void setIndexService(IIndexService indexService) {
		this.indexService = indexService;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public String getOut() {
		return out;
	}

	public Map<Integer, Double> getResult() {
		return result;
	}

	public void setResult(Map<Integer, Double> result) {
		this.result = result;
	}

	public Map<Integer, Double> getOldresult() {
		return oldresult;
	}

	public void setOldresult(Map<Integer, Double> oldresult) {
		this.oldresult = oldresult;
	}

	public Map<String, Double> getQueryList() {
		return queryList;
	}

	public void setQueryList(Map<String, Double> queryList) {
		this.queryList = queryList;
	}

	public List<Query> getQueryLists() {
		return queryLists;
	}

	public void setQueryLists(List<Query> queryLists) {
		this.queryLists = queryLists;
	}

	public Map<String, Double> getOldqueryList() {
		return oldqueryList;
	}

	public void setOldqueryList(Map<String, Double> oldqueryList) {
		this.oldqueryList = oldqueryList;
	}

	public Set<Integer> getRelationDocs() {
		return relationDocs;
	}

	public void setRelationDocs(Set<Integer> relationDocs) {
		this.relationDocs = relationDocs;
	}

	public Set<Integer> getUnRelationDocs() {
		return unRelationDocs;
	}

	public void setUnRelationDocs(Set<Integer> unRelationDocs) {
		this.unRelationDocs = unRelationDocs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map<Integer, String> getDocs() {
		return docs;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public long getTime() {
		return time;
	}
}
