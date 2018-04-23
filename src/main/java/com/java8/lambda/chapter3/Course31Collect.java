package com.java8.lambda.chapter3;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;


/**
 * collect(toList()) 方法由 Stream 里的值生成一个列表，是一个及早求值操作。
 * @author hzweiyongqiang
 *
 */
public class Course31Collect {
	
	@Test
	public void collect() {
		// 使用 collect(toList()) 方法从 Stream 中生成一个列表
		List<String> collected = Stream.of("a","b","c")			// 首先由列表生成一个 Stream 
				.collect(Collectors.toList());					// 然后进行一些 Stream 上的操作，继而是 collect 操作，由 Stream 生成列表
		
		assertEquals(Arrays.asList("a","b","c"), collected);	// 最后使用断言判断结果是否和预期一致
	}
	
	
}
