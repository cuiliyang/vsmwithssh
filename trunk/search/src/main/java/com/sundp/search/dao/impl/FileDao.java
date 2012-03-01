package com.sundp.search.dao.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

import com.sundp.DataTransfer;
import com.sundp.handle.MutiHandle;
import com.sundp.search.dao.IIndexDao;

public class FileDao implements IIndexDao {

	public FileDao() {
		super();
		ArrayList<String> fileSet = new ArrayList<String>();
		for (int i = 1; i <= 30; i++) {
			fileSet.add("D:\\data\\" + i + ".txt");

		}

		DataTransfer m = new MutiHandle();
		m.init(fileSet);
		// paoding.run();
		// TODO 初始化
		docs = m.getDocs();
		idf = m.getIdf();
		terms = m.getTerms();
		docTerms = m.getDocTerms();
	}

	private final Map<Integer, String> docs;
	private final Map<String, Double> idf;
	private final Map<String, Map<Integer, Double>> terms;
	private final Map<Integer, Map<String, Double>> docTerms;

	@Override
	public void out() {
		try {
			// IndexService ss = new IndexService();
			// getHibernateTemplate().save(ss);
		} catch (RuntimeException re) {
			throw re;
		}
		System.out.println("这是Dao中的输出");
	}

	@Override
	public Map<Integer, String> getDocs() {
		return docs;
	}

	@Override
	public Map<String, Double> getIdf() {
		return idf;
	}

	@Override
	public Map<String, Map<Integer, Double>> getTerms() {
		return terms;
	}

	@Override
	public String getDocContent(Integer doc) {
		return docs.get(doc);
	}

	@Override
	public Map<String, Double> getQueryTerms(String query) {
		if (query == null)
			query = "";
		StringReader reader = new StringReader(query);
		LinkedHashMap<String, Integer> termCountList = new LinkedHashMap<String, Integer>();
		LinkedHashMap<String, Double> termList = new LinkedHashMap<String, Double>();
		Analyzer analyzer = new PaodingAnalyzer();
		int maxFerq = 1;
		try {
			TokenStream ts = analyzer.tokenStream("", reader);
			ts.reset();
			TermAttribute termAtt = ts.addAttribute(TermAttribute.class);

			while (ts.incrementToken()) {
				String tempTermAtt = termAtt.term();
				if (idf.containsKey(tempTermAtt)) {
					if (!termCountList.containsKey(tempTermAtt)) {
						termCountList.put(tempTermAtt, 1);// idf.get(tempTermAtt));
					} else {
						Integer temp = termCountList.get(tempTermAtt) + 1;
						termCountList.put(tempTermAtt, temp);
						if (temp > maxFerq)
							maxFerq = temp;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String doc : termCountList.keySet()) {
			Double temp = ((double) termCountList.get(doc) / (double) maxFerq)
					* idf.get(doc);
			termList.put(doc, temp);
		}
		return termList;
	}

	@Override
	public Set<String> getQueryList(String query) {
		StringReader reader = new StringReader(query);
		Set<String> termList = new HashSet<String>();
		Analyzer analyzer = new PaodingAnalyzer();

		try {
			TokenStream ts = analyzer.tokenStream("", reader);
			ts.reset();
			TermAttribute termAtt = ts.addAttribute(TermAttribute.class);

			while (ts.incrementToken()) {
				String tempTermAtt = termAtt.term();
				if ((!termList.contains(tempTermAtt))
						&& idf.containsKey(tempTermAtt)) {
					termList.add(tempTermAtt);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return termList;
	}

	@Override
	public Map<Integer, Map<String, Double>> getDocTerms() {
		return docTerms;
	}
}
