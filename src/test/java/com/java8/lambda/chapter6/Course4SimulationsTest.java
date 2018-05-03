package com.java8.lambda.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class Course4SimulationsTest {
	
	Course4Simulations test = new Course4Simulations();

	@Test
	void test() {
		List<Integer> times = Arrays.asList(10000,100000,1000000,10000000,100000000);
		times.forEach(time->{
			long startTime1 = System.nanoTime();
			System.out.println("[time]"+time);
			Map<Integer, Double> result1 = test.parallelDiceRolls(time);
			System.out.println("[并行化][UseTime]"+(System.nanoTime() - startTime1));
			long startTime2 = System.nanoTime();
			Map<Integer, Double> result2 = test.serialDiceRolls(time);
			System.out.println("[串行化][UseTime]"+(System.nanoTime() - startTime2));
			System.out.println();
		});
	}

}
