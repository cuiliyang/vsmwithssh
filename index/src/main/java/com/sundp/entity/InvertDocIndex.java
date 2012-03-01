/*package com.sundp.entity;

import java.util.LinkedHashMap;
import java.util.Map;


public class InvertDocIndex implements InventDocIndexOperate{
	
	private Map<TermInfo, Info> invertDocIndex = new LinkedHashMap<TermInfo, Info>();

	class Info {
		
		private Map<DocInfo, TermValue> docList = new LinkedHashMap<DocInfo, TermValue>();

		public void setDocList(Map<DocInfo, TermValue> docList) {
			this.docList = docList;
		}

		public Map<DocInfo, TermValue> getDocList() {
			return docList;
		}
	}

	public Map<TermInfo, Info> getInvertDocIndex() {
		return invertDocIndex;
	}

	public void setInvertDocIndex(Map<TermInfo, Info> invertDocIndex) {
		this.invertDocIndex = invertDocIndex;
	}

	@Override
	public void addDocTerm(DocInfo doc, TermInfo term) {
		Info info = new Info();
		TermValue tv = new TermValue();
		info.getDocList().put(doc, tv);
		invertDocIndex.put(term, info);
		
	}

//	@Override
//	public int getTermDocCount(TermInfo term) {
//		//  Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int getTermFerqCount(TermInfo term) {
//		//  Auto-generated method stub
//		return 0;
//	}
}
*/