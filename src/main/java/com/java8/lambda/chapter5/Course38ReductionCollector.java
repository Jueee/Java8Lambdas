package com.java8.lambda.chapter5;

import java.util.List;
import java.util.jar.Attributes.Name;
import java.util.stream.Collectors;

import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;

/**
 * 	对收集器的归一化处理
 * 
 * 	定制收集器其实不难，但如果你想为自己领域内的类定制一个收集器，不妨考虑一下其他替代方案。
 * 	最容易想到的方案是构建若干个集合对象，作为参数传给领域内类的构造函数。
 * 	如果领域内的类包含多种集合，这种方式又简单又适用。
 * 
 * 	如果领域内的类没有这些集合，需要在已有数据上计算，那这种方法就不合适了。
 * 	但即使如此，也不见得需要定制一个收集器。
 * 	你还可以使用 reducing 收集器，它为流上的归一操作提供了统一实现。
 *	
 *	@author Jue
 */
public class Course38ReductionCollector {

	/**
	 * 	reducing 是一种定制收集器的简便方式
	 * 	Collectors.reducing 的第二个参数，我们为流中每个元素创建了唯一的StringCombiner 。
	 * 	[注]这种方式非常低效，这也是我要定制收集器的原因之一。
	 *	@param artists
	 *	@return
	 */
	public static String getStringByReducing(List<Artist> artists) {
		return artists.stream()
			      .map(Artist::getName)
			      .collect(Collectors.reducing(
			    		  new StringCombiner(", ", "[", "]"),
			    		  name->new StringCombiner(", ", "[", "]").add(name),
			    		  StringCombiner::merge
			    		  ))
			      .toString();
	}
	
	public static void main(String[] args) {
		List<Artist> artists = SampleData.getThreeArtists();
		System.out.println(Course37RefactoringCollectors.getStringByFor(artists));
		System.out.println(Course38ReductionCollector.getStringByReducing(artists));
	}
}
