package com.java8.lambda.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.SampleData;

class Course4RefactoringTest {

	Course4Refactoring test = new Course4Refactoring();
	
	@Test
	void test() {
		List<Album> album1 = SampleData.getThreeAlbums();
		
		List<List<Album>> albumList = new ArrayList<>();
		albumList.add(album1);
		albumList.add(Arrays.asList(SampleData.aLoveSupreme));
		albumList.add(Arrays.asList(SampleData.sampleShortAlbum));
		albumList.add(Arrays.asList(SampleData.sampleShortAlbum, SampleData.manyTrackAlbum));
		
		albumList.forEach(albums -> {
			System.out.println("Testing: " + albums.toString());
			System.out.println(test.findLongTracks0(albums));
			System.out.println(test.findLongTracks1(albums));
			System.out.println(test.findLongTracks2(albums));
			System.out.println(test.findLongTracks3(albums));
			System.out.println(test.findLongTracks4(albums));
		});
	}

}
