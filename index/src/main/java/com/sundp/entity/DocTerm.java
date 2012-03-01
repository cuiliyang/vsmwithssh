package com.sundp.entity;

import java.util.ArrayList;


/**
 * Term描述
 * 
 * @author 孙大鹏
 */
public class DocTerm {

	// 文档
	private DocInfo doc;
	// 词条
	private TermInfo termInfo;
	// 出现位置
	private ArrayList<String> pos = new ArrayList<String>();
	// 词频
	private double freq;
	// idf值
	private double invertFreq;

	public DocInfo getDoc() {
		return doc;
	}

	public void setDoc(DocInfo doc) {
		this.doc = doc;
	}

	public TermInfo getTermInfo() {
		return termInfo;
	}

	public void setTermInfo(TermInfo termInfo) {
		this.termInfo = termInfo;
	}

	public ArrayList<String> getPos() {
		return pos;
	}

	public void setPos(ArrayList<String> pos) {
		this.pos = pos;
	}

	public double getFreq() {
		return freq;
	}

	public void setFreq(double freq) {
		this.freq = freq;
	}

	public double getInvertFreq() {
		return invertFreq;
	}

	public void setInvertFreq(double invertFreq) {
		this.invertFreq = invertFreq;
	}

}
