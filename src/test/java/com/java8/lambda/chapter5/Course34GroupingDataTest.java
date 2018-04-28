package com.java8.lambda.chapter5;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;

class Course34GroupingDataTest {
	
	Course34GroupingData test = new Course34GroupingData();

	@Test
	void test() {
		/**
		 * 	使用主唱对专辑分组
		 */
		List<Album> albums = SampleData.getThreeAlbums();
		Map<Artist, List<Album>> artistMap = test.albumsByArtist(albums);
		artistMap.forEach((key,value)->{
			System.out.println("[Artist]" + key);
			value.forEach(album->System.out.println(album.getName()));
		});
		

		/**
		 * 	将艺术家组成的流分成乐队和独唱歌手两部分
		 */
		List<Artist> artists = SampleData.membersOfTheBeatles;
		Map<Boolean, List<Artist>> artistMap1 = test.bandsAndSolo(artists);
		System.out.println("[独唱]" + artistMap1.get(true));
		System.out.println("[乐队]" + artistMap1.get(false));
		
		
		List<Artist> artists2 = SampleData.getThreeArtists();
		Map<Boolean, List<Artist>> artistMap2 = test.bandsAndSolo(artists2);
		System.out.println("[独唱]" + artistMap2.get(true));
		System.out.println("[乐队]" + artistMap2.get(false));
	}

}
