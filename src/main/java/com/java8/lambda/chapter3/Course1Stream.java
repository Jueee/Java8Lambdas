package com.java8.lambda.chapter3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 流（Stream）
 * 流使程序员得以站在更高的抽象层次上对集合进行操作。
 * @author hzweiyongqiang
 *
 */
public class Course1Stream {

	
	public static void main(String[] args) {
		List<String> citys = new ArrayList<String>();
		citys.add("abc");
		citys.add("bcd");
		citys.add("cde");
		citys.add("def");
		
		// 使用 for 循环计算包含“b”的个数
		int count1 = 0;
		for (String city:citys) {
			if (city.indexOf("b") != -1) {
				count1++;
			}
		}
		System.out.println(count1);
		
		// 使用迭代器计算
		int count2 = 0;
		Iterator<String> iterator = citys.iterator();
		while (iterator.hasNext()) {
			String city = iterator.next();
			if (city.indexOf("b") != -1) {
				count2++;
			}
		}
		System.out.println(count2);
		
		// 使用内部迭代计算
		// Stream 是用函数式编程方式在集合类上进行复杂操作的工具。
		long count3 = citys.stream().filter(city -> city.indexOf("b") != -1).count();
		System.out.println(count3);
	}
}
