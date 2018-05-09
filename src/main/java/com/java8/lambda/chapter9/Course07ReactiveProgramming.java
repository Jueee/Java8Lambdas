package com.java8.lambda.chapter9;

import java.util.List;
import java.util.stream.Collectors;

import com.java8.lambda.chapter1.Artist;

import rx.Observable;
import rx.Observer;

/**
 * 	响应式编程
 *	
 *	CompletableFuture 背后的概念可以从单一的返回值推广到数据流，这就是响应式编程。
 *	响应式编程其实是一种声明式编程方法，它让程序员以自动流动的变化和数据流来编程。
 *
 *	RxJava 类库将响应式的理念移植到了 JVM。
 *	RxJava 类库引入了一个叫作 Observable 的类，该类代表了一组待响应的事件，可以理解为一沓欠条。
 *	在 Observable 对象和 Stream 接口之间有很强的关联。
 *
 *	两种情况下，都需要使用 Lambda 表达式将行为和一般的操作关联、都需要将高阶函数链接起来定义完成任务的规则。
 *	
 *	实际上， Observable 定义的很多操作都和 Stream 的相同：map 、 filter 、 reduce 。
 *	最大的不同在于用例。Stream 是为构建内存中集合的计算流程而设计的，而 RxJava 则是为了组合异步和基于事件的系统流程而设计的。它没有取数据，而是把数据放进去。
 *	换个角度理解 RxJava，它是处理一组值，而 CompletableFuture 用来处理一个值。
 *	
 *	@author hzweiyongqiang
 */
public class Course07ReactiveProgramming {
	/**
	 * 	查找艺术家
	 */
	
	private final List<Artist> savedArtists;
	
	private final List<String> savedArtistNames;
	
	public Course07ReactiveProgramming(List<Artist> savedArtists) {
		this.savedArtists = savedArtists;
		this.savedArtistNames = savedArtists.stream()
											.map(Artist::getName)
											.collect(Collectors.toList());
	}
	
	/**
	 * 	通过名字和国籍查找艺术家
	 * 	search 方法根据名字和国籍过滤结果，它在本地缓存了一份艺术家名单，但必须从外部服务上查询艺术家信息，比如国籍。
	 *	@param searchedName
	 *	@param secrchedNationality
	 *	@param maxResults
	 *	@return
	 */
	public Observable<Artist> search(String searchedName,String secrchedNationality,int maxResults){
		return getSavedArtists()														// 取得一个包含艺术家姓名的 Observable 对象，该对象的高阶函数和 Stream 类似
			  .filter(name->name.contains(searchedName))								// 使用姓名做过滤，和使用 Stream 是一样的
			  .flatMap(this::lookupArtist)												// 将姓名替换为一个 Artist 对象
			  .filter(artist->artist.getNationality().contains(secrchedNationality))	// 使用国籍做过滤，和使用 Stream 是一样的
			  .take(maxResults);														// 在查找时限定返回结果的最大值： maxResults
	}

	public Observable<String> getSavedArtists() {
		return Observable.from(savedArtistNames);
	}
	
	public Observable<Artist> lookupArtist(String name){
		Artist required = savedArtists.stream()
									  .filter(artist->artist.getName().equals(name))
									  .findFirst()
									  .get();
		return Observable.from(required);
	}
	
	
	public void creationCodeSample() {
		Observer<String> observer = null;
		
		/**
		 * 	给 Observable 对象传值，并且完成它
		 * 	调用 onCompleted 方法表示任务完成
		 */
		observer.onNext("a");
		observer.onNext("b");
		observer.onNext("c");
		observer.onCompleted();
		
		/**
		 * 	通知 Observable 对象有错误发生
		 * 	Observable 能处理异常，如果出现错误，调用 onError 方法
		 */
		observer.onError(new Exception());
	}
}
