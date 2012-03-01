package com.sundp.search.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.sundp.search.dao.IIndexDao;
import com.sundp.search.dao.impl.FileDao;
import com.sundp.search.service.impl.IndexService;

public class testRelevance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String ,Double> queryList ;
		Set<Integer> relationDocs = new HashSet<Integer>();
		Set<Integer> unRelationDocs = new HashSet<Integer>();
		IIndexDao dd = new FileDao();
		IIndexService ss = new IndexService(dd);
		String question = "用经济常识知识简要说明重视安全生产的经济意义";
		//String question = "从经济常识角度，说明怎样才能“用新体制、新机制振兴东北老工业基地”？";
		queryList=ss.getQueryList(question);
		Map<Integer, Double> result = ss.getResult(queryList);
		relationDocs.add(25);
		relationDocs.add(17);
		relationDocs.add(8);
		//relationDocs.add(25);
		unRelationDocs.add(1);
		Map<Integer, Double> result2 = ss.getResult(ss.getQueryList(queryList, relationDocs, unRelationDocs));
		System.out.println(question);
		for (Object obj : result2.keySet()) {
			System.out.println(obj+":"+result.get(obj).toString().substring(0, 4)+"->"+result2.get(obj).toString().substring(0, 4));
		}
		//ss.getQueryList("经济公有制");
	}

}
