package com.java8.lambda.chapter3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.Track;

/**
 * 	重构遗留代码
 * 
 * 	编写示例代码的每一步都要进行单元测试，保证代码能够正常工作。
 * @author hzweiyongqiang
 *
 */
public class Course4Refactoring {

	// 假定选定一组专辑，找出其中所有长度大于 1 分钟的曲目名称。
	
	
	// 遗留代码：找出长度大于 1 分钟的曲目
	public Set<String> findLongTracks0(List<Album> albums){
		Set<String> trackNames = new HashSet<>();		// 保存找到的曲目名称
		for (Album album : albums) {					// 遍历所有专辑
			for (Track track : album.getTrackList()) {	// 遍历每张专辑上的每首曲目
				if (track.getLength() > 60) {			// 检查其长度是否大于 60 秒
					String name = track.getName();
					trackNames.add(name);
				}
			}
		}
		return trackNames;
	}
	
	// 重构的第一步：找出长度大于 1 分钟的曲目
	// 使用 Stream 的 forEach 方法替换掉 for 循环，但还是暂时保留原来循环体中的代码
	public Set<String> findLongTracks1(List<Album> albums){
		Set<String> trackNames = new HashSet<>();		// 保存找到的曲目名称
		albums.stream()
			  .forEach(album -> {						// 遍历所有专辑
				  album.getTracks()
				  	   .forEach(track->{				// 遍历每张专辑上的每首曲目
				  		   if (track.getLength() > 60) {// 检查其长度是否大于 60 秒
								String name = track.getName();
								trackNames.add(name);
							}
				  	   });
			  });
		return trackNames;
	}
	
	// 重构的第二步：找出长度大于 1 分钟的曲目
	
	// 最内层的 forEach 方法有三个功用：
	// 找出长度大于 1 分钟的曲目，得到符合条件的曲目名称，将曲目名称加入集合 Set 。
	
	// 这就意味着需要三项 Stream 操作：
	// 找出满足某种条件的曲目是 filter 的功能，得到曲目名称则可用 map 达成，终结操作可使用 forEach 方法将曲目名称加入一个集合。
	public Set<String> findLongTracks2(List<Album> albums){
		Set<String> trackNames = new HashSet<>();		// 保存找到的曲目名称
		albums.stream()
			  .forEach(album -> {						// 遍历所有专辑
				  album.getTracks()
				  	   .filter(track -> track.getLength() > 60)
				  	   .map(track -> track.getName())
				  	   .forEach(name -> trackNames.add(name));
			  });
		return trackNames;
	}
	
	// 重构的第三步：找出长度大于 1 分钟的曲目
	// 将专辑转化成一个曲目的 Stream
	// 任何时候想转化或替代代码，都该使用 map 操作。
	// 使用比 map 更复杂的 flatMap 操作，把多个 Stream 合并成一个 Stream 并返回。
	public Set<String> findLongTracks3(List<Album> albums){
		Set<String> trackNames = new HashSet<>();		// 保存找到的曲目名称
		
		albums.stream()
			  .flatMap(album -> album.getTracks())
		  	  .filter(track -> track.getLength() > 60)
		  	  .map(track -> track.getName())
		  	  .forEach(name -> trackNames.add(name));
		
		return trackNames;
	}
	
	// 重构的第四步：找出长度大于 1 分钟的曲目
	// 使用 collect(Collectors.toSet()) 将 Stream 中的值转换成一个集合。
	// 因此，将最后的 forEach 方法替换为 collect ，并删掉变量 trackNames 
	public Set<String> findLongTracks4(List<Album> albums){
		return albums.stream()
			  .flatMap(album -> album.getTracks())
		  	  .filter(track -> track.getLength() > 60)
		  	  .map(track -> track.getName())
		  	  .collect(Collectors.toSet());
	}
}
