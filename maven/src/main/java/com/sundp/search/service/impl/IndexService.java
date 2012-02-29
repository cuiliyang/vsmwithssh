package com.sundp.search.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.sundp.search.dao.IIndexDao;
import com.sundp.search.dao.impl.FileDao;
import com.sundp.search.entity.Query;
import com.sundp.search.service.IIndexService;

public class IndexService implements IIndexService {
	// TODO 依赖
	private IIndexDao indexDao = new FileDao();

	// private final Map<String, Double> idf;
	private final Map<Integer, String> docs;
	private final Map<String, Map<Integer, Double>> terms;
	private final Map<Integer, Double> docLength = new LinkedHashMap<Integer, Double>();
	private final Map<Integer, Map<String, Double>> docTerms;

	public IndexService() {
		super();
		docs = indexDao.getDocs();
		// idf = indexDao.getIdf();
		terms = indexDao.getTerms();
		docTerms = indexDao.getDocTerms();
		calculateDocsLength();
	}

	@Override
	public String out() {
		indexDao.out();
		return "ture";
	}

	@Override
	public String getDocument(Integer doc) {
		return indexDao.getDocContent(doc);
	}

	@Override
	public Set<String> getQueryTermSet(String query) {
		return indexDao.getQueryList(query);
	}

	@Override
	public Map<String, Double> getQueryList(String query) {
		return indexDao.getQueryTerms(query);
	}

	public IIndexDao getIndexDao() {
		return indexDao;
	}

	public void setIndexDao(IIndexDao indexDao) {
		this.indexDao = indexDao;
	}

	private void calculateDocsLength() {
		Map<String, Double> docTempTermWeight;// = new LinkedHashMap<String,
		// Double>();
		for (Integer doc : docTerms.keySet()) {
			docTempTermWeight = docTerms.get(doc);
			double tempWeight = 0;
			for (String term : docTempTermWeight.keySet()) {
				tempWeight = tempWeight + docTempTermWeight.get(term)
						* docTempTermWeight.get(term);
			}
			tempWeight = Math.sqrt(tempWeight);
			docLength.put(doc, tempWeight);
		}
	}

	@Override
	public Map<String, Double> getQueryList(Map<String, Double> queryList,
			Set<Integer> relationDocs, Set<Integer> unRelationDocs) {
		Map<String, Double> relationTermList = new LinkedHashMap<String, Double>();
		Map<String, Double> unRelationTermList = new LinkedHashMap<String, Double>();
		Map<String, Double> tempTermList;
		Set<String> tempTerms;
		if (relationDocs != null && !relationDocs.isEmpty()) {
			tempTerms = new HashSet<String>();
			for (Integer doc : relationDocs) {
				tempTermList = docTerms.get(doc);
				for (String term : tempTermList.keySet()) {
					if (!relationTermList.containsKey(term)) {
						tempTerms.add(term);
						relationTermList.put(term, tempTermList.get(term));
					} else {
						relationTermList.put(term, relationTermList.get(term)
								+ tempTermList.get(term));
					}
				}
			}
			for (String term : tempTerms) {
				if (queryList.containsKey(term)) {
					queryList.put(term,
							(relationTermList.get(term) / relationDocs.size())
									+ queryList.get(term));
				} else {
					queryList.put(term,
							(relationTermList.get(term) / relationDocs.size()));
				}
			}
		}

		if (unRelationDocs != null && !unRelationDocs.isEmpty()) {
			tempTerms = new HashSet<String>();
			for (Integer doc : unRelationDocs) {
				tempTermList = docTerms.get(doc);
				for (String term : tempTermList.keySet()) {
					if (!unRelationTermList.containsKey(term)) {
						tempTerms.add(term);
						unRelationTermList.put(term, tempTermList.get(term));
					} else {
						unRelationTermList.put(term, unRelationTermList
								.get(term)
								+ tempTermList.get(term));
					}
				}
			}
			for (String term : tempTerms) {
				if (queryList.containsKey(term)) {
					queryList.put(term,
							-(unRelationTermList.get(term) / unRelationDocs
									.size())
									+ queryList.get(term));
				} else {
					queryList.put(term,
							-(unRelationTermList.get(term) / unRelationDocs
									.size()));
				}
			}
		}
		return queryList;
	}

	@Override
	public Map<Integer, Double> getResult(Map<String, Double> queryList) {
		Map<Integer, Double> result = new LinkedHashMap<Integer, Double>();
		Map<Integer, Double> tempResult = new TreeMap<Integer, Double>();
		Set<Integer> docSet = new HashSet<Integer>();
		docSet.addAll(docs.keySet());
		Double queryLength = new Double(0);
		for (String term : queryList.keySet()) {
			queryLength = queryLength + queryList.get(term)
					* queryList.get(term);
			if (terms.containsKey(term)) {
				Map<Integer, Double> termTempDocWeight = terms.get(term);
				for (Integer doc : termTempDocWeight.keySet()) {
					if (!tempResult.containsKey(doc)) {
						tempResult.put(doc, termTempDocWeight.get(doc));
					} else {
						tempResult.put(doc, tempResult.get(doc)
								+ termTempDocWeight.get(doc));
					}
				}
			}
		}
		queryLength = Math.sqrt(queryLength);
		// TODO 计算分母
		for (Integer doc : tempResult.keySet()) {
			double temp = tempResult.get(doc)
					/ (docLength.get(doc) * queryLength);
			docSet.remove(doc);
			tempResult.put(doc, temp);
		}
		if (tempResult != null && !tempResult.isEmpty()) {
			List<Map.Entry<Integer, Double>> entryList = new ArrayList<Map.Entry<Integer, Double>>(
					tempResult.entrySet());
			Collections.sort(entryList,
					new Comparator<Map.Entry<Integer, Double>>() {
						public int compare(Map.Entry<Integer, Double> o1,
								Map.Entry<Integer, Double> o2) {
							if (o2.getValue() > o1.getValue()) {
								return 1;
							} else if (o1.getValue() > o2.getValue()) {
								return -1;
							} else {
								return 0;
							}
						}
					});
			Iterator<Map.Entry<Integer, Double>> iter = entryList.iterator();
			Map.Entry<Integer, Double> tmpEntry = null;
			while (iter.hasNext()) {
				tmpEntry = iter.next();
				result.put(tmpEntry.getKey(), tmpEntry.getValue());
			}

		}
		for (Integer doc : docSet) {
			result.put(doc, (double) 0);
		}
		return result;
	}

	@Override
	public Map<Integer, String> getDocs() {
		return docs;
	}

	@Override
	public List<Query> getQueryLists(Map<String, Double> queryList) {
		List<Query> queryLists = new ArrayList<Query>();
		for (String term : queryList.keySet()) {
			queryLists.add(new Query(term, queryList.get(term)));
		}
		return queryLists;
	}

	@Override
	public Map<String, Double> getQueryList(List<Query> queryLists) {
		Map<String, Double> queryList = new LinkedHashMap<String, Double>();
		if(queryLists!=null)
		{
			for (Query q : queryLists) {
				queryList.put(q.getTerm(), q.getValue());
			}
		}
		return queryList;
	}
}
