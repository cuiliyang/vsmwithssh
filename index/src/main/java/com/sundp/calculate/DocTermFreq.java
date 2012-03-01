package com.sundp.calculate;

import java.util.LinkedHashMap;
import java.util.Map;

public class DocTermFreq {

	private Map<String, Double> termFreqMap;

	public void calculate(Map<String, Integer> termList, int freqMax) {
		termFreqMap = new LinkedHashMap<String, Double>();
		for (Object obj : termList.keySet()) {
			Integer count = termList.get(obj);
			// TODO tf
			double result = (double) count / (double) freqMax;
			termFreqMap.put((String) obj, result);
		}
	}

	public Map<String, Double> getTermFreqMap() {
		return termFreqMap;
	};

}
