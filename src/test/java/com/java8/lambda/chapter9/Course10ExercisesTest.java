package com.java8.lambda.chapter9;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter9.Course10Exercises.ArtistAnalyzer;

class Course10ExercisesTest {
	
	private void assertLargerGroup(ArtistAnalyzer analyser,boolean expected, String artistName,String otherArtistName) {
		AtomicBoolean isLarger = new AtomicBoolean(!expected);
		analyser.isLargerGroup(artistName, otherArtistName, isLarger::set);
		assertEquals(expected, isLarger.get());
	}
	
	@Test
	void test() {
		Course10Exercises test = new Course10Exercises();
		FakeLookupService lookupService = new FakeLookupService();
		
		ArtistAnalyzer analyser1 = test.new CallbackArtistAnalyzer(lookupService::lookupArtistname);
		assertLargerGroup(analyser1, true, "The Beatles", "John Coltrane");
		assertLargerGroup(analyser1, false, "John Coltrane", "The Beatles");
		
		ArtistAnalyzer analyser2 = test.new CallbackArtistAnalyzer(lookupService::lookupArtistname);
		assertLargerGroup(analyser2, true, "The Beatles", "John Coltrane");
		assertLargerGroup(analyser2, false, "John Coltrane", "The Beatles");
	}

	
}
