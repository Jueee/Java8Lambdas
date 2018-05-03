package com.java8.lambda.chapter6;

import java.util.List;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Track;

/**
 * 	并行化流操作
 * 	
 * 	并行化操作流只需改变一个方法调用。
 * 	如果已经有一个 Stream 对象，调用它的 parallel 方法就能让其拥有并行操作的能力。
 * 	如果想从一个集合类创建一个流，调用 parallelStream 就能立即获得一个拥有并行能力的流。
 *	
 *	@author hzweiyongqiang
 */
public class Course3StreamOperations {

	/**
	 * 	计算一组专辑的曲目总长度
	 * 	拿到每张专辑的曲目信息，然后得到曲目长度，最后相加得出曲目总长度。
	 */
	/**
	 * 	串行化计算专辑曲目长度
	 *	@param albums
	 *	@return
	 */
	public int serialArraySum(List<Album> albums) {
		return albums.stream()
					 .flatMap(Album::getTracks)
					 .mapToInt(Track::getLength)
					 .sum();
	}
	
	/**
	 * 	并行化计算专辑曲目长度
	 *	@param albums
	 *	@return
	 */
	public int parallelArraySum(List<Album> albums) {
		return albums.parallelStream()
					 .flatMap(Album::getTracks)
					 .mapToInt(Track::getLength)
					 .sum();
	}
	
	
}
