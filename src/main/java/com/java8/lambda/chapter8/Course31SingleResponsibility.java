package com.java8.lambda.chapter8;

import java.util.stream.IntStream;

/**
 * 	单一功能原则
 * 	程序中的类或方法只能有一个改变的理由。
 */
/**
 *	当软件的需求发生变化，实现这些功能的类和方法也需要变化。
 *	如果你的类有多个功能，一个功能引发的代码变化会影响该类的其他功能。
 *	这可能会引入缺陷，还会影响代码演进的能力。
 *
 *	一个类不仅要功能单一，而且还需将功能封装好。
 *
 *	@author hzweiyongqiang
 */
public class Course31SingleResponsibility {

	/**
	 * 	计算质数个数，一个方法里塞进了多重职责
	 *	@param upTo
	 *	@return
	 */
	public long countPrimes(int upTo) {
		long tally = 0;
		for (int i = 1; i < upTo; i++) {
			boolean isPrime = true;			// 判断一个数是否是质数
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrime = false;
				}
			}
			if (isPrime) {					// 计数
				tally ++;
			}
		}
		return tally;
	}
	
	/**
	 * 	将 isPrime 重构成另外一个方法后，计算质数个数的方法
	 *	@param upTo
	 *	@return
	 */
	public long countPrimes2(int upTo) {
		long tally = 0;
		for (int i = 1; i < upTo; i++) {
			if (isPrime2(i)) {				// 计数
				tally ++;
			}
		}
		return tally;
	}
	public boolean isPrime2(int number) {	// 判断一个数是否是质数
		for (int j = 2; j < number; j++) {
			if (number % j == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 	代码中的大部分都在对数字循环，如果我们遵守单一功能原则，那么迭代过程应该封装起来。
	 * 	改进代码还有一个现实的原因，如果需要对一个很大的 upTo 计数，我们希望可以并行操作。
	 */
	
	/**
	 * 	使用集合流重构质数计数程序，将循环操作交给类库本身处理。
	 *	@param upTo
	 *	@return
	 */
	public long countPrimes3(int upTo) {
		return IntStream.range(1, upTo)		// 计数
						.filter(this::isPrime3)
						.count();
				
	}
	public boolean isPrime3(int number) {	// 判断一个数是否是质数
		return IntStream.range(2, number)
						.allMatch(x->(number % x) != 0);
	}
	
	/**
	 * 	如果我们想利用更多 CPU 加速计数操作，可使用 parallelStream 方法，而不需要修改任何其他代码
	 */
	
	/**
	 * 	并行运行基于集合流的质数计数程序
	 *	@param upTo
	 *	@return
	 */
	public long countPrimes4(int upTo) {
		return IntStream.range(1, upTo)		// 计数
						.parallel()
						.filter(this::isPrime4)
						.count();
				
	}
	public boolean isPrime4(int number) {	// 判断一个数是否是质数
		return IntStream.range(2, number)
						.allMatch(x->(number % x) != 0);
	}
}
