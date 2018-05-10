package com.java8.lambda.chapter9;

import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;

public class FakeLookupService {

	public Artist lookupArtistname(String name) {
		switch (name) {
		case "The Beatles":
			return SampleData.theBeatles;
		case "John Coltrane":
			return SampleData.johnColtrane;
		default:
			throw new IllegalArgumentException("Unknown artist:"+name);
		}
	}
}
