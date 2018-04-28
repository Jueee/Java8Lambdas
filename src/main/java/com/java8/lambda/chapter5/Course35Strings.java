package com.java8.lambda.chapter5;

import java.util.List;
import java.util.stream.Collectors;

import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;

/**
 * 	字符串
 * 	
 * 	很多时候，收集流中的数据都是为了在最后生成一个字符串。
 *	
 *	可以使用 Collectors.joining 收集流中的值
 *	该方法可以方便地从一个流得到一个字符串，允许用户提供分隔符（用以分隔元素）、前缀和后缀。
 *
 *	@author hzweiyongqiang
 */
public class Course35Strings {

	/**
	 * 	假设我们想将参与制作一张专辑的所有艺术家的名字输出为一个格式化好的列表
	 * 	以专辑 Let It Be 为例，期望的输出为：
	 *  "[George Harrison, John Lennon, Paul McCartney, Ringo Starr, The Beatles]" 。
	 */
	
	/**
	 * 	使用 for 循环格式化艺术家姓名
	 *	@param artists
	 *	@return
	 */
	public static String getStringByFor(List<Artist> artists) {
		StringBuilder builder = new StringBuilder("[");
		for (int i = 0; i < artists.size(); i++) {
			if (i > 0) {
				builder.append(", ");
			}
			String name = artists.get(i).getName();
			builder.append(name);
		}
		builder.append("]");
		return builder.toString();
	}
	
	/**
	 * 	使用流和收集器格式化艺术家姓名
	 *	@param artists
	 *	@return
	 */
	public static String getStringByStream(List<Artist> artists) {
		return artists.stream()
					  .map(Artist::getName)							// 使用 map 操作提取出艺术家的姓名
					  .collect(Collectors.joining(", ", "[", "]"));	// 使用 Collectors.joining 收集流中的值
	}
	
	public static void main(String[] args) {
		List<Artist> artists = SampleData.getThreeArtists();
		System.out.println(Course35Strings.getStringByFor(artists));
		System.out.println(Course35Strings.getStringByStream(artists));
	}
	
}
