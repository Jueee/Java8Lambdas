package com.java8.lambda.chapter7;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.SampleData;

/**
 *	
 *	@author hzweiyongqiang
 */
class Course5LoggingTest {

	@Test
	void test() {
		Course5Logging test = new Course5Logging();
		Album album = SampleData.testAlbum;
		
		// ByFor
		test.getNationalityByFor(album).size();
		
		// ByForEach
		test.getNationalityByForEach(album).size();
	}

}
