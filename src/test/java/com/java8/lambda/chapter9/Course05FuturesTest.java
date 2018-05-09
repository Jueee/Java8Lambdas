package com.java8.lambda.chapter9;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;
import com.java8.lambda.chapter1.Track;
import com.java8.lambda.chapter9.Course05Futures.AlbumLookup;

class Course05FuturesTest {
	
	@Test
	void test() {
		Course05Futures test = new Course05Futures();
		
		Album album = SampleData.aLoveSupreme;
		List<Track> tracks = album.getTrackList();
		List<Artist> artists = album.getMusicianList();
		
		AlbumLookup lookup = test.new FutureAlbumLookup(tracks, artists);
		System.out.println("Testing:"+lookup.getClass().getSimpleName());
		
		Album result = lookup.lookupByName(album.getName());
		System.out.println(result);
		
		assertEquals(tracks, result.getTrackList());
		assertEquals(artists, result.getMusicianList());
		System.out.println("end!");
	}

}
