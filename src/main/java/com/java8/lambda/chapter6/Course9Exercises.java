package com.java8.lambda.chapter6;

import java.util.List;

/**
 * 	练习
 *	
 *	@author hzweiyongqiang
 */
public class Course9Exercises {

	/**
	 * 	顺序求列表中数字的平方和
	 *	@param values
	 *	@return
	 */
	public int sequentialSumOfSquares(List<Integer> values) {
		return values.stream()
					 .mapToInt(x->x*x)
					 .sum();
	}
	/**
	 * 	并行计算列表中数字的平方和
	 *	@param values
	 *	@return
	 */
	public int parallelSumOfSquares(List<Integer> values) {
		return values.parallelStream()
					 .mapToInt(x->x*x)
					 .sum();
	}
	
	
	/**
	 * 	把列表中的数字相乘，然后再将所得结果乘以 5。
	 * 	顺序执行这段程序没有问题，但并行执行时有一个缺陷，使用流并行化执行该段代码，并修复缺陷。
	 *	@param linkedList
	 *	@return
	 */
	public int multiplyThrough(List<Integer> linkedList) {
		return linkedList.stream()
						 .reduce(5, (acc,x)->x*acc);
	}
	/**
	 * 	改进版，并行计算，把列表中的数字相乘，然后再将所得结果乘以 5。
	 *	@param linkedList
	 *	@return
	 */
	public int multiplyThroughByParallel(List<Integer> linkedList) {
		return linkedList.parallelStream()
						 .reduce(1, (acc,x)->x*acc)
						 .intValue() * 5;
	}
	
	/**
	 * 	求列表元素的平方和，该实现方式性能不高
	 * 	尝试改进代码性能，但不得牺牲代码质量。
	 *	@param linkedList
	 *	@return
	 */
	public int slowSumOfSquares(List<Integer> linkedList) {
		return linkedList.parallelStream()
						 .map(x->x*x)
						 .reduce(0, (acc,x)->acc+x);
	}
	/**
	 * 	改进版，并行计算，求列表元素的平方和
	 *	@param linkedList
	 *	@return
	 */
	public int slowSumOfSquares2(List<Integer> linkedList) {
		return linkedList.parallelStream()
						 .mapToInt(x->x*x)
						 .sum();
	}
}
