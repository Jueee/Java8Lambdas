package com.java8.lambda.chapter7;

import java.util.List;
import java.util.function.ToLongFunction;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Track;

/**
 * 	同样的东西写两遍
 * 
 * 
 * 	不要重复你劳动（Don’t Repeat Yourself，DRY）是一个众所周知的模式，它的反面是同样的东西写两遍（Write Everything Twice，WET）。
 *	这种代码异味多见于重复的样板代码，产生了更多需要测试的代码，这样的代码难于重构，一改就坏。
 *
 *	如果有一个整体上大概相似的模式，只是行为上有所不同，就可以试着加入一个 Lambda 表达式。
 *	@author hzweiyongqiang
 */
public class Course13EverythingTwice {

	/**
	 * 	增加一个简单的 Order 类
	 * 	来计算用户购买专辑的一些有用属性，如计算音乐家人数、曲目和专辑时长等。
	 */
	/**
	 * 	Order 类的命令式实现
	 * 	每个方法里，都有样板代码将每个专辑里的属性和总数相加，比如每首曲目的长度或音乐家的人数。
	 *	@author hzweiyongqiang
	 */
	class Order{
		List<Album> albums;
		
		public Order(List<Album> albums) {
			this.albums = albums;
		}
		public long countRunningTime() {
			long count = 0;
			for(Album album:albums) {
				for(Track track : album.getTrackList()) {
					count += track.getLength();
				}
			}
			return count;
		}
		public long countMusicians() {
			long count = 0;
			for(Album album:albums) {
				count += album.getMusicianList().size();
			}
			return count;
		}
		public long countTracks() {
			long count = 0;
			for(Album album:albums) {
				count += album.getTrackList().size();
			}
			return count;
		}
	}

	/**
	 * 	使用流重构命令式的 Order 类
	 *	然而这段代码仍然有重用可读性的问题，因为有一些抽象和共性只能使用领域内的知识来表达。
	 *	@author hzweiyongqiang
	 */
	class OrderStream{
		List<Album> albums;
		
		public OrderStream(List<Album> albums) {
			this.albums = albums;
		}
		public long countRunningTime() {
			return albums.stream()
						 .mapToLong(album->album.getTracks()
								 				.mapToLong(Track::getLength)
								 				.sum())
						 .sum();	
		}
		public long countMusicians() {
			return albums.stream()
						 .mapToLong(album->album.getMusicians().count())
						 .sum();
		}
		public long countTracks() {
			return albums.stream()
					 	 .mapToLong(album->album.getTracks().count())
					 	 .sum();
		}
	}
	
	/**
	 * 	使用领域方法重构 Order 类
	 *	实现一个函数：返回一个 long ，统计所有专辑的某些特征，还需要一个 Lambda 表达式，告诉我们统计专辑上的什么信息。
	 *	ToLongFunction：它的类型随参数类型，因此我们要使用的类型为 ToLongFunction<Album> 。
	 *	@author hzweiyongqiang
	 */
	class OrderStreamNew{
		List<Album> albums;
		
		public OrderStreamNew(List<Album> albums) {
			this.albums = albums;
		}
		public long countFeature(ToLongFunction<Album> function) {
			return albums.stream()
						 .mapToLong(function)
						 .sum();
		}
		public long countRunningTime() {
			return countFeature(album->album.getTracks()
							 				.mapToLong(Track::getLength)
							 				.sum());	
		}
		public long countMusicians() {
			return countFeature(album->album.getMusicians().count());
		}
		public long countTracks() {
			return countFeature(album->album.getTracks().count());
		}
	}
}
