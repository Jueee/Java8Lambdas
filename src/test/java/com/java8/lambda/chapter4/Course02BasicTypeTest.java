package com.java8.lambda.chapter4;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.SampleData;

class Course02BasicTypeTest {

	@Test
	void test() {
		Course02BasicType.printTrackLengthStatistics(SampleData.aLoveSupreme);
		System.out.println();
		
		Course02BasicType.printTrackLengthStatistics(SampleData.manyTrackAlbum);
		System.out.println();
		
		Course02BasicType.printTrackLengthStatistics(SampleData.sampleShortAlbum);
	}

}
