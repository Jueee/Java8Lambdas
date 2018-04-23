package com.java8.lambda.chapter3;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Track;

/**
 * Stream 上常用的操作之一是求最大值和最小值。 
 * Stream API 中的 max 和 min 操作足以解决这一问题。
 * 
 * @author hzweiyongqiang
 *
 */
public class Course35MaxMin {

	@Test
	public void maxmin() {
		List<Track> tracks = Arrays.asList(	new Track("BaKai", 524),
											new Track("Violets for Your Furs", 378),
											new Track("Time Was", 451));
		
		Track shortTrack = tracks.stream()
				.min(Comparator.comparing(track -> track.getLength()))
				.get();
		
		assertEquals(tracks.get(1), shortTrack);
	}
}
