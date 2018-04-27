package com.java8.lambda.chapter5;

import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;

/**
 * 	方法引用
 * 
 * 	标准语法为 Classname::methodName 。
 * 
 * 	需要注意的是，虽然这是一个方法，但不需要在后面加括号，因为这里并不调用该方法。
 * 	凡是使用 Lambda 表达式的地方，就可以使用方法引用。
 *	
 *	@author hzweiyongqiang
 */
public class Course1MethodReferences {

	public static void main(String[] args) {
		List<Artist> artists = SampleData.membersOfTheBeatles;
		
		// 调用参数的 Lambda 的表达式
		List<String> names1 = artists.stream().map(artist -> artist.getName()).collect(Collectors.toList());
		System.out.println(names1);
		// 方法引用的 Lambda 的表达式
		List<String> names2 = artists.stream().map(Artist::getName).collect(Collectors.toList());
		System.out.println(names2);
		
		
		// 遍历并打印
		artists.forEach(artist -> System.out.println(artist));
		artists.forEach(System.out::println);
		
		
		// 使用 Lambda 表达式创建数组对象
		IntFunction<int[]> arrayMaker = int[]::new;
		int[] array = arrayMaker.apply(10); // 创建数组 int[10]
		
	}
}
