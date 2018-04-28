package com.java8.lambda.chapter5;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;

/**
 * 	数据分组
 *	
 *	数据分组是一种更自然的分割数据操作，与将数据分成 ture 和 false 两部分不同，可以使用任意值对数据分组。
 *
 *	groupingBy 收集器接受一个分类函数，用来对数据分组，就像 partitioningBy 一样，接受一个 Predicate 对象将数据分成 ture 和 false 两部分。
 *
 *	我们使用的分类器是一个 Function 对象，和 map 操作用到的一样。
 *
 *	@author hzweiyongqiang
 */
public class Course34GroupingData {

	/**
	 * 	使用主唱对专辑分组
	 *	@param albums
	 *	@return
	 */
	public Map<Artist, List<Album>> albumsByArtist(List<Album> albums){
		return albums.stream()
					 .collect(Collectors.groupingBy(Album::getMainMusician));
	}
	
	/**
	 * 	改写 数据分块 Course33PartitioningData 中的函数
	 * 
	 * 	将艺术家组成的流分成乐队和独唱歌手两部分
	 *	@param artists
	 *	@return
	 */
	public Map<Boolean, List<Artist>> bandsAndSolo(List<Artist> artists){
		return artists.stream()
					  .collect(Collectors.groupingBy(Artist::isSolo));
	}
}
