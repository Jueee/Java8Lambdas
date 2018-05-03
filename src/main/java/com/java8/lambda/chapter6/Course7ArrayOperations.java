package com.java8.lambda.chapter6;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 	并行化数组操作
 *	
 *	Java 8 还引入了一些针对数组的并行操作，脱离流框架也可以使用 Lambda 表达式。
 *	像流框架上的操作一样，这些操作也都是针对数据的并行化操作。
 *
 *	数组上的并行化操作：（这些操作都在工具类 Arrays 中）
 *		方法名				操作
 *		parallelPrefix 		任意给定一个函数，计算数组的和
 *		parallelSetAll 		使用 Lambda 表达式更新数组元素
 *		parallelSort 		并行化对数组元素排序
 *
 *
 *	@author hzweiyongqiang
 */
public class Course7ArrayOperations {

	/**
	 * 	使用 for 循环初始化数组
	 *	@param size
	 *	@return
	 */
	public double[] imperativeInitilize(int size) {
		double[] values = new double[size];
		for (int i = 0; i < values.length; i++) {
			values[i] = i;
		}
		return values;
	}
	
	/**
	 * 	使用并行化数组操作初始化数组
	 *	@param size
	 *	@return
	 */
	public double[] parallelInitilize(int size) {
		double[] values = new double[size];		// 提供了一个用于操作的数组
		Arrays.parallelSetAll(values, i->i);	// 传入一个 Lambda 表达式，根据数组下标计算元素的值
		return values;
	}
	
	/**
	 * 	计算简单滑动平均数
	 * 	计算方法：对于一个给定的数列，首先设定一个固定的值k，然后分别计算第1项到第k项，第2项到第k+1项，第3项到第k+2项的平均值，依次类推。
	 *	@param values
	 *	@param n		时间窗口的大小，我们据此计算滑动平均值
	 *	@return
	 */
	public double[] simpleMovingAverage(double[] values,int n) {
		double[] sums = Arrays.copyOf(values, values.length);			// 由于要使用的并行操作会改变数组内容，为了不修改原有数据，复制一份输入数据。
		Arrays.parallelPrefix(sums, Double::sum);						// 执行并行操作，将数组的元素相加。现在 sums 变量中保存了求和结果。
		
		// 现在有了和，就能计算出时间窗口中的和了，减去窗口起始位置的元素即可，除以 n 即得到平均值。
		int start = n - 1;
		return IntStream.range(start, sums.length)						// 使用 Intstream.range	得到包含所需元素下标的流。
						.mapToDouble(i->{
							double prefix = i == start ? 0 : sums[i -n];
							return (sums[i] - prefix) / n;				// 使用总和减去窗口起始值，然后再除以 n 得到平均值
						})
						.toArray();										// 将流转换为数组
	}
}
