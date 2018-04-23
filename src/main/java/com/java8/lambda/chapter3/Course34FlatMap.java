package com.java8.lambda.chapter3;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * flatMap 方法可用 Stream 替换值，然后将多个 Stream 连接成一个 Stream
 * 
 * @author hzweiyongqiang
 *
 */
public class Course34FlatMap {

	
	@Test
	public void flatMap() {
		// 假设有一个包含多个列表的流，现在希望得到所有数字的序列。
		
		// 调用 stream 方法，将每个列表转换成 Stream 对象，其余部分由 flatMap 方法处理。
		List<Integer> together = Stream.of(Arrays.asList(1,2),Arrays.asList(3,4))
				.flatMap(number -> number.stream())
				.collect(Collectors.toList());
		assertEquals(Arrays.asList(1,2,3,4), together);
		
	}
}
