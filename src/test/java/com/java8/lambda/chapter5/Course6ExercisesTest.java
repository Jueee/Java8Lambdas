package com.java8.lambda.chapter5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter5.Course6Exercises.Fibonacci;

class Course6ExercisesTest {

	Course6Exercises test = new Course6Exercises();
	
	@Test
	void exercises1() {
		List<String> list1 = Arrays.asList("a","b","hello");
		List<String> collected1 = test.exercises11(list1); 
		assertEquals(Arrays.asList("A","B","HELLO"), collected1);
		
		List<Integer> list2 = Arrays.asList(1,2,3,4,5,6);
		Optional<Integer> collected2 = test.exercises12(list2);
		assertSame(21, collected2.get());
		
		List<Integer> list3 = Arrays.asList(1,2,3);
		List<Integer> list4 = Arrays.asList(3,4,5,6);
		List<Integer> collected3 = test.exercises13(list3, list4);
		assertEquals(Arrays.asList(1,2,3,3,4,5,6), collected3);
		
	}

	@Test
	void exercises2() {
		List<String> names = Arrays.asList("John Lennon", "Paul McCartney",
				"George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
		String longestName1 = test.exercises21byCollecting(names);
		System.out.println("[longestName1]" + longestName1);
		assertSame("Stuart Sutcliffe", longestName1);
		String longestName2 = test.exercises21byReduce(names);
		System.out.println("[longestName2]" + longestName2);
		assertSame("Stuart Sutcliffe", longestName1);
		
		List<String> words = Arrays.asList("John", "Paul", "George", "John", "Paul", "John");
		System.out.println(test.exercises22(words));
		
		System.out.println(test.exercises23(words));
		System.out.println(test.exercises232(words));
	}

	@Test
	void exercises3() {
		Fibonacci fibonacci = test.new Fibonacci();
		for (int i = 0; i < 10; i++) {
			System.out.println("[fibonacci--"+i+"]" + fibonacci.fibonacci(i));
		}
	}
}
