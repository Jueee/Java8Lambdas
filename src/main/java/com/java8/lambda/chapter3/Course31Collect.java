package com.java8.lambda.chapter3;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * collect(toList()) 方法由 Stream 里的值生成一个列表，是一个及早求值操作。
 * @author hzweiyongqiang
 *
 */
public class Course31Collect {

	public void collect() {
		List<String> collected = Stream.of("a","b","c").collect(Collectors.toList());
		assertEquals(Arrays.asList("a","b","c"), collected);
	}
	
	public static void main(String[] args) {
		
		List<String> collected = Stream.of("a","b","c").collect(Collectors.toList());
		assertEquals(Arrays.asList("a","b","c"), collected);
	}
}
