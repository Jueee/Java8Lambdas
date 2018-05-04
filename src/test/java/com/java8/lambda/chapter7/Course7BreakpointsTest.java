package com.java8.lambda.chapter7;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.SampleData;

class Course7BreakpointsTest {

	@Test
	void test() {
		Course7Breakpoints test = new Course7Breakpoints();
		Album album = SampleData.testAlbum;
		
		// ByPeek
		test.getNationalityByPeek(album).size();
	}

}
