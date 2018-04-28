package com.java8.lambda.chapter5;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;

class Course35StringsTest {

	@Test
	void test() {
		List<Artist> artists = SampleData.getThreeArtists();
		
		System.out.println(Course35Strings.getStringByFor(artists));
		
		System.out.println(Course35Strings.getStringByStream(artists));
	}

}
