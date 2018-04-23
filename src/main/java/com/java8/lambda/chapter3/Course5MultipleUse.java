package com.java8.lambda.chapter3;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;

/**
 * 	多次调用流操作
 * 
 * 	用户也可以选择每一步强制对函数求值，而不是将所有的方法调用链接在一起，但是，最好不要如此操作。
 * 
 * 
 * 	相比有如下缺点：
 * 	1、代码可读性差，样板代码太多，隐藏了真正的业务逻辑； 
 * 	2、效率差，每一步都要对流及早求值，生成新的集合； 
 * 	3、代码充斥一堆垃圾变量，它们只用来保存中间结果，除此之外毫无用处； 
 * 	4、难于自动并行化处理。
 *  
 * @author hzweiyongqiang
 *
 */
public class Course5MultipleUse {

	/**
	 * 	误用 Stream 的例子
	 * @param album
	 * @return
	 */
	public Set<String> errorMultipleUse(Album album) {
		List<Artist> musicians = album.getMusicians().collect(Collectors.toList());
		List<Artist> bands = musicians.stream()
									  .filter(artist->artist.getName().startsWith("The"))
									  .collect(Collectors.toList());
		Set<String> origins = bands.stream()
								   .map(artist -> artist.getNationality())
								   .collect(Collectors.toSet());
		return origins;
	}

	/**
	 * 	符合 Stream 使用习惯的链式调用
	 * @param album
	 * @return
	 */
	public Set<String> correctMultipleUse(Album album) {
		Set<String> origins = album.getMusicians()
								   .filter(artist->artist.getName().startsWith("The"))
								   .map(artist -> artist.getNationality())
								   .collect(Collectors.toSet());
		return origins;
	}
}
