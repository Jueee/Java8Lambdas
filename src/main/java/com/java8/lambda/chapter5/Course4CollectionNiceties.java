package com.java8.lambda.chapter5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;

/**
 * 	一些细节
 * 
 * 	构建 Map 时，为给定值计算键值是常用的操作之一，一个经典的例子就是实现一个缓存。
 * 	传统的处理方式是先试着从 Map 中取值，如果没有取到，创建一个新值并返回。
 *	
 *	@author Jue
 */
public class Course4CollectionNiceties {

	/**
	 * 	假设使用 Map<String, Artist> artistCache 定义缓存，我们需要使用费时的数据库操作查询艺术家信息
	 */
	Map<String, Artist> artistCache = new HashMap<>();
	
	/**
	 * 	使用显式判断空值的方式缓存
	 *	@param name
	 *	@return
	 */
	public Artist getArtist1(String name) {
		Artist artist = artistCache.get(name);
		if (artist == null) {
			artist = readArtistFromDB(name);
		}
		artistCache.put(name, artist);
		return artist;
	}
	
	/**
	 * 	使用 computeIfAbsent 缓存
	 * 	Java 8 引入了一个新方法 computeIfAbsent ，该方法接受一个 Lambda 表达式，值不存在时使用该 Lambda 表达式计算新值。
	 *	@param name
	 *	@return
	 */
	public Artist getArtist2(String name) {
		return artistCache.computeIfAbsent(name, this::readArtistFromDB);
	}
	
	/**
	 * 	数据库操作查询艺术家信息
	 *	@param name
	 *	@return
	 */
	public Artist readArtistFromDB(String name) {
		return SampleData.georgeHarrison;
	}
	
	/**
	 * 	在 Map 上迭代
	 */
	/**
	 * 	一种丑陋的迭代 Map 的方式
	 * 	创建一个 Map ，然后统计每个艺术家专辑的数量。
	 *	@param albumsByArtist
	 */
	public void iterationMap1(Map<Artist, List<Album>> albumsByArtist) {
		Map<Artist, Integer> countofAlbums = new HashMap<>();
		for (Map.Entry<Artist, List<Album>> entry:albumsByArtist.entrySet()) {
			Artist artist = entry.getKey();
			List<Album> albums = entry.getValue();
			countofAlbums.put(artist, albums.size());
		}
	}
	/**
	 * 	使用内部迭代遍历 Map 里的值	
	 * 	Java 8 为 Map 接口新增了一个 forEach 方法，该方法接受一个 BiConsumer 对象为参数（该对象接受两个参数，返回空）
	 *	@param albumsByArtist
	 */
	public void iterationMap2(Map<Artist, List<Album>> albumsByArtist) {
		Map<Artist, Integer> countofAlbums = new HashMap<>();
		albumsByArtist.forEach((artist,albums)->{
			countofAlbums.put(artist, albums.size());
		});
	}
}
