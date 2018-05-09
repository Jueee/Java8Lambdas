package com.java8.lambda.chapter9;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;

class Course07ReactiveProgrammingTest {

	@Test
	void test() {
		Course07ReactiveProgramming examples = new Course07ReactiveProgramming(SampleData.getThreeArtists());
		
		Artist artist = examples.search("John", "UK", 5)
								.toBlockingObservable()
								.single();
		System.out.println(artist);
		
		assertEquals(SampleData.johnLennon, artist);
	}

}
