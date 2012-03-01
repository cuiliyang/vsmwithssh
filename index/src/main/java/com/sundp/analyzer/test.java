package com.sundp.analyzer;

import java.util.ArrayList;

import com.sundp.handle.MutiHandle;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> fileSet = new ArrayList<String>();
		System.out.println(ClassLoader.getSystemResource("1.txt").toString().substring(6));
		
		fileSet.add(ClassLoader.getSystemResource("resource/1.txt").toString().substring(6));
		MutiHandle paoding = new MutiHandle();
		paoding.setFileSet(fileSet);
		paoding.run();
	}
}
