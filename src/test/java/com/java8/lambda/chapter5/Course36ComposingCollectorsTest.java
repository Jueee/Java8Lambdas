package com.java8.lambda.chapter5;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.SampleData;

class Course36ComposingCollectorsTest {

	Course36ComposingCollectors test = new Course36ComposingCollectors();
	
	@Test
	void test() {
		List<Album> albums = SampleData.getThreeAlbums();
		
		System.out.println("------计算一个艺术家的专辑数量-------");
		
		test.numberOfAlbumsSimple(albums)
			.forEach((key,value)->{
		    	System.out.println("[Artist]" + key.getName() + "\t[Value]" + value);
		    });
		
		test.numberOfAlbumsCollect(albums)
			.forEach((key,value)->{
		    	System.out.println("[Artist]" + key.getName() + "\t[Value]" + value);
		    });

		System.out.println("------计算一个艺术家的专辑名称-------");
		
		test.nameOfAlbumsSimple(albums)
		    .forEach((key,value)->{
		    	System.out.println("[Artist]" + key.getName() + "\t[Value]" + value);
		    });
		
		test.nameOfAlbumsCollect(albums)
		    .forEach((key,value)->{
		    	System.out.println("[Artist]" + key.getName() + "\t[Value]" + value);
		    });
	}

}
