package com.java8.lambda.chapter5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;

/**
 * 	组合收集器
 *	
 *	我们用到第二个收集器，用以收集最终结果的一个子集。这些收集器叫作下游收集器。
 *	
 *	收集器是生成最终结果的一剂配方，下游收集器则是生成部分结果的配方，主收集器中会用到下游收集器。
 *
 *	这种组合使用收集器的方式，使得它们在 Stream 类库中的作用更加强大。
 *
 *	@author hzweiyongqiang
 */
public class Course36ComposingCollectors {

	/**
	 * 	考虑如何计算一个艺术家的专辑数量。
	 */
	
	/**
	 * 	计算每个艺术家专辑数的简单方式
	 * 	一个简单的方案是使用前面的方法对专辑先分组后计数
	 *	@param albums
	 *	@return
	 */
	public Map<Artist, Integer> numberOfAlbumsSimple(List<Album> albums) {
		Map<Artist, List<Album>> albumsByArtist = albums.stream()
														.collect(Collectors.groupingBy(Album::getMainMusician));
		Map<Artist, Integer> numberOfAlbums = new HashMap<>();
		for(Entry<Artist, List<Album>> entry:albumsByArtist.entrySet()) {
			numberOfAlbums.put(entry.getKey(), entry.getValue().size());
		}
		return numberOfAlbums;
	}
	
	/**
	 * 	使用收集器计算每个艺术家的专辑数
	 * 	需要 counting 收集器，告诉 groupingBy 不用为每一个艺术家生成一个专辑列表，只需要对专辑计数就可以了。
	 * 	groupingBy 先将元素分成块，每块都与分类函数 getMainMusician 提供的键值相关联，然后使用下游的另一个收集器收集每块中的元素，最好将结果映射为一个 Map 。
	 *	@param albums
	 *	@return
	 */
	public Map<Artist, Long> numberOfAlbumsCollect(List<Album> albums) {
		return albums.stream()
					 .collect(Collectors.groupingBy(Album::getMainMusician,Collectors.counting()));
	}
	


	/**
	 * 	考虑如何计算一个艺术家的专辑名称。
	 */
	
	/**
	 * 	计算每个艺术家专辑数的简单方式
	 * 	一个简单的方案是先将专辑分组，然后再调整生成的 Map 中的值
	 *	@param albums
	 *	@return
	 */
	public Map<Artist, List<String>> nameOfAlbumsSimple(List<Album> albums) {
		Map<Artist, List<Album>> albumsByArtist = albums.stream()
														.collect(Collectors.groupingBy(Album::getMainMusician));
		Map<Artist, List<String>> numberOfAlbums = new HashMap<>();
		for(Entry<Artist, List<Album>> entry:albumsByArtist.entrySet()) {
			numberOfAlbums.put(entry.getKey(), entry.getValue()
													.stream()
													.map(Album::getName)
													.collect(Collectors.toList()));
		}
		return numberOfAlbums;
	}
	
	/**
	 * 	使用收集器求每个艺术家的专辑名
	 * 	我们需要有 mapping 方法，可以告诉 groupingBy 将它的值做映射，生成最终结果。
	 * 
	 * 	groupingBy 先将元素分成块，每块都与分类函数 getMainMusician 提供的键值相关联，然后使用下游的另一个收集器收集每块中的元素，最好将结果映射为一个 Map 。
	 * 	mapping 允许在收集器的容器上执行类似 map 的操作。但是需要指明使用什么样的集合类存储结果，比如 toList 。
	 *	@param albums
	 *	@return
	 */
	public Map<Artist, List<String>> nameOfAlbumsCollect(List<Album> albums) {
		return albums.stream()
					 .collect(Collectors.groupingBy(Album::getMainMusician,Collectors.mapping(Album::getName, Collectors.toList())));
	}
}
