package com.java8.lambda.chapter7;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.SampleData;

/**
 * 	孤独的覆盖
 *	
 *	这个情况是指使用继承，其目的只是为了覆盖一个方法。
 *
 *	ThreadLocal 就是一个很好的例子。 
 *	ThreadLocal 能创建一个工厂，为每个线程最多只产生一个值。
 *	这是确保非线程安全的类在并发环境下安全使用的一种简单方式。
 *
 *	@author hzweiyongqiang
 */
public class Course12LonelyOverride {
	
	Course12LonelyOverride databases = new Course12LonelyOverride();
	/**
	 * 	模拟在在数据库中查询一个艺术家的操作
	 *	@return
	 */
	public Album lookupCurrentAlbum() {
		return SampleData.aLoveSupreme;
	}
	
	/**
	 * 	在数据库中查找艺术家，但希望每个线程只做一次这种查询
	 */
	ThreadLocal<Album> thisAlbum1 = new ThreadLocal<Album>() {
		protected Album initialValue() {
			return databases.lookupCurrentAlbum();
		};
	};
	
	/**
	 * 	使用工厂方法
	 * 	在 Java 8 中，可以为工厂方法 withInitial 传入一个 Supplier 对象的实例来创建对象
	 * 	任何已有的 Supplier<Album> 实例不需要重新封装，就可以在此使用，这鼓励了重用和组合。
	 */
	ThreadLocal<Album> thisAlbum2 = ThreadLocal.withInitial(() -> databases.lookupCurrentAlbum());
}
