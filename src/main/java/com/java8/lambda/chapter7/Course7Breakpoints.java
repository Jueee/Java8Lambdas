package com.java8.lambda.chapter7;

import java.util.Set;
import java.util.stream.Collectors;

import com.java8.lambda.chapter1.Album;

/**
 * 	在流中间设置断点
 *	
 *	记录日志这是 peek 方法的用途之一。
 *
 *	为了像调试循环那样一步一步跟踪，可在 peek 方法中加入断点，这样就能逐个调试流中的元素了。
 *
 *	此时， peek 方法可知包含一个空的方法体，只要能设置断点就行。
 *	
 *	有一些调试器不允许在空的方法体中设置断点，此时，我将值简单地映射为其本身，这样就有地方设置断点了，虽然这样做不够完美，但只要能工作就行。
 *
 *	@author hzweiyongqiang
 */
public class Course7Breakpoints {

	public Set<String> getNationalityByPeek(Album album){
		return album.getMusicians()
					.filter(artist->artist.getName().startsWith("The"))
					.map(artist->artist.getNationality())
					.peek(nation -> {})						// 包含一个空的方法体，设置断点
					.collect(Collectors.toSet());
	}
}
