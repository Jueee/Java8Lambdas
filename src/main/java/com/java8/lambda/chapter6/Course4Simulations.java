package com.java8.lambda.chapter6;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 	模拟系统
 *	
 *	并行化流操作的用武之地是使用简单操作处理大量数据，比如模拟系统。
 *
 *	本节我们会搭建一个简易的模拟系统来理解摇骰子，但其中的原理对于大型、真实的系统也适用。
 *
 *	@author hzweiyongqiang
 */
public class Course4Simulations {
	/**
	 * 	解决问题：
	 * 	如果公平地掷两次骰子，然后将赢的一面上的点数相加，就会得到一个 2~12 的数字。
	 * 	我们想要得出点数落在 2~12 之间每个值的概率。
	 */
	/**
	 * 	解决该问题的方法之一是求出掷骰子的所有组合
	 * 	比如，得到 2 点的方式是第一次掷得 1 点，第二次也掷得 1 点。总共有 36 种可能的组合，因此，掷得 2 点的概率就是 1/36。
	 */
	/**
	 * 	我们这里要讨论的是蒙特卡洛模拟法。
	 * 	蒙特卡洛模拟法会重复相同的模拟很多次，每次模拟都使用随机生成的种子。
	 * 	每次模拟的结果都被记录下来，汇总得到一个对系统的全面模拟。
	 * 
	 * 	模拟投掷骰子的次数越多，得到的结果越准确，因此，我们希望尽可能多地增加模拟次数。
	 * 
	 * 	[注]手动实现并行化蒙特卡洛模拟法的代码参见 ManualDiceRolls 类。
	 */
	
	/**
	 * 	使用蒙特卡洛模拟法并行化模拟掷骰子事件
	 *	@param N	代表模拟次数
	 *	@return
	 */
	public Map<Integer, Double> parallelDiceRolls(int N){
		double fraction = 1.0 / N;
		return IntStream.range(0, N)				// 使用 IntStream 的 range 方法创建大小为 N 的流
						.parallel()					// 调用 parallel 方法使用流的并行化操作
						.mapToObj(twoDiceThrows())	// 使用	mapToObj 方法以便在流上使用该函数
						.collect(Collectors.groupingBy(side->side,			// 得到了需要合并的所有结果的流，使用 groupingBy 方法将点数一样的结果合并。
								Collectors.summingDouble(n->fraction)));	// 将数字映射为 1/N 并且相加
	}
	
	/**
	 * 	使用蒙特卡洛模拟法串行化模拟掷骰子事件
	 *	@param N
	 *	@return
	 */
	public Map<Integer, Double> serialDiceRolls(int N){
		double fraction = 1.0 / N;
		return IntStream.range(0, N)				// 使用 IntStream 的 range 方法创建大小为 N 的流
						.mapToObj(twoDiceThrows())	// 使用	mapToObj 方法以便在流上使用该函数
						.collect(Collectors.groupingBy(side->side,			// 得到了需要合并的所有结果的流，使用 groupingBy 方法将点数一样的结果合并。
								Collectors.summingDouble(n->fraction)));	// 将数字映射为 1/N 并且相加
	}
	
	/**
	 * 	模拟连续掷两次骰子事件，返回值是两次点数之和
	 *	@return
	 */
	public IntFunction<Integer> twoDiceThrows(){
		return i->{
			ThreadLocalRandom random = ThreadLocalRandom.current();
			int firstThrow = random.nextInt(1,7);
			int secondThrow = random.nextInt(1,7);
			return firstThrow + secondThrow;
		};
	}
}
