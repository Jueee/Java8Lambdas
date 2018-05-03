package com.java8.lambda.chapter6;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class Course9ExercisesTest {

	Course9Exercises test = new Course9Exercises();
	
	@Test
	void test1() {
		System.out.println("------ start test 1 -------");
		List<Integer> values = Arrays.asList(1,2,3,4,5);
		System.out.println(test.sequentialSumOfSquares(values));
		System.out.println(test.parallelSumOfSquares(values));
	}
	
	@Test
	void test2() {
		System.out.println("------ start test 2 -------");
		List<Integer> values = Arrays.asList(1,2,3,4,5);
		System.out.println(test.multiplyThrough(values));
		System.out.println(test.multiplyThroughByParallel(values));
	}
	
	@Test
	void test3() {
		System.out.println("------ start test 3 -------");
		List<Integer> values = Arrays.asList(1,2,3,4,5);
		System.out.println(test.slowSumOfSquares(values));
		System.out.println(test.slowSumOfSquares2(values));
	}

}
