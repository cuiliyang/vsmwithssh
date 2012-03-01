package com.sundp.handle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import com.sundp.analyzer.AbstractAnalyzer;
import com.sundp.analyzer.Paoding;
import com.sundp.analyzer.PaodingString;
import com.sundp.calculate.DocTermFreq;
import com.sundp.entity.DocInfo;

public class DocHandle {

	private AbstractAnalyzer analyzer = new Paoding();

	private AbstractAnalyzer analyzer2 = new PaodingString();
	private DocTermFreq docTermFreq = new DocTermFreq();

	private DocInfo docInfo;

	private BufferedReader bufferedReader = null;

	private Map<String, Double> termFreqMap;

	private void read(String filePath) {
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath), "GB2312"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public void run(String filePath) {
		docTermFreq = new DocTermFreq();
		this.read(filePath);
		docInfo = new DocInfo();
		// TODO 读出ContentString
		analyzer.analyzer(bufferedReader);
		docInfo.setMaxcount(analyzer.getFreqMax());
		docInfo.setWcount(analyzer.getWordsCount());
		docTermFreq.calculate(analyzer.getTermList(), analyzer.getFreqMax());
		termFreqMap = docTermFreq.getTermFreqMap();
	};
	
	public void runString(String content) {
		docTermFreq = new DocTermFreq();
		//this.read(content);
		docInfo = new DocInfo();
		// TODO 读出ContentString
		analyzer2.analyzer(content);
		docInfo.setMaxcount(analyzer2.getFreqMax());
		docInfo.setWcount(analyzer2.getWordsCount());
		docTermFreq.calculate(analyzer2.getTermList(), analyzer2.getFreqMax());
		termFreqMap = docTermFreq.getTermFreqMap();
	};

	public void test(String filePath) {
		this.read(filePath);
		analyzer.analyzer(bufferedReader);
		docTermFreq.calculate(analyzer.getTermList(), analyzer.getFreqMax());
		termFreqMap = docTermFreq.getTermFreqMap();
		this.write(termFreqMap, filePath + ".csv");
	};

	private void write(Map<String, Double> tokenlist, String fileFullPath) {
		try {
			OutputStreamWriter ow = new OutputStreamWriter(
					new FileOutputStream(fileFullPath), "GB2312");
			ow.write("Term,Freq\n");
			if (tokenlist.size() > 0) {
				for (Object obj : tokenlist.keySet()) {
					String key = (String) obj;
					Double value = tokenlist.get(obj);
					ow.write(key + "," + value + "\n");
				}
			}
			ow.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
			}
		}
	};

	public Map<String, Double> getTermFreqMap() {
		return termFreqMap;
	};

	public Set<String> getTermSet() {
		return analyzer.getTermSet();
	}
	public Set<String> getTermSet2() {
		return analyzer2.getTermSet();
	}
	public String getDocContent() {
		return analyzer.getContent();
	}
	public DocInfo getDocInfo() {
		return docInfo;
	}
}
