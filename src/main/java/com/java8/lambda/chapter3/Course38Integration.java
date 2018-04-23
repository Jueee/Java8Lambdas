package com.java8.lambda.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.Track;

/**
 * 	整合操作
 * @author hzweiyongqiang
 *
 */
public class Course38Integration {

	
	@Test
	public void integration() {
		// 目标：找出某张专辑上所有乐队的国籍
		
		/**
		 *	将这个问题分解为如下几个步骤
		 *	1. 找出专辑上的所有表演者。
		 *	2. 分辨出哪些表演者是乐队。
		 *	3. 找出每个乐队的国籍。
		 *	4. 将找出的国籍放入一个集合。
		 */
		
		/**
		 * 	找出每一步对应的 Stream API 
		 * 	1. Album 类有个 getMusicians 方法，该方法返回一个 Stream 对象，包含整张专辑中所有的表演者；
		 * 	2. 使用 filter 方法对表演者进行过滤，只保留乐队
		 * 	3. 使用 map 方法将乐队映射为其所属国家
		 * 	4. 使用 collect(Collectors.toList()) 方法将国籍放入一个列表。
		 */
		
		List<Track> tracks = new ArrayList<>();
		List<Artist> musicians = new ArrayList<>();
		Album album = new Album("test Album", tracks, musicians);
		
		// 只要调用 List 或 Set 的 stream 方法就能得到一个 Stream 对象。
		// 通过 Stream 暴露集合的最大优点在于，它很好地封装了内部实现的数据结构。
		// 仅暴露一个 Stream 接口，用户在实际操作中无论如何使用，都不会影响内部的 List 或 Set 。
		Set<String> origins = album.getMusicians()
								   .filter(artist->artist.getName().startsWith("The"))
								   .map(artist->artist.getNationality())
								   .collect(Collectors.toSet());
		System.out.println(origins.size());
		
	}
}
