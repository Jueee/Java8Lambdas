package com.java8.lambda.chapter3;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
/**
 *  map 操作就可以将一个流中的值转换成一个新的流。
 * @author hzweiyongqiang
 *
 */
public class Course32Map {

	
	@Test
	public void map() {
		// 在一个循环中，对每个字符串调用 toUppercase 方法，然后将得到的结果加入一个新的列表。
		
		// 使用 for 循环将字符串转换为大写
		List<String> collected1 = new ArrayList<String>();
		for(String string:Arrays.asList("a","b","hello")) {
			String uooercaseString = string.toUpperCase();
			collected1.add(uooercaseString);
		}
		assertEquals(Arrays.asList("A","B","HELLO"), collected1);
		
		// 使用 map 操作将字符串转换为大写形式
		List<String> collected2 = Stream.of("a","b","hello")
				.map(string -> string.toUpperCase())
				.collect(Collectors.toList());
		assertEquals(Arrays.asList("A","B","HELLO"), collected2);
	}
}
