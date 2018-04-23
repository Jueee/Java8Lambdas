package com.java8.lambda.chapter3;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.SampleData;

class Course5MultipleUseTest {

	Course5MultipleUse test = new Course5MultipleUse();
	
	@Test
	void test() {
		List<Album> albums = new ArrayList<>();
		albums.add(SampleData.aLoveSupreme);
		albums.add(SampleData.manyTrackAlbum);
		albums.add(SampleData.sampleShortAlbum);
		albums.add(SampleData.testAlbum);
		
		albums.forEach(album -> {
			System.out.println("test - " + album.getName());
			System.out.println(test.errorMultipleUse(album));
			System.out.println(test.correctMultipleUse(album));
		});
	}

}
