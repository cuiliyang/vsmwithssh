package com.sundp.search.action;

import java.util.LinkedHashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.sundp.search.service.IAnalyzerService;
import com.sundp.search.service.impl.AnalyzerService;

public class analyzerAction extends ActionSupport {

	private static final long serialVersionUID = -8029228232251237118L;

	private String content = "";

	private Map<String, Integer> result = new LinkedHashMap<String, Integer>();

	private final IAnalyzerService a = new AnalyzerService();

	private long time;

	public String analyzer() {
		time = System.currentTimeMillis();
		result = a.getResult(content);
		time = System.currentTimeMillis() - time;
		return "analyzer";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, Integer> getResult() {
		return result;
	}

	public long getTime() {
		return time;
	}
}
