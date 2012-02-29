package com.sundp.search.dao.impl;

import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sundp.search.dao.IIndexDao;
import com.sundp.search.entity.Doc;
import com.sundp.search.entity.Idf;
import com.sundp.search.entity.Terms;

public class IndexDao extends HibernateDaoSupport implements IIndexDao {
	@SuppressWarnings("unchecked")
	public IndexDao() {
		super();
		List<Doc> docList = this.getSession().createCriteria(Doc.class).list();
		docs = new LinkedHashMap<Integer, String>();
		for (Doc doc : docList) {
			if (doc == null) {
				continue;
			}
			docs.put(doc.getDoc(), doc.getContent());
		}
		docList = null;
		List<Idf> idfList = this.getSession().createCriteria(Idf.class).list();
		idf = new LinkedHashMap<String, Double>();
		for (Idf idf : idfList) {
			if (idf == null) {
				continue;
			}
			this.idf.put(idf.getTerm(), idf.getValue());
		}
		idfList = null;
		terms = new LinkedHashMap<String, Map<Integer, Double>>();
		List<Terms> termsList = this.getSession().createCriteria(Terms.class)
				.list();
		for (Terms term : termsList) {
			if (term == null) {
				continue;
			}
			if (!terms.containsKey(term.getTerm())) {
				Map<Integer, Double> docMap = new LinkedHashMap<Integer, Double>();
				docMap.put(term.getDoc(), term.getValue());
				terms.put(term.getTerm(), docMap);
			} else {
				Map<Integer, Double> docMap = terms.get(term.getTerm());
				docMap.put(term.getDoc(), term.getValue());
			}
		}
		termsList = null;
	}

	private final Map<Integer, String> docs;
	private final Map<String, Double> idf;
	private final Map<String, Map<Integer, Double>> terms;

	public List String(final String hql, final Object... args) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);

				for (int i = 0; i < args.length; i++) {
					query.setParameter(i, args[i]);
				}
				List list = query.list();
				return list;
			}
		});
		return list;
	}

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
		StringReader reader = new StringReader(query);
		LinkedHashMap<String, Double> termList = new LinkedHashMap<String, Double>();
		Analyzer analyzer = new PaodingAnalyzer();

		try {
			TokenStream ts = analyzer.tokenStream("", reader);
			ts.reset();
			TermAttribute termAtt = ts.addAttribute(TermAttribute.class);

			while (ts.incrementToken()) {
				String tempTermAtt = termAtt.term();
				if (idf.containsKey(tempTermAtt)) {
					if (!termList.containsKey(tempTermAtt)) {
						termList.put(tempTermAtt, idf.get(tempTermAtt));
					} else {
						Double temp = termList.get(tempTermAtt) * 2;
						termList.put(tempTermAtt, temp);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
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
		// TODO Auto-generated method stub
		return null;
	}
}
