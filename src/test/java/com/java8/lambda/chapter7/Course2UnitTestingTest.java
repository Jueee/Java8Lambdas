package com.java8.lambda.chapter7;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class Course2UnitTestingTest {

	@Test
	void test() {
		Course2UnitTesting test = new Course2UnitTesting();
		
		/**
		 * 	测试大写转换
		 */
		List<String> input1 = Arrays.asList("a","b","hello");
		List<String> result1 = test.allToUpperCase(input1);
		assertEquals(Arrays.asList("A","B","HELLO"), result1);
		
		/**
		 * 	测试字符串包含两个字符的情况，第一个字母被转换为大写
		 */
		List<String> input2 = Arrays.asList("ab");
		List<String> result2 = test.elementFirstToUpperCaseLambdas(input2);
		assertEquals(Arrays.asList("Ab"), result2);
		
		String input3 = "ab";
		String result3 = Course2UnitTesting.firstToUppercase(input3);
		assertEquals("Ab", result3);
	}

}
