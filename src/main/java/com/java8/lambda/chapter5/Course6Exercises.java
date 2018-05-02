package com.java8.lambda.chapter5;

import java.util.Arrays;
import java.util.Collection;import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.java8.lambda.chapter1.Artist;

/**
 * 	练习
 *	
 *	@author hzweiyongqiang
 */
public class Course6Exercises {

	/**
	 * 	转换大写的 map 方法
	 *	@param strings
	 *	@return
	 */
	public List<String> exercises11(List<String> strings) {
		return strings.stream()
					  .map(String::toUpperCase)
					  .collect(Collectors.toList());
	}
	
	/**
	 * 	使用 reduce 实现 count 方法
	 *	@param numbers
	 *	@return
	 */
	public Optional<Integer> exercises12(List<Integer> numbers) {
		return numbers.stream()
					  .reduce(Math::addExact);
	}
	
	/**
	 * 	使用 flatMap 连接列表
	 *	@param list1
	 *	@param list2
	 *	@return
	 */
	public List<Integer> exercises13(List<Integer> list1, List<Integer> list2) {
		return Stream.of(list1, list2)
					 .flatMap(Collection::stream)
					 .collect(Collectors.toList());
	}
	
	/**
	 * 	找出名字最长的艺术家，使用收集器实现。
	 *	@param names
	 *	@return
	 */
	public String exercises21byCollecting(List<String> names) {
		Function<String, Integer> getLength = name -> name.length();
		return names.stream()
					.collect(Collectors.maxBy(Comparator.comparing(getLength)))
					.get();
	}
	
	/**
	 * 	找出名字最长的艺术家，使用第 3 章介绍过的 reduce 高阶函数实现。
	 *	@param names
	 *	@return
	 */
	public String exercises21byReduce(List<String> names) {
		Function<String, Integer> getLength = name -> name.length();
		return names.stream()
					.reduce("", (name1,name2)->{
						if (name1.length() > name2.length()) {
							return name1;
						} else {
							return name2;
						}
					});
	}
	
	/**
	 * 	假设一个元素为单词的流，计算每个单词出现的次数。
	 *	@param words
	 *	@return
	 */
	public Map<String, Long> exercises22(List<String> words){
		return words.stream()
				    .collect(Collectors.groupingBy(name->name,Collectors.counting()));
	}
	
	
	/**
	 * 	用一个定制的收集器实现 Collectors.groupingBy 方法
	 */
	/**
	 * 	用一个定制的收集器实现 Collectors.groupingBy 方法————按字符串大小分组
	 *	@param words
	 *	@return
	 */
	public Map<Integer, List<String>> exercises23(List<String> words){
		return words.stream()
				    .collect(new GroupingBy<>(String::length));
	}
	/**
	 * 	用一个定制的收集器实现 Collectors.groupingBy 方法————按字符串名称分组
	 *	@param words
	 *	@return
	 */
	public Map<String, List<String>> exercises232(List<String> words){
		return words.stream()
				    .collect(new GroupingBy<>(name -> name));
	}
	
	/**
	 * 	使用 Map 的 computeIfAbsent 方法高效计算斐波那契数列。
	 * 	这里的“高效”是指避免将那些较小的序列重复计算多次。
	 *	
	 *	@author hzweiyongqiang
	 */
	class Fibonacci {
		private final Map<Integer, Long> cache;
		
		public Fibonacci() {
			cache = new HashMap<>();
			cache.put(0, 0L);
			cache.put(1, 1L);
		}
		
		public long fibonacci(int x) {
			return cache.computeIfAbsent(x, n -> fibonacci(n-1) + fibonacci(n-2));
		}
	}
}
