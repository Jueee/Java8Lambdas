package com.java8.lambda.chapter6;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class Course6PerformanceTest {

	Course6Performance test = new Course6Performance();
	
	@Test
	void test() {
		List<Integer> values = Arrays.asList(1,2,3,4,5);
		assertSame(15, test.addIntegers(values));
	}

}
