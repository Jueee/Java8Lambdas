package com.java8.lambda.chapter9;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

import com.java8.lambda.chapter1.Artist;

/**
 * 	练习
 *	
 *	@author hzweiyongqiang
 */
public class Course10Exercises {
	/**
	 * 	使用 CompletableFuture 重构代码
	 *	该类从两个艺术家的名字中找出成员数更多的那个，如果第一个艺术家的成员多，返回 true ，否则返回 false 。
	 *	该类被注入一个 artistLookupService ，因为查找 Artist 的过程可能会耗费一定时间。
	 *	由于 BlockingArtistAnalyzer 类要依序调用两次查找服务，分析就会变慢，练习的目标就是加速这一过程。
	 *	@author hzweiyongqiang
	 */
	public class BlockingArtistAnalyzer{
		private final Function<String, Artist> artistLookupService;
		public BlockingArtistAnalyzer(Function<String, Artist> artistLookupService) {
			this.artistLookupService = artistLookupService;
		}
		public boolean isLargerGroup(String artistName,String otherArtistName) {
			return getNumberOfMembers(artistName) > getNumberOfMembers(otherArtistName);
		}
		private long getNumberOfMembers(String artistName) {
			return artistLookupService.apply(artistName)
									  .getMembers()
									  .count();
		}
	}
	
	
	
	
	/**
	 * 	需要实现的 ArtistAnalyzer 接口
	 * 	使用一个回调接口重构阻塞代码
	 */
	public interface ArtistAnalyzer{
		public void isLargerGroup(String artistName,String otherArtistName,Consumer<Boolean> handler);
	}
	
	/**
	 * 	使用回调重构代码
	 */
	public class CallbackArtistAnalyzer implements ArtistAnalyzer{
		private final Function<String, Artist> artistLookupService;
		public CallbackArtistAnalyzer(Function<String, Artist> artistLookupService) {
			this.artistLookupService = artistLookupService;
		}
		public void isLargerGroup(String artistName,String otherArtistName,Consumer<Boolean> handler) {
			boolean isLarger = getNumberOfMembers(artistName) > getNumberOfMembers(otherArtistName);
			handler.accept(isLarger);
		}
		private long getNumberOfMembers(String artistName) {
			return artistLookupService.apply(artistName)
									  .getMembers()
									  .count();
		}
	}
	/**
	 * 	使用 CompletableFuture 重构代码
	 */
	public class CompletableFutureArtistAnalyzer implements ArtistAnalyzer{
		private final Function<String, Artist> artistLookupService;
		public CompletableFutureArtistAnalyzer(Function<String, Artist> artistLookupService) {
			this.artistLookupService = artistLookupService;
		}
		public void isLargerGroup(String artistName,String otherArtistName,Consumer<Boolean> handler) {
			CompletableFuture<Long> otherArtistMemberCount = CompletableFuture.supplyAsync(() -> getNumberOfMembers(otherArtistName));
			CompletableFuture<Long> artistMemberCount = CompletableFuture.supplyAsync(() -> getNumberOfMembers(artistName));
			
			artistMemberCount.thenCombine(otherArtistMemberCount, (count,otherCount)-> count>otherCount)
							 .thenAccept(handler::accept);
		}
		private long getNumberOfMembers(String artistName) {
			return artistLookupService.apply(artistName)
									  .getMembers()
									  .count();
		}
	}
}
