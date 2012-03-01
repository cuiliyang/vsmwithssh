package com.sundp.analyzer;

import java.io.BufferedReader;
import java.util.Map;
import java.util.Set;

public abstract class AbstractAnalyzer {

	Set<String> termSet;

	protected Map<String, Integer> termList;

	protected int wordsCount = 0;

	protected int freqMax = 1;

	protected String content;

	public abstract void analyzer(BufferedReader bufferedReader);

	public abstract void analyzer(String content);

	public Set<String> getTermSet() {
		return termSet;
	}

	public Map<String, Integer> getTermList() {
		return termList;
	}

	public int getWordsCount() {
		return wordsCount;
	}

	public int getFreqMax() {
		return freqMax;
	}

	public String getContent() {
		return content;
	}
}
