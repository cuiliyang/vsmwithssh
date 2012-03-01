package com.sundp.handle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sundp.DataTransfer;
import com.sundp.calculate.TermFreqInvertDocFreq;

public class MutiHandle implements DataTransfer {
	// 文件集
	private List<String> fileSet = new ArrayList<String>();

	private DocHandle docHandle = new DocHandle();

	private Map<Integer, String> docs = new LinkedHashMap<Integer, String>();
	private Map<String, Double> invertDocFreq;

	private TermFreqInvertDocFreq invertDocFreqCalculate = new TermFreqInvertDocFreq();

	private Map<String, Integer> termOfDocCountMap = new LinkedHashMap<String, Integer>();

	private Map<Integer, Map<String, Double>> docTermFreq = new LinkedHashMap<Integer, Map<String, Double>>();

	private Map<Integer, Map<String, Double>> invertDocTermWeight;

	private Map<String, Double> invertTermFreq = new LinkedHashMap<String, Double>();

	public Map<String, Map<Integer, Double>> terms = new LinkedHashMap<String, Map<Integer, Double>>();

	final public void setFileSet(List<String> fileSet) {
		this.fileSet = fileSet;
	};

	final public void run() {
		int i = 0;
		for (Iterator<String> iterator = fileSet.iterator(); iterator.hasNext();) {
			i++;
			String file = (String) iterator.next();
			docHandle.test(file);
			docTermFreq.put(i, docHandle.getTermFreqMap());
			docs.put(i, docHandle.getDocContent());
			this.put(docHandle.getTermSet());
		}
		invertDocFreqCalculate.calculate(docTermFreq, termOfDocCountMap, i);
		invertDocTermWeight = invertDocFreqCalculate.getInvertDocTermWeight();
		invertDocFreq = invertDocFreqCalculate.getInvertDocFreq();
		DTFTransferTDF();
		write(invertDocTermWeight, "D:\\invertDocTermFreq.csv");
		write(docTermFreq, "D:\\docTermFreq.csv");
	};
	
	@Override
	final public void init(Map<Integer,String> sourceDocs) {
		int i = 0;
		for (Integer docId : sourceDocs.keySet()) {
			i++;
			docHandle.runString(sourceDocs.get(docId));
			docTermFreq.put(i, docHandle.getTermFreqMap());
			docs.put(i, docHandle.getDocContent());
			this.put(docHandle.getTermSet2());
		}
		invertDocFreqCalculate.calculate(docTermFreq, termOfDocCountMap, i);
		invertDocTermWeight = invertDocFreqCalculate.getInvertDocTermWeight();
		invertDocFreq = invertDocFreqCalculate.getInvertDocFreq();
		DTFTransferTDF();
		//write(invertDocTermWeight, "D:\\invertDocTermFreq.csv");
		//write(docTermFreq, "D:\\docTermFreq.csv");
	};

	private void put(Set<String> termSet) {
		for (String termname : termSet) {
			if (termOfDocCountMap.containsKey(termname)) {
				termOfDocCountMap.put(termname,
						termOfDocCountMap.get(termname) + 1);
			} else {
				termOfDocCountMap.put(termname, 1);
			}
		}
	}

	protected void write(Map<Integer, Map<String, Double>> temp, String file) {
		try {
			OutputStreamWriter ow = new OutputStreamWriter(
					new FileOutputStream(file), "GB2312");
			ow.write("Doc,Term,Freq\n");

			for (Object obj : temp.keySet()) {
				this.invertTermFreq = temp.get(obj);
				for (Object obj2 : this.invertTermFreq.keySet()) {
					ow.write(obj + "," + obj2 + "," + invertTermFreq.get(obj2)
							+ "\n");
				}
			}
			ow.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<Integer, Map<String, Double>> getDocTerms() {
		return invertDocTermWeight;
	}

	@Override
	public Map<Integer, String> getDocs() {
		return docs;
	}

	@Override
	public Map<String, Double> getIdf() {
		return invertDocFreq;
	}

	@Override
	public Map<String, Map<Integer, Double>> getTerms() {
		return terms;
	};

	/**
	 * 权重转换：由doc索引转换成Term索引
	 */
	private void DTFTransferTDF() {
		Map<String, Double> docTempTermWeight;
		Map<Integer, Double> termTempDocWeight;

		for (Integer doc : invertDocTermWeight.keySet()) {
			docTempTermWeight = invertDocTermWeight.get(doc);
			for (String term : docTempTermWeight.keySet()) {
				if (!terms.containsKey(term)) {
					termTempDocWeight = new LinkedHashMap<Integer, Double>();
					termTempDocWeight.put(doc, docTempTermWeight.get(term));
					terms.put(term, termTempDocWeight);
				} else {
					termTempDocWeight = terms.get(term);
					termTempDocWeight.put(doc, docTempTermWeight.get(term));
					//terms.put(term, termTempDocWeight);
				}
			}
		}
	}

	@Override
	public void init(List<String> fileSet) {
		setFileSet(fileSet);
		run();
	}

	@Override
	public Map<Integer, Map<String, Double>> getDocTermFreq() {
		return docTermFreq;
	}
}
