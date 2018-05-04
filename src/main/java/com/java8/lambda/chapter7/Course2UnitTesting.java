package com.java8.lambda.chapter7;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 	Lambda表达式的单元测试
 * 
 * 	单元测试是测试一段代码的行为是否符合预期的方式。
 *	通常，在编写单元测试时，怎么在应用中调用该方法，就怎么在测试中调用。
 *	给定一些输入或测试替身，调用这些方法，然后验证结果是否和预期的行为一致。
 *
 *	Lambda 表达式给单元测试带来了一些麻烦，Lambda 表达式没有名字，无法直接在测试代码中调用。
 *	
 *	解决该问题有两种方式。
 *	第一种是将 Lambda 表达式放入一个方法测试，这种方式要测那个方法，而不是 Lambda 表达式本身。
 *	第二种是别用 Lambda 表达式。请用方法引用。任何 Lambda 表达式都能被改写为普通方法，然后使用方法引用直接引用。
 *	@author hzweiyongqiang
 */
public class Course2UnitTesting {

	/**
	 * 	将字符串转换为大写形式
	 * 	在这段代码中，Lambda 表达式唯一的作用就是调用一个 Java 方法。
	 * 	测试这段代码，会将重点放在方法的行为上。
	 *	@param words
	 *	@return
	 */
	public List<String> allToUpperCase(List<String> words){
		return words.stream()
					.map(String::toUpperCase)
					.collect(Collectors.toList());
	}
	
	/**
	 * 	将列表中元素的第一个字母转换成大写
	 * 	如果要测试这段代码，我们必须创建一个列表，然后将想要测试的各种情况都测试到。
	 *	@param words
	 *	@return
	 */
	public List<String> elementFirstToUpperCaseLambdas(List<String> words){
		return words.stream()
					.map(value->{
						char firstChar = Character.toUpperCase(value.charAt(0));
						return firstChar + value.substring(1);
					})
					.collect(Collectors.toList());
	}
	
	/**
	 * 	将首字母转换为大写，应用到所有列表元素
	 * 	将 Lambda 表达式重构为一个方法，然后在主程序中使用，主程序负责转换字符串。
	 *	@param words
	 *	@return
	 */
	public List<String> elementFirstToUpperCase(List<String> words){
		return words.stream()
					.map(Course2UnitTesting::firstToUppercase)
					.collect(Collectors.toList());
	}
	public static String firstToUppercase(String value) {
		char firstChar = Character.toUpperCase(value.charAt(0));
		return firstChar + value.substring(1);
	}
	
}
