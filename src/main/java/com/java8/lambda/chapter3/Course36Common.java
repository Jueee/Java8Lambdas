package com.java8.lambda.chapter3;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Track;

/**
 * 
 * @author hzweiyongqiang
 *
 */
public class Course36Common {

	@Test
	public void maxmin() {
		List<Track> tracks = Arrays.asList(	new Track("BaKai", 524),
											new Track("Violets for Your Furs", 378),
											new Track("Time Was", 451));
		
		// 使用 Stream 查找最短曲目
		Track shortTrack = tracks.stream()
				.min(Comparator.comparing(track -> track.getLength()))
				.get();
		
		assertEquals(tracks.get(1), shortTrack);
		
		// 使用 for 循环查找最短曲目
		Track shortTrack2 = tracks.get(0);
		for (Track track : tracks) {
			if (track.getLength() < shortTrack2.getLength()) {
				shortTrack2 = track;
			}
		}
		assertEquals(tracks.get(1), shortTrack2);
	}
}
