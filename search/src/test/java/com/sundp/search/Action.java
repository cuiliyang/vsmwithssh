package com.sundp.search;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.junit.Test;

import com.sundp.search.dao.IIndexDao;
import com.sundp.search.dao.impl.FileDao;
import com.sundp.search.service.IIndexService;
import com.sundp.search.service.impl.IndexService;

public class Action {
	IIndexDao dd = new FileDao();
	IIndexService ss = new IndexService(dd);
	Date d = new Date();

	@Test
	public void show() {
		String question;// = "1、用经济常识知识简要说明重视安全生产的经济意义。";
		int answer = 1;
		int flag = 1;
		int firstAnswer = 0;
		Map<String, Double> queryList;
		Map<Integer, Double> result;// = ss.getResult(question);
		try {
			OutputStreamWriter ow = null;

			for (int i = 0; i < d.getQuestionList().size(); i++) {
				question = d.getQuestionList().get(i);
				queryList = ss.getQueryList(question);
				result = ss.getResult(queryList);
				answer = d.getAnswerList().get(i);
				
				// System.out.println("\n"+question);
				for (Integer obj : result.keySet()) {
					if (flag == 1) {
						firstAnswer = obj.intValue();
						if (firstAnswer == answer) {
							ow = new OutputStreamWriter(new FileOutputStream("D:\\result\\"
									+ (i+1) + ".txt"), "GB2312");
							ow.write("\n" + question + "\n正确\t答案是" + answer+"\n");
							//ow.write("\n" + question + ",正确,答案是" + answer+"\n");
							System.out.println("\n" + question + "\n正确，答案是"
									+ answer);
							// break;
						} else {
							ow = new OutputStreamWriter(new FileOutputStream("D:\\result\\"
									+ (i+1) + "w.txt"), "GB2312");
							ow.write("\n" + question + "\n不正确\t答案是" + answer+"\n");// +question);
							//ow.write("\n" + question + ",不正确,答案是" + answer+"\n");// +question);
							System.out.println("\n" + question + "\n不正确，答案是"
									+ answer);// +question);
						}// TODO assertTrue(firstAnswer == answer.intValue());
						flag = 0;
					}/*
					ow.write(obj + "," + result.get(obj) + ","
							+ ss.getDocument(obj)+"\n");*/
					ow.write(obj + "\t" + result.get(obj) + "\t"
							+ ss.getDocument(obj)+"\n");
					System.out.println(obj + "\t" + result.get(obj) + "\t"
							+ ss.getDocument(obj));
					// break;//System.out.print(obj + " ");
				}
				flag = 1;
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
