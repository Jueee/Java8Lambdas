package com.java8.lambda.chapter5;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;

/**
 * 	转换成值
 * 
 * 	利用收集器让流生成一个值。 
 * 	maxBy 和 minBy 允许用户按某种特定的顺序生成一个值。
 *	
 *	@author hzweiyongqiang
 */
public class Course32ToValues {

	/**
	 * 	找出成员最多的乐队
	 *	@param artists
	 *	@return
	 */
	public Optional<Artist> biggestGroup(List<Artist> artists){
		// 使用一个 Lambda 表达式，将艺术家映射为成员数量
		Function<Artist, Long> getCount = artist -> artist.getMembers().count();
		// 定义一个比较器，并将比较器传入 maxBy 收集器。
		return artists.stream().collect(Collectors.maxBy(Comparator.comparing(getCount)));
	}
	
	/**
	 * 	找出一组专辑上曲目的平均数
	 *	@param albums
	 *	@return
	 */
	public double averageNumberOfTracks(List<Album> albums) {
		// averagingInt 方法接受一个 Lambda 表达式作参数，将流中的元素转换成一个整数，然后再计算平均数。
		return albums.stream()
					 .collect(Collectors.averagingInt(album->album.getTrackList().size()));
	}
	
}
