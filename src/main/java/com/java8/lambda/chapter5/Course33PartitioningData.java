package com.java8.lambda.chapter5;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.java8.lambda.chapter1.Artist;

/**
 * 	数据分块
 *	
 *	收集器 partitioningBy ，它接受一个流，并将其分成两部分。
 *
 *	它使用 Predicate 对象判断一个元素应该属于哪个部分，并根据布尔值返回一个 Map 到列表。
 *	因此，对于 true List 中的元素， Predicate 返回 true ；对其他 List 中的元素， Predicate 返回 false 。
 *
 *
 *	@author hzweiyongqiang
 */
public class Course33PartitioningData {

	/**
	 * 	将艺术家组成的流分成乐队和独唱歌手两部分
	 *	@param artists
	 *	@return
	 */
	public Map<Boolean, List<Artist>> bandsAndSolo(List<Artist> artists){
		return artists.stream()
					  .collect(Collectors.partitioningBy(Artist::isSolo));
	}
}
