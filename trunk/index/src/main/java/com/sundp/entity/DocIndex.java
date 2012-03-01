package com.sundp.entity;

import java.util.LinkedHashMap;
import java.util.Map;


public class DocIndex implements DocIndexOperate{

	private Map<DocInfo, Info> docIndex = new LinkedHashMap<DocInfo, Info>();
	
	class Info {
		
		private Map<TermInfo, TermValue> termList = new LinkedHashMap<TermInfo, TermValue>();

		public void setTermList(Map<TermInfo, TermValue> termList) {
			this.termList = termList;
		}

		public Map<TermInfo, TermValue> getTermList() {
			return termList;
		}

	}
	
	public void setDocIndex(Map<DocInfo, Info> docIndex) {
		this.docIndex = docIndex;
	}

	public Map<DocInfo, Info> getDocIndex() {
		return docIndex;
	}

	@Override
	public void addDocTerm(DocInfo doc, TermInfo term) {
		Info info =new Info();
		TermValue tv = new TermValue();
		info.getTermList().put(term, tv);
		docIndex.put(doc, info);
		
	}

}
