package com.java8.lambda.chapter8;

import org.junit.jupiter.api.Test;

class Course31SingleResponsibilityTest {

	Course31SingleResponsibility test = new Course31SingleResponsibility();
	@Test
	void test() {
		System.out.println(test.countPrimes(100));
		System.out.println(test.countPrimes2(100));
		System.out.println(test.countPrimes3(100));
		System.out.println(test.countPrimes4(100));
	}

}
