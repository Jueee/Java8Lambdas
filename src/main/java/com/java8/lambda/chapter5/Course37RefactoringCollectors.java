package com.java8.lambda.chapter5;

import java.util.List;

import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.SampleData;

/**
 * 	重构和定制收集器
 *	
 *	@author hzweiyongqiang
 */
public class Course37RefactoringCollectors {
	// 重构遗留代码

	/**
	 * 	需求：
	 * 	假设我们想将参与制作一张专辑的所有艺术家的名字输出为一个格式化好的列表
	 * 	以专辑 Let It Be 为例，期望的输出为：
	 *  "[George Harrison, John Lennon, Paul McCartney, Ringo Starr, The Beatles]" 。
	 */
	
	/**
	 * 	使用 for 循环格式化艺术家姓名
	 * 	来源：com.java8.lambda.chapter5.Course35Strings
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
	 * 	使用 forEach 和 StringBuilder 格式化艺术家姓名
	 * 	使用 map 操作，将包含艺术家的流映射为包含艺术家姓名的流。
	 *	@param artists
	 *	@return
	 */
	public static String getStringRefactoring1(List<Artist> artists) {
		StringBuilder builder = new StringBuilder("[");
		artists.stream()
			   .map(Artist::getName)
			   .forEach(name->{
				   if (builder.length() > 1) {
						builder.append(", ");
					}
					builder.append(name);
			   });
		builder.append("]");
		return builder.toString();
	}

	/**
	 * 	使用 reduce 和 StringBuilder 格式化艺术家姓名
	 * 	 reduce 操作生成艺术家姓名列表，艺术家与艺术家之间用“,”分隔
	 *	@param artists
	 *	@return
	 */
	public static String getStringRefactoring2(List<Artist> artists) {
		StringBuilder reduced = artists.stream()
									   .map(Artist::getName)
									   .reduce(new StringBuilder(),(builder,name)->{	// 创建一个 StringBuilder 对象，该对象是 reduce 操作的初始状态
										   if (builder.length() > 1) {					// 使用 Lambda 表达式将姓名连接到 builder 上
												builder.append(", ");
											}
											builder.append(name);
											return builder;
									   },(left,right) -> left.append(right));			//  reduce 操作的第三个参数也是一个 Lambda 表达式，接受两个 StringBuilder 对象做参数，将两者连接起来。
		reduced.insert(0, "[");			// 添加前缀
		reduced.append("]");			// 添加后缀
		return reduced.toString();
	}
	
	/**
	 * 	使用 reduce 和 StringCombiner 类格式化艺术家姓名
	 * 	使用一个 StringCombiner 类对细节进行抽象
	 *	@param artists
	 *	@return
	 */
	public static String getStringRefactoring3(List<Artist> artists) {
		StringCombiner reduced = artists.stream()
									    .map(Artist::getName)
									    .reduce(new StringCombiner(", ","[","]"),
										 	   	StringCombiner::add,
										 	   	StringCombiner::merge);			
		return reduced.toString();
	}
	
	/**
	 * 	使用 reduce 操作，将工作代理给 StringCombiner 对象
	 * 	在最后调用 toString 方法，将整个步骤串成一个方法链。
	 *	@param artists
	 *	@return
	 */
	public static String getStringRefactoring4(List<Artist> artists) {
		return artists.stream()
				      .map(Artist::getName)
				      .reduce(new StringCombiner(", ","[","]"),
					 	   		StringCombiner::add,
					 	   		StringCombiner::merge)
				      .toString();
	}
	
	/**
	 * 	使用定制的收集器 StringCollector 收集字符串
	 * 	将 reduce 操作重构为一个收集器 StringCollector，在程序中的任何地方都能使用。
	 *	@param artists
	 *	@return
	 */
	public static String getStringRefactoring5(List<Artist> artists) {
		return artists.stream()
				      .map(Artist::getName)
				      .collect(new StringCollector(", ", "[", "]"));
	}
	
	public static void main(String[] args) {
		List<Artist> artists = SampleData.getThreeArtists();
		System.out.println(Course37RefactoringCollectors.getStringByFor(artists));
		System.out.println(Course37RefactoringCollectors.getStringRefactoring1(artists));
		System.out.println(Course37RefactoringCollectors.getStringRefactoring2(artists));
		System.out.println(Course37RefactoringCollectors.getStringRefactoring3(artists));
		System.out.println(Course37RefactoringCollectors.getStringRefactoring4(artists));
		System.out.println(Course37RefactoringCollectors.getStringRefactoring5(artists));
	}
}

