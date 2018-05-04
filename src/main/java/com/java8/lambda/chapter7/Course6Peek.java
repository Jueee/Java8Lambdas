package com.java8.lambda.chapter7;

import java.util.Set;
import java.util.stream.Collectors;

import com.java8.lambda.chapter1.Album;

/**
 * 	解决方案： peek
 *	
 *	流有一个方法让你能查看每个值，同时能继续操作流。这就是 peek 方法。
 *	
 *	使用 peek 方法还能以同样的方式，将输出定向到现有的日志系统中，比如 log4j 、 java.util.logging 或者 slf4j 。
 *
 *	@author hzweiyongqiang
 */
public class Course6Peek {

	/**
	 * 	使用 peek 方法记录中间值
	 * 	使用 peek 方法重写了 Course5Logging 的例子，输出流中的值，同时避免了重复的流操作。
	 *	@param album
	 *	@return
	 */
	public Set<String> getNationalityByPeek(Album album){
		return album.getMusicians()
					.filter(artist->artist.getName().startsWith("The"))
					.map(artist->artist.getNationality())
					.peek(nation->System.out.println("[ByPeek]Found:"+nation))
					.collect(Collectors.toSet());
	}
}
