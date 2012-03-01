package com.sundp.calculate;

import java.util.LinkedHashMap;
import java.util.Map;

public class TermFreqInvertDocFreq {

	private Map<String, Double> docTempTermFreq;
	private Map<String, Double> docTempTermWeight;
	private Map<String, Double> invertDocFreq;
	private int docCount;
	private Map<String, Integer> termOfDocCountMap;

	private Map<Integer, Map<String, Double>> invertDocTermWeight = new LinkedHashMap<Integer, Map<String, Double>>();

	public Map<Integer, Map<String, Double>> calculate(
			Map<Integer, Map<String, Double>> docTermFreq,
			Map<String, Integer> termOfDocCountMap, int docCount) {
		this.termOfDocCountMap = termOfDocCountMap;
		this.docCount = docCount;
		calculateIDF();
		for (Integer doc : docTermFreq.keySet()) {
			this.docTempTermFreq = docTermFreq.get(doc);
			docTempTermWeight = new LinkedHashMap<String, Double>();
			for (String term : this.docTempTermFreq.keySet()) {
				// TODO idf
				double idf = invertDocFreq.get(term);
				// TODO weight
				double weight = docTempTermFreq.get(term) * idf;
				this.docTempTermWeight.put((String) term, weight);
			}
			invertDocTermWeight.put((Integer) doc, this.docTempTermWeight);
		}
		return invertDocTermWeight;
	}

	private void calculateIDF() {
		invertDocFreq = new LinkedHashMap<String, Double>();
		for (String term : termOfDocCountMap.keySet()) {
			double idf = (double) docCount / (double) termOfDocCountMap.get(term);
			idf = Math.log(idf);
			invertDocFreq.put(term, idf);
		}
	}

	public Map<String, Double> getInvertDocFreq() {
		return invertDocFreq;
	}

	public Map<Integer, Map<String, Double>> getInvertDocTermWeight() {
		return invertDocTermWeight;
	}
}
