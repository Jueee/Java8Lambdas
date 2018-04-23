package com.java8.lambda.chapter3;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * Stream 中提供的新方法 filter 可以遍历数据并检查其中的元素
 * 
 * @author hzweiyongqiang
 *
 */
public class Course33Filter {

	
	@Test
	public void filter() {
		// 找出一组字符串中以数字开头的字符串
		// 比如字符串 " 1abc " 和 " abc "，其中 " 1abc " 就是符合条件的字符串。
		// isDigit() 方法用于判断指定字符是否为数字。
		
		// 使用循环遍历列表，使用条件语句做判断
		List<String> beaginningWithNumbers1 = new ArrayList<>();
		for(String value:Arrays.asList("a","1abc","abc1")) {
			if (Character.isDigit(value.charAt(0))) {
				beaginningWithNumbers1.add(value);
			}
		}
		assertEquals(Arrays.asList("1abc"), beaginningWithNumbers1);
		
		// 函数式风格
		List<String> beaginningWithNumbers2 = Stream.of("a","1abc","abc1")
				.filter(value -> Character.isDigit(value.charAt(0)))
				.collect(Collectors.toList());
		assertEquals(Arrays.asList("1abc"), beaginningWithNumbers2);
		
	}
}
