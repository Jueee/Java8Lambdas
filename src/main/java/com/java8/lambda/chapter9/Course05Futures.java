package com.java8.lambda.chapter9;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Artist;
import com.java8.lambda.chapter1.Track;

/**
 * 	Future
 *	
 *	Future 是构建复杂并行操作的一种方案 。 
 *	Future 像一张欠条，方法不是返回一个值，而是返回一个 Future 对象，该对象第一次创建时没有值，但以后能拿它“换回”一个值。
 *	调用 Future 对象的 get 方法获取值，它会阻塞当前线程，直到返回值。
 *
 *	@author hzweiyongqiang
 */
public class Course05Futures {
	/**
	 * 	我们要考虑的场景是从外部网站查找某专辑的信息。
	 * 	我们需要找出专辑上的曲目列表和艺术家，还要保证有足够的权限访问登录等各项服务，或者至少确保已经登录。
	 */
	/**
	 * 	测试结果：
	 * 	将会看到，如果要将 Future 对象的结果传给其他任务，会阻塞当前线程的执行。
	 * 	这会成为一个性能问题，任务不是平行执行了，而是（意外地）串行执行。
	 * 
	 * 	这意味着在登录两个服务之前，我们无法启动任何查找任务。
	 * 	没必要这样：lookupTracks 只需要自己的登录凭证， lookupArtists 也是一样。
	 * 
	 * 	查询操作不必等待所有登录操作完成后才能执行，参考图 Course05Futures.jpg。
	 * 
	 * 	可以将对 get 的调用放到 lookupTracks 和 lookupArtists 方法的中间，这能解决问题，但是代码丑陋，而且无法在多次调用之间重用登录凭证。
	 */
	
	/**
	 * 	
	 */
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
	
	public class FutureAlbumLookup implements AlbumLookup{
		private final ExecutorService service = Executors.newFixedThreadPool(2);
		private final List<Track> tracks;
		private final List<Artist> artists;
		public FutureAlbumLookup(List<Track> tracks,List<Artist> artists) {
			this.tracks = tracks;
			this.artists = artists;
		}
		/**
		 * 	使用 Future 从外部网站下载专辑信息
		 */
		@Override
		public Album lookupByName(String albumName) {
			// 登录提供曲目和艺术家信息的服务，这时会返回一个 Future<Credentials> 对象，该对象包含登录信息
			// Future 接口支持泛型，可将 Future<Credentials> 看作是 Credentials 对象的一张欠条。
			Future<Credentials> trackLogin = loginTo("track");
			Future<Credentials> artistLogin = loginTo("artist");
			
			try {
				// 使用登录后的凭证查询曲目和艺术家信息，通过调用 Future 对象的 get 方法获取凭证信息。
				Future<List<Track>> tracks = lookupTracks(albumName, trackLogin.get());
				Future<List<Artist>> artists = lookupArtists(albumName, artistLogin.get());
				
				// 构建待返回的专辑对象，这里同样调用 get 方法以阻塞 Future 对象。
				return new Album(albumName, tracks.get(), artists.get());
			} catch (InterruptedException|ExecutionException e) {
				// 如果有异常，将其转化为一个待解问题域内的异常，然后将其抛出。
				throw new AlbumLookupException(e.getCause());
			}
		}
		
		private Future<List<Artist>> lookupArtists(String albumName,Credentials credentials) {
			return service.submit(()->{
				fakeWaitingForExternalWebService();
				return artists;
			});
		}
		
		private Future<List<Track>> lookupTracks(String albumName,Credentials credentials) {
			return service.submit(()->{
				return tracks;
			});
		}
		
		public Future<Credentials> loginTo(String serviceName){
			return service.submit(()->{
				if ("track".equals(serviceName)) {
					fakeWaitingForExternalWebService();
				}
				return new Credentials();
			});
		}
		
		private void fakeWaitingForExternalWebService() throws InterruptedException {
			Thread.sleep(1000);
		}
	}
}
