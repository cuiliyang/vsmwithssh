package com.sundp.entity;

public class DocInfo {

	private Integer docId;

	private Integer wcount;

	private Integer maxcount;

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public Integer getWcount() {
		return wcount;
	}

	public void setWcount(Integer wcount) {
		this.wcount = wcount;
	}

	public Integer getMaxcount() {
		return maxcount;
	}

	public void setMaxcount(Integer maxcount) {
		this.maxcount = maxcount;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.docId == ((DocInfo) obj).docId)
			return true;
		return false;
	}
}
