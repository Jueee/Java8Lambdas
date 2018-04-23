package com.java8.lambda.chapter3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;

/**
 * 	练习
 * 	
 * @author hzweiyongqiang
 *
 */
public class Course9Exercises {

	/**
	 * 	编写一个求和函数，计算流中所有数之和。
	 * @param numbers
	 * @return
	 */
	public int exercises1a(Stream<Integer> numbers) {
		return numbers.reduce(0, (a,b) -> a + b);
	}

	/**
	 * 	编写一个函数，接受艺术家列表作为参数
	 * 	返回一个字符串列表，其中包含艺术家的姓名和国籍
	 * @param artists
	 * @return
	 */
	public List<String> exercises1b(List<Artist> artists) {
		return artists.stream()
					  .map(artist -> artist.getName()+"--"+artist.getNationality())
					  .collect(Collectors.toList());
	}
	
	/**
	 * 	编写一个函数，接受专辑列表作为参数
	 * 	返回一个由最多包含 3 首歌曲的专辑组成的列表。
	 * @param albums
	 * @return
	 */
	public List<Album> exercises1c(List<Album> albums) {
		return albums.stream()
					 .filter(album -> album.getTrackList().size() <= 3)
					 .collect(Collectors.toList());
	}
	
	/**
	 * 	修改如下代码，将外部迭代转换成内部迭代
	 * @param artists
	 * @return
	 */
	public int exercises2example(List<Artist> artists) {
		int totalMembers = 0;
		for (Artist artist : artists) {
			Stream<Artist> members = artist.getMembers();
			totalMembers += members.count();
		}
		return totalMembers;
	}
	
	public int exercises2(List<Artist> artists) {
		return artists.stream()
					  .map(artist -> artist.getMembers().count())
					  .reduce(0L, Long::sum)
					  .intValue();
	}
	
	/**
	 * 	计算一个字符串中小写字母的个数
	 * @param string
	 * @return
	 */
	public static long exercises7(String string) {
		return string.chars()
					 .filter(Character::isLowerCase)
					 .count();
	}
	
	/**
	 * 	在一个字符串列表中，找出包含最多小写字母的字符串。
	 * @param strings
	 * @return
	 */
	public Optional<String> exercises8(List<String> strings){
		return strings.stream()
					  .max(Comparator.comparing(Course9Exercises::exercises7));
	}
	
	
	/**
	 * 	只用 reduce 和 Lambda 表达式写出实现 Stream 上的 map 操作的代码
	 * @param stream
	 * @param mapper
	 * @return
	 */
	public static <I,O> List<O> mapUsingReduce(Stream<I> stream,Function<I, O> mapper){
		return stream.reduce(new ArrayList<O>(), (acc,x) -> {
			List<O> newAcc = new ArrayList<>(acc);
			newAcc.add(mapper.apply(x));
			return newAcc;
		},(List<O> left,List<O> right) -> {
			List<O> newLeft = new ArrayList<>(left);
			newLeft.addAll(right);
			return newLeft;
		});
	} 
	
	/**
	 * 	只用 reduce 和 Lambda 表达式写出实现 Stream 上的 filter 操作的代码
	 * @param stream
	 * @param predicate
	 * @return
	 */
	public static <I> List<I> filterUsingReduce(Stream<I> stream,Predicate<I> predicate){
		List<I> initial = new ArrayList<>();
		return stream.reduce(initial,
							 (List<I> acc, I x) -> {
								 if(predicate.test(x)) {
									 List<I> newAcc = new ArrayList<>(acc);
									 newAcc.add(x);
									 return newAcc;
								 } else {
									 return acc;
								 }
							 },
							 Course9Exercises::combineLists);
	}
	
	private static <I> List<I> combineLists(List<I> left, List<I> right){
		List<I> newLeft = new ArrayList<>(left);
		newLeft.addAll(right);
		return newLeft;
	}
}
