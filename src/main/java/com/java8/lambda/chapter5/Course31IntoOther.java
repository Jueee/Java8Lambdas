package com.java8.lambda.chapter5;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;

/**
 * 	转换成其他集合
 *	
 *	处理需求：
 *	1. 已有代码是为集合编写的，因此需要将流转换成集合传入； 
 *	2. 在集合上进行一系列链式操作后，最终希望生成一个值； 
 *	3. 写单元测试时，需要对某个具体的集合做断言。 
 *	@author hzweiyongqiang
 */
public class Course31IntoOther {

	public static void main(String[] args) {
		Stream<Artist> artistStream = SampleData.threeArtists();
		
		// 使用 toCollection ，用定制的集合收集元素
		artistStream.collect(Collectors.toCollection(TreeSet::new));
		
	}
}
