package com.sundp.entity;

public interface DocIndexOperate {

	/**
	 * 增加条目
	 * @param doc
	 * @param term
	 */
	public void addDocTerm(DocInfo doc, TermInfo term);

	/**
	 * 获取词条出现次数
	 * @param term
	 * @return
	 *//*
	public int getTermFerqCount(TermInfo term);
	
	*//**
	 * 获取词条出现的文档数
	 * @param term
	 * @return
	 *//*
	public int getTermDocCount(TermInfo term);*/
}
