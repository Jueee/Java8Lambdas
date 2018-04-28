package com.java8.lambda.chapter5;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;

class Course32ToValuesTest {

	@Test
	void test() {
		Course32ToValues test = new Course32ToValues();
		
		List<Artist> artists = SampleData.getThreeArtists();
		Optional<Artist> optional = test.biggestGroup(artists);
		System.out.println(optional.isPresent());
		System.out.println(optional.get());
		
		List<Album> albums = SampleData.getThreeAlbums();
		System.out.println(test.averageNumberOfTracks(albums));
	}

}
