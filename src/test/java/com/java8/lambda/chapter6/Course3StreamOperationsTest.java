package com.java8.lambda.chapter6;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.SampleData;

class Course3StreamOperationsTest {
	
	Course3StreamOperations test = new Course3StreamOperations();

	@Test
	void test() {
		/**
		 * 	在一个四核电脑上，如果有 10 张专辑，串行化代码的速度是并行化代码速度的 8 倍；
		 * 	如果将专辑数量增至 100 张，串行化和并行化速度相当；
		 * 	如果将专辑数量增值 10 000 张，则并行化代码的速度是串行化代码速度的 2.5 倍。
		 * 
		 * 	输入流的大小并不是决定并行化是否会带来速度提升的唯一因素，性能还会受到编写代码的方式和核的数量的影响。
		 */
		List<Album> albums = SampleData.getThreeAlbums();
		
		long startTime1 = System.nanoTime();
		System.out.println(test.serialArraySum(albums));
		System.out.println("[UseTime]"+(System.nanoTime() - startTime1));
		
		long startTime2 = System.nanoTime();
		System.out.println(test.parallelArraySum(albums));
		System.out.println("[UseTime]"+(System.nanoTime() - startTime2));
	}

}
