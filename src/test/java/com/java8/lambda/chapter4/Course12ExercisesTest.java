package com.java8.lambda.chapter4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;
import com.java8.lambda.chapter4.Course12Exercises.Artists;
import com.java8.lambda.chapter4.Course12Exercises.ArtistsFixed;
import com.java8.lambda.chapter4.Course12Exercises.PerFormanceFixed;
import com.java8.lambda.chapter4.Course12Exercises.PerFormanceFixedAmswers;

class Course12ExercisesTest {

	@Test
	@Disabled	// JUnit 4禁止测试使用了@Ignore注释，而在JUnit 5中则使用@Disabled注释。
	void testPerFormance() {
		PerFormanceFixed stub = new PerFormanceFixed() {
			
			@Override
			public String getName() {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public Stream<Artist> getMusicians() {
				return Stream.of(SampleData.theBeatles);
			}
		};
		
		List<Artist> allMusicians = stub.getAllMusicians().collect(Collectors.toList());
		assertThat(allMusicians, Matchers.hasItem(SampleData.theBeatles));
		assertThat(allMusicians, Matchers.hasItems(SampleData.membersOfTheBeatles.toArray(new Artist[0])));
	}
	
	@Test
	@Disabled
	void testPerFormanceFixedAmswers() {
		PerFormanceFixedAmswers stub = new PerFormanceFixedAmswers() {
			
			@Override
			public String getName() {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public Stream<Artist> getMusicians() {
				return Stream.of(SampleData.theBeatles);
			}
		};
		
		List<Artist> allMusicians = stub.getAllMusicians().collect(Collectors.toList());
		assertThat(allMusicians, Matchers.hasItem(SampleData.theBeatles));
		assertThat(allMusicians, Matchers.hasItems(SampleData.membersOfTheBeatles.toArray(new Artist[0])));
	}

	Course12Exercises test = new Course12Exercises();
	private final Artists optionalExamples = test.new Artists(SampleData.getThreeArtists());
	
	@Test
	void indexWithinRange() {
		Artist artist = optionalExamples.getArtist(0);
		assertNotNull(artist);
	}
	
	@Test
	public void indexOutsideRange() {
		try {
			optionalExamples.getArtist(4);
		} catch (Exception e) {
			System.out.println("indexOutsideRange:" + (e instanceof IllegalArgumentException));
			assertTrue(e instanceof IllegalArgumentException);
		}
	}
	
	@Test
	public void nameIndexInsideRange() {
		String artist = optionalExamples.getArtistName(0);
		System.out.println("nameIndexInsideRange:" + artist);
		assertEquals("John Coltrane", artist);
	}

	
	@Test
	public void nameIndexOutsideRange() {
		String artist = optionalExamples.getArtistName(4);
		System.out.println("nameIndexOutsideRange:" + artist);
		assertEquals("unknown", artist);
	}

	private final ArtistsFixed optionalFixedExamples = test.new ArtistsFixed(SampleData.getThreeArtists());
	
	@Test
	void indexWithinRangeFixed() {
		Optional<Artist> artist = optionalFixedExamples.getArtist(0);
		assertTrue(artist.isPresent());
	}
	
	@Test
	public void indexOutsideRangeFixed() {
		Optional<Artist> artist = optionalFixedExamples.getArtist(4);
		assertFalse(artist.isPresent());
	}
	
	@Test
	public void nameIndexInsideRangeFixed() {
		String artist = optionalFixedExamples.getArtistName(0);
		System.out.println("nameIndexInsideRange:" + artist);
		assertEquals("John Coltrane", artist);
	}

	
	@Test
	public void nameIndexOutsideRangeFixed() {
		String artist = optionalFixedExamples.getArtistName(4);
		System.out.println("nameIndexOutsideRange:" + artist);
		assertEquals("unknown", artist);
	}
	
}
