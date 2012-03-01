package com.sundp;

import java.util.List;
import java.util.Map;

public interface DataTransfer {
	
	/**
	 * 构造方法1：设置文档集
	 * @param fileSet 文件路径
	 */
	public void init(List<String> fileSet);
	
	/**
	 * 构造方法2：传递文档内容Map<文档编号、文档内容>
	 * @param sourceDocs
	 */
	public void init(Map<Integer,String> sourceDocs);
	
	/***************** 获取结果 *******************/
	/**
	 * 获取文档集
	 * @return
	 */
	public Map<Integer, String> getDocs();

	/**
	 * 获取IDF表
	 * @return
	 */
	public Map<String, Double> getIdf();

	/**
	 * 获取TF表
	 * @return
	 */
	public Map<Integer, Map<String, Double>> getDocTermFreq();
	
	/**
	 * 获取倒置文档权重表
	 * @return
	 */
	public Map<String, Map<Integer, Double>> getTerms();

	/**
	 * 获取正向文档权重表
	 * @return
	 */
	public Map<Integer, Map<String, Double>> getDocTerms();
}
