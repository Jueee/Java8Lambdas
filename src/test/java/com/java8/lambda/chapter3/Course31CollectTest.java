package com.java8.lambda.chapter3;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class Course31CollectTest {
	
	Course31Collect collect = new Course31Collect();

	@Test
	void testCollect() {
		List<String> collected = Stream.of("a","b","c").collect(Collectors.toList());
		assertEquals(Arrays.asList("a","b","c"), collected);
		collect.collect();
	}

}
