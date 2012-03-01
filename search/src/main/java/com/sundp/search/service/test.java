package com.sundp.search.service;

import java.util.Map;

import com.sundp.search.dao.IIndexDao;
import com.sundp.search.dao.impl.FileDao;
import com.sundp.search.service.impl.IndexService;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String ,Double> queryList ;
		IIndexDao dd = new FileDao();
		IIndexService ss = new IndexService(dd);
		String question = "用经济常识知识简要说明重视安全生产的经济意义";
		//String question = "从经济常识角度，说明怎样才能“用新体制、新机制振兴东北老工业基地”？";
		queryList=ss.getQueryList(question);
		Map<Integer, Double> result = ss.getResult(queryList);
		System.out.println(question);
		for (Object obj : result.keySet()) {
			System.out.println(obj+":"+result.get(obj));
		}
		//ss.getQueryList("经济公有制");
	}

}
