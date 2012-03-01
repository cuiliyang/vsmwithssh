package com.sundp.analyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.LinkedHashMap;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

public class PaodingString extends AbstractAnalyzer {

	@Override
	public void analyzer(BufferedReader bufferedReader) {

	}

	@Override
	public void analyzer(String content) {
		this.content = content;
		termSet = new HashSet<String>();
		termList = new LinkedHashMap<String, Integer>();
		Analyzer analyzer = new PaodingAnalyzer();
		try {

			StringReader reader = new StringReader(content);
			// collect token
			TokenStream ts = analyzer.tokenStream("", reader);
			ts.reset();
			TermAttribute termAtt = ts.addAttribute(TermAttribute.class);

			wordsCount = 0;
			freqMax = 1;

			while (ts.incrementToken()) {
				String tempTermAtt = termAtt.term();
				wordsCount++;
				// 分发
				if (!termSet.contains(tempTermAtt)) {

					termSet.add(tempTermAtt);
					termList.put(tempTermAtt, 1);

				} else {
					int temp = termList.get(tempTermAtt) + 1;
					termList.put(tempTermAtt, temp);
					if (temp > freqMax)
						freqMax = temp;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}