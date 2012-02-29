package com.sundp.search.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.sundp.DataTransfer;
import com.sundp.handle.MutiHandle;

public class calcuAction extends ActionSupport{

	private static final long serialVersionUID = -1870280854086100789L;
	
	private List<String> docsList;

	public Map<String, Double> idf;

	public Map<String, Map<Integer, Double>> terms;

	public Map<Integer, Map<String, Double>> docTerms;
	
	private Map<Integer, Map<String, Double>> docTermFreq;
	
	DataTransfer data =new MutiHandle();
	
	private long time;
	
	public String calcu() {
		time = System.currentTimeMillis();
		Map<Integer,String> source = new LinkedHashMap<Integer, String>();
		if(docsList!=null){
			for (int i = 0; i < docsList.size(); i++) {
				source.put(i, docsList.get(i));
			}
			data.runString(source);
			docTermFreq = data.getDocTermFreq();
			idf = data.getIdf();
			terms= data.getTerms();
			docTerms = data.getDocTerms();
		}
		time = System.currentTimeMillis() - time;
		return "calcu";
	}
	
	public Map<String, Double> getIdf() {
		return idf;
	}

	public Map<String, Map<Integer, Double>> getTerms() {
		return terms;
	}

	public Map<Integer, Map<String, Double>> getDocTerms() {
		return docTerms;
	}

	public Map<Integer, Map<String, Double>> getDocTermFreq() {
		return docTermFreq;
	}
	
	public List<String> getDocsList() {
		return docsList;
	}

	public void setDocsList(List<String> docsList) {
		this.docsList = docsList;
	}

	public long getTime() {
		return time;
	}

}
