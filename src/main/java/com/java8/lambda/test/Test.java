package com.java8.lambda.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List strList = new ArrayList();
		strList.add("疯狂Java讲义");
		strList.add("疯狂Android讲义");
		strList.forEach(str -> System.out.println(((String) str).length()));
	}
}
