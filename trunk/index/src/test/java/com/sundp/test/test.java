package com.sundp.test;

import java.util.ArrayList;

import com.sundp.DataTransfer;
import com.sundp.handle.MutiHandle;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Map<String, DocTerm> tokenlist = new LinkedHashMap<String, DocTerm>();
		//Map<Integer,Map<String, DocTerm>> docList = new LinkedHashMap<Integer, Map<String,DocTerm>>();
		ArrayList<String> fileSet = new ArrayList<String>();
		for (int i = 1; i <= 30; i++) {
			fileSet.add("D:\\1\\"+i+".txt");
		}
		
		DataTransfer index = new MutiHandle();
		index.init(fileSet);

		System.out.println("IDF:"+index.getIdf().size());
		System.out.println("TF:"+index.getDocTermFreq().size());
		System.out.println("InvoTF*IDF:"+index.getTerms().size());
		System.out.println("TF*IDF:"+index.getIdf().size());
		
	}
	
}
