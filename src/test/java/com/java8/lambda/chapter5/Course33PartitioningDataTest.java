package com.java8.lambda.chapter5;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;

class Course33PartitioningDataTest {

	@Test
	void test() {
		Course33PartitioningData test = new Course33PartitioningData();
		
		
		List<Artist> artists = SampleData.membersOfTheBeatles;
		Map<Boolean, List<Artist>> artistMap = test.bandsAndSolo(artists);
		System.out.println("[独唱]" + artistMap.get(true));
		System.out.println("[乐队]" + artistMap.get(false));
		
		
		List<Artist> artists2 = SampleData.getThreeArtists();
		Map<Boolean, List<Artist>> artistMap2 = test.bandsAndSolo(artists2);
		System.out.println("[独唱]" + artistMap2.get(true));
		System.out.println("[乐队]" + artistMap2.get(false));
	}

}
