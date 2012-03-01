package com.sundp.search.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

import com.sundp.search.service.IAnalyzerService;

public class AnalyzerService implements IAnalyzerService {

	@Override
	public Map<String, Integer> getResult(String content) {
		StringReader reader = new StringReader(content);
		Analyzer analyzer = new PaodingAnalyzer();
		Map<String, Integer> termList = new LinkedHashMap<String, Integer>();
		Set<String> termSet = new HashSet<String>();
		try {
			TokenStream ts = analyzer.tokenStream("", reader);
			ts.reset();
			TermAttribute termAtt = ts.addAttribute(TermAttribute.class);

			while (ts.incrementToken()) {
				String tempTermAtt = termAtt.term();
				// 分发
				if (!termSet.contains(tempTermAtt)) {

					termSet.add(tempTermAtt);
					termList.put(tempTermAtt, 1);

				} else {
					int temp = termList.get(tempTermAtt) + 1;
					termList.put(tempTermAtt, temp);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return termList;
	}

}
