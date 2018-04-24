package com.java8.lambda.chapter4;

import java.util.IntSummaryStatistics;

import com.java8.lambda.chapter1.Album;

/**
 * 	基本类型
 * 
 * 	将基本类型转换为装箱类型，称为装箱，反之则称为拆箱，两者都需要额外的计算开销。
 * 	对于需要大量数值运算的算法来说，装箱和拆箱的计算开销，以及装箱类型占用的额外内存，会明显减缓程序的运行速度。
 * 
 * 	为了减小这些性能开销， Stream 类的某些方法对基本类型和装箱类型做了区分。
 * 	在 Java 8 中，仅对整型、长整型和双浮点型做了特殊处理，因为它们在数值计算中用得最多，特殊处理后的系统性能提升效果最明显。
 * 
 * @author hzweiyongqiang
 *
 */
public class Course02BasicType {

	public static void printTrackLengthStatistics(Album album) {
		IntSummaryStatistics trackLengthStats = album.getTracks()
													 .mapToInt(track->track.getLength())	// 使用对基本类型进行特殊处理的方法 mapToInt ，将每首曲目映射为曲目长度。
													 .summaryStatistics();					// 这个方法能计算出各种各样的统计值：
																							// 如 IntStream 对象内所有元素中的最小值、最大值、平均值以及数值总和。
		
		System.out.printf("max:%d,Min:%d,Ave:%f,Sum:%d",trackLengthStats.getMax(),
														trackLengthStats.getMin(),
														trackLengthStats.getAverage(),
														trackLengthStats.getSum());
	}
}
