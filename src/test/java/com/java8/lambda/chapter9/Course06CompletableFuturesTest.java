package com.java8.lambda.chapter9;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;
import com.java8.lambda.chapter1.Track;
import com.java8.lambda.chapter9.Course06CompletableFutures.CompletableFutureAlbumLookup;

class Course06CompletableFuturesTest {

	@Test
	void test() {
		Course06CompletableFutures test = new Course06CompletableFutures();
		
		Album album = SampleData.aLoveSupreme;
		List<Track> tracks = album.getTrackList();
		List<Artist> artists = album.getMusicianList();
		
		CompletableFutureAlbumLookup lookup = test.new CompletableFutureAlbumLookup(tracks, artists);
		System.out.println("Testing:"+lookup.getClass().getSimpleName());
		
		Album result = lookup.lookupByName(album.getName());
		System.out.println(result);
		
		assertEquals(tracks, result.getTrackList());
		assertEquals(artists, result.getMusicianList());
		System.out.println("end!");
	}

}
