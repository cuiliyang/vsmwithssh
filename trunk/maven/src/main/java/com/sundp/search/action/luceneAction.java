package com.sundp.search.action;

import java.io.File;
import java.util.LinkedHashMap;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.opensymphony.xwork2.ActionSupport;
import com.sundp.search.dao.IIndexDao;
import com.sundp.search.dao.impl.FileDao;

public class luceneAction extends ActionSupport {

	private static final long serialVersionUID = -1377054056684391247L;
	boolean error = false; // used to control flow for error messages
	String indexName = "/opt/lucene/index"; // local copy of the configuration
	// variable
	IndexSearcher searcher = null; // the searcher used to open/search the index
	IIndexDao d = new FileDao();
	Query query = null; // the Query created by the QueryParser
	TopDocs hits = null; // the search results
	int startindex = 0; // the first index displayed on this page
	int maxpage = 50; // the maximum items displayed on this page
	String queryString = null; // the query entered in the previous page
	String startVal = null; // string version of startindex
	String maxresults = null; // string version of maxpage
	ScoreDoc[] scoreDocs  = null;
	LinkedHashMap<String, Float> result ;
	
	int thispage = 0;

	public String lucene() {

		try {
			IndexReader reader = IndexReader.open(FSDirectory.open(new File(
					indexName)), true); // only searching, so read-only=true
			searcher = new IndexSearcher(reader); // create an indexSearcher for
			// our page
			// NOTE: this operation is slow for large
			// so you might want to keep an IndexSearcher
			Analyzer analyzer = new PaodingAnalyzer(); // open
			QueryParser qp = new QueryParser(Version.LUCENE_CURRENT,
					"contents", analyzer);
			query = qp.parse(queryString); // parse the

			hits = searcher.search(query, 100);
			scoreDocs = hits.scoreDocs;
			result = new LinkedHashMap<String, Float>();
			for (int i = 0; i < scoreDocs.length; i++) {
				Document doc = searcher.doc(scoreDocs[i].doc);
				//doc.get("path").substring(5).split(".");
				result.put(doc.get("path"),scoreDocs[i].score);
			}
			System.out.println("sssss");
			if (searcher != null)
				searcher.close();
		} catch (Exception e) { // any error that happens is probably due
			// to a permission problem or non-existant
			// or otherwise corrupt index
			e.getMessage();
		}

		return "lucene";
	}
	
	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public LinkedHashMap<String, Float> getResult() {
		return result;
	}

	public void setResult(LinkedHashMap<String, Float> result) {
		this.result = result;
	}

}
