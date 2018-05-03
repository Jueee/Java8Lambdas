package com.java8.lambda.chapter6;

import org.junit.jupiter.api.Test;

class Course7ArrayOperationsTest {

	Course7ArrayOperations test = new Course7ArrayOperations();
	
	@Test
	void test() {
		int size = 10;
		
		double[] num1 = test.imperativeInitilize(size);
		for(double num:num1) {System.out.println(num);}

		double[] num2 = test.parallelInitilize(size);
		for(double num:num2) {System.out.println(num);}
		
		double[] values = new double[]{0, 1, 2, 3, 4, 3.5};
		int n = 3;
		double[] num3 = test.simpleMovingAverage(values, n);
		for(double num:num3) {System.out.println(num);}
		
	}

}
