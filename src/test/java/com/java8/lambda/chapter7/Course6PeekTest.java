package com.java8.lambda.chapter7;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.SampleData;

class Course6PeekTest {

	@Test
	void test() {
		Course6Peek test = new Course6Peek();
		Album album = SampleData.testAlbum;
		
		// ByPeek
		test.getNationalityByPeek(album).size();
	}

}
