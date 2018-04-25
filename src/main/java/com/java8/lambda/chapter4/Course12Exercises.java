package com.java8.lambda.chapter4;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.java8.lambda.chapter1.Artist;

/**
 * 	练习
 *	
 *	@author hzweiyongqiang
 */
public class Course12Exercises {
	static Course12Exercises test = new Course12Exercises();
	/**
	 * 	在所示的 Performance 接口基础上，添加 getAllMusicians 方法，
	 * 	该方法返回包含所有艺术家名字的 Stream，如果对象是乐队，则返回每个乐队成员的名字。
	 * 	例如，如果 getMusicians 方法返回甲壳虫乐队，则 getAllMusicians 方法返回乐队名和乐队成员，如约翰 · 列侬、保罗 · 麦卡特尼等。
	 */
	public interface PerFormance{
		public String getName();
		public Stream<Artist> getMusicians();
	}
	
	public class ExerciseNotCompletedException extends RuntimeException{
		public ExerciseNotCompletedException() {
			super("Please remove this line of code and implement the exercise");
		}
	}
	
	public static class Exercises{
		public static <T> T replaceThisWithSolution() {
			throw test.new ExerciseNotCompletedException();
		}
	}
	
	public interface PerFormanceFixed{
		public String getName();
		public Stream<Artist> getMusicians();
		public default Stream<Artist> getAllMusicians(){
			return Course12Exercises.Exercises.replaceThisWithSolution();
		}
	}
	
	public interface PerFormanceFixedAmswers{
		public String getName();
		public Stream<Artist> getMusicians();
		public default Stream<Artist> getAllMusicians(){
			return getMusicians().flatMap(artist-> Stream.concat(Stream.of(artist), artist.getMembers()));
		}
	}
	
	
	
	/**
	 * 	Artists 类表示了一组艺术家
	 * 	重构该类，使得 getArtist 方法返回一个 Optional<Artist> 对象。
	 * 	如果索引在有效范围内，返回对应的元素，否则返回一个空 Optional 对象。
	 * 	此外，还需重构 getArtistName 方法，保持相同的行为。
	 */
	public class Artists{
		private List<Artist> artists;
		
		public Artists(List<Artist> artists) {
			this.artists = artists;
		}
		
		public Artist getArtist(int index) {
			if (index < 0 || index >= artists.size()) {
				indexException(index);
			}
			return artists.get(index);
		}
		
		private void indexException(int index) {
			throw new IllegalArgumentException(index + " doesn't correspond to an Artist");
		}
		
		public String getArtistName(int index) {
			try {
				Artist artist = getArtist(index);
				return artist.getName();
			} catch (Exception e) {
				return "unknown";
			}
		}
	}
	
	public class ArtistsFixed{
		private List<Artist> artists;
		
		public ArtistsFixed(List<Artist> artists) {
			this.artists = artists;
		}
		
		public Optional<Artist> getArtist(int index) {
			if (index < 0 || index >= artists.size()) {
				return Optional.empty();
			}
			return Optional.of(artists.get(index));
		}
		
		private void indexException(int index) {
			throw new IllegalArgumentException(index + " doesn't correspond to an Artist");
		}
		
		public String getArtistName(int index) {
			Optional<Artist> artist = getArtist(index);
			return artist.map(Artist::getName).orElse("unknown");
		}
	}
	
}
