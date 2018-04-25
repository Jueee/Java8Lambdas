package com.java8.lambda.chapter4;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter4.Course07MultExtends.MusicalCarriage;

class Course07MultExtendsTest {

	@Test
	void test() {
		Course07MultExtends test = new Course07MultExtends();
		MusicalCarriage carriage = test.new MusicalCarriage();
		System.out.println(carriage.rock());
		assertEquals("... all over the world!", carriage.rock());
	}

}
