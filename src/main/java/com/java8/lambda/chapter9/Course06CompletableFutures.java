package com.java8.lambda.chapter9;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.Track;

/**
 * 	CompletableFutures
 *	
 *	我们真正需要的是不必调用 get 方法阻塞当前线程，就能操作 Future 对象返回的结果。
 *	我们需要将 Future 和回调结合起来使用。即使用 CompletableFuture 。
 *	
 *	CompletableFuture 结合了 Future 对象打欠条的主意和使用回调处理事件驱动的任务。
 *	其要点是可以组合不同的实例，而不用担心末日金字塔问题。
 *
 *	CompletableFuture API 的技巧是注册 Lambda 表达式，并且把高阶函数链接起来。
 *	方法不同，但道理和 Stream API 的设计是相通的。
 *
 *	创建 CompletableFuture 对象分两部分：创建对象和传给它欠客户代码的值。
 */
/**
 *	CompletableFutures 接口的一些用例：
 *	1、如果你想在链的末端执行一些代码而不返回任何值，比如  Consumer 和 Runnable ，那就看看 thenAccept 和 thenRun 方法。
 *	2、可使用  thenApply 方法转换 CompletableFuture 对象的值，有点像使用Stream的 map 方法。
 *	3、在  CompletableFuture 对象出现异常时，可使用 exceptionally 方法恢复，可以将一个函数注册到该方法，返回一个替代值。
 *	4、如果你想有一个  map ，包含异常情况和正常情况，请使用 handle 方法。
 *	5、要找出  CompletableFuture 对象到底出了什么问题，可使用 isDone 和 isCompletedExceptionally 方法辅助调查。
 *
 *	@author hzweiyongqiang
 */
public class Course06CompletableFutures {
	/**
	 * 	重写类 Course05Futures 中的方法
	 */
	
	/***/
	public class Credentials {}
	
	public static interface AlbumLookup{
		Album lookupByName(String albumName);
	}
	
	public class AlbumLookupException extends RuntimeException{
		private static final long serialVersionUID = 1L;
		public AlbumLookupException(Throwable cause) {
			super(cause);
		}
		public AlbumLookupException(String message) {
			super(message);
		}
	}
	
	public class CompletableFutureAlbumLookup implements AlbumLookup{
		private final ExecutorService service = Executors.newFixedThreadPool(2);
		private final List<Track> tracks;
		private final List<Artist> artists;
		public CompletableFutureAlbumLookup(List<Track> tracks,List<Artist> artists) {
			this.tracks = tracks;
			this.artists = artists;
		}
		/**
		 * 	使用 Future 从外部网站下载专辑信息
		 */
		@Override
		public Album lookupByName(String albumName) {
			// 使用 thenCompose 方法将 Credentials 对象转换成包含艺术家信息的 CompletableFuture 对象
			CompletableFuture<List<Artist>> artistLookup = loginTo("artist").thenCompose(artistLogin -> lookupArtists(albumName, artistLogin));	
			
			return loginTo("track").thenCompose(trackLogin->lookupTracks(albumName, trackLogin))						// 通过登录 Track API，将 Credentials 对象转换成包含曲目信息的 CompletableFuture 对象
								   .thenCombine(artistLookup, (tracks,artists)->new Album(albumName, tracks, artists))	// 将一个 CompletableFuture 对象的结果和另一个 CompletableFuture 对象组合起来
								   .join();																				// join 的作用和 get 方法是一样的，而且它没有使用 get 方法时令人倒胃口的检查异常
		}
		
		private CompletableFuture<List<Artist>> lookupArtists(String albumName,Credentials credentials) {
			return CompletableFuture.supplyAsync(()->{			// 创建	CompletableFuture 实例
				fakeWaitingForExternalWebService();
				return artists;
			},service);
		}
		
		private CompletableFuture<List<Track>> lookupTracks(String albumName,Credentials credentials) {
			return CompletableFuture.completedFuture(tracks);
		}
		
		public CompletableFuture<Credentials> loginTo(String serviceName){
			return CompletableFuture.supplyAsync(()->{			// 创建	CompletableFuture 实例
				if ("track".equals(serviceName)) {
					fakeWaitingForExternalWebService();
				}
				return new Credentials();
			},service);
		}
		
		private void fakeWaitingForExternalWebService() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		private Track track;
		private Artist artist;
		/**
		 * 	为 Future 提供值
		 *	@param id
		 *	@return
		 */
		CompletableFuture<Artist> createFuture(String id){
			CompletableFuture<Artist> future = new CompletableFuture<>();
			startJob(future);
			return future;
		}
		/**
		 * 	为 Future 提供一个值，完成工作
		 *	@param future
		 */
		private void startJob(CompletableFuture<Artist> future) {
			future.complete(artist);
		}
		
		CompletableFuture<Track> lookupTrack(String id) {
		    return CompletableFuture.supplyAsync(() -> {		// 创建	CompletableFuture 实例
		        // Some expensive work is done here <1>
		        // supplyAsync 方法接受一个 Supplier 对象作为参数，然后执行它
		    	// 能执行一些耗时的任务，同时不会阻塞当前线程
		        return track; // <2>
		    }, service); // 返回值用来完成 CompletableFuture 
		}
		/**
		 * 	出现错误时完成 Future
		 * 	completeExceptionally ，用于处理异常情况。
		 * 	该方法可以视作 complete 方法的备选项，但不能同时调用 complete 和 completeExceptionally 方法。
		 *	@param future
		 *	@param name
		 */
		private void processExceptionally(CompletableFuture<Album> future, String name) {
			future.completeExceptionally(new AlbumLookupException("Unable to find " + name));
	    }
	}
}
