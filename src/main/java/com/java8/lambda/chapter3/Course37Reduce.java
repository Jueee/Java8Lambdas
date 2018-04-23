package com.java8.lambda.chapter3;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author hzweiyongqiang
 *
 */
public class Course37Reduce {

	@Test
	public void reduce() {
		/**
		 * 
		 *  reduce 模式
		 *  
		 *  reduce 操作可以实现从一组值中生成一个值。
		 *  
		 * 	首先赋给 accumulator 一个初始值： initialValue 
		 * 	然后在循环体中，通过调用 combine 函数，拿 accumulator 和集合中的每一个元素做运算
		 *	再将运算结果赋给 accumulator ，最后 accumulator 的值就是想要的结果。
		 *  
		Object accumulator = initialValue;
		for(Object element:collection) {
			accumulator = combine(accumulator, element);
		}
		 */
		
		// 使用 reduce 求和
		// Lambda 表达式的返回值是最新的 acc ，是上一轮 acc 的值和当前元素相加的结果。
		//  reducer 的类型是 BinaryOperator 。
		int count = Stream.of(1,2,3,4)
						.reduce(0, (acc,element) -> acc + element);
		assertEquals(10, count);
		
		// 展开 reduce 操作
		BinaryOperator<Integer> accumulator = (acc, element) -> acc + element;
		int count2 = accumulator.apply(
						accumulator.apply(
							accumulator.apply(0, 1),
						2),
					3);
		System.out.println(count2);
		assertEquals(6, count2);
		
		// 使用命令式编程方式求和
		// 在命令式编程方式下，每一次循环将集合中的元素和累加器相加，用相加后的结果更新累加器的值。
		int acc = 0;
		for(Integer element : Arrays.asList(1,2,3)) {
			acc = acc + element;
		}
		assertEquals(6, acc);
	}
}
