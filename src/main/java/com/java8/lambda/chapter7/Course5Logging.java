package com.java8.lambda.chapter7;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;

/**
 * 	日志和打印消息
 *	
 *	假设你要在集合上进行大量操作，你要调试代码，你希望看到每一步操作的结果是什么。
 *	可以在每一步打印出集合中的值，这在流中很难做到，因为一些中间步骤是惰性求值的。
 *
 *	解决方案参考下一节：Course6Peek
 *
 *	@author hzweiyongqiang
 */
public class Course5Logging {
	/**
	 * 	找出了专辑上每位艺术家来自哪个国家。
	 * 	将找到的国家信息记录到日志中。
	 */
	
	/**
	 * 	记录中间值，以便调试 for 循环
	 *	@param album
	 *	@return
	 */
	public Set<String> getNationalityByFor(Album album){
		Set<String> nationalitys = new HashSet<>();
		for (Artist artist:album.getMusicianList()) {
			if (artist.getName().startsWith("The")) {
				String nationality = artist.getNationality();
				System.out.println("[ByFor]Found nationality:"+nationality);
				nationalitys.add(nationality);
			}
		}
		return nationalitys;
	}
	
	/**
	 * 	使用 forEach 记录中间值，这种方式有点幼稚
	 * 
	 * 	使用 forEach 方法打印出流中的值，这同时会触发求值过程。
	 * 	这样的操作有个缺点：我们无法再继续操作流了，流只能使用一次。如果我们还想继续，必须重新创建流。
	 *	@param album
	 *	@return
	 */
	public Set<String> getNationalityByForEach(Album album){
		album.getMusicians()
		  	 .filter(artist->artist.getName().startsWith("The"))
			 .map(artist->artist.getNationality())
			 .forEach(nationality->System.out.println("[ByForEach]Found:"+nationality));
		return album.getMusicians()
					.filter(artist->artist.getName().startsWith("The"))
					.map(artist->artist.getNationality())
					.collect(Collectors.toSet());
	}
}
