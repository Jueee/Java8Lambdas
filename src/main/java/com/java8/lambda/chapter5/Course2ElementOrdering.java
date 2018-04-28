package com.java8.lambda.chapter5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

/**
 * 	元素顺序
 *	
 *	流是有序的，因为流中的元素都是按顺序处理的。这种顺序称为出现顺序。出现顺序的定义依赖于数据源和对流的操作。
 *
 *	一些操作在有序的流上开销更大，调用 unordered 方法消除这种顺序就能解决该问题。
 *	大多数操作都是在有序流上效率更高，比如 filter 、 map 和 reduce 等。
 *
 *	@author hzweiyongqiang
 */
public class Course2ElementOrdering {

	@Test
	public void testElementOrdering() {
		/**
		 * 	在一个有序集合中创建一个流时，流中的元素就按出现顺序排列。
		 */
		List<Integer> numbers1 = Arrays.asList(1,2,3,4);
		List<Integer> sameOrder1 = numbers1.stream().collect(Collectors.toList());
		// 顺序测试永远通过
		assertEquals(numbers1, sameOrder1);

		/**
		 * 	如果集合本身就是无序的，由此生成的流也是无序的。
		 */
		// 顺序测试不能保证每次通过
		Set<Integer> numbers2 = new HashSet<>(Arrays.asList(4,3,2,1));
		List<Integer> sameOrder2 = numbers2.stream().collect(Collectors.toList());
		assertNotEquals(Arrays.asList(4,3,2,1), sameOrder2);
		
		/**
		 * 	有些集合本身是无序的，但这些操作有时会产生顺序
		 */
		Set<Integer> numbers3 = new HashSet<>(Arrays.asList(4,3,2,1));
		List<Integer> sameOrder3 = numbers3.stream().sorted().collect(Collectors.toList());
		assertEquals(Arrays.asList(1,2,3,4), sameOrder3);
		
		/**
		 * 	一些中间操作会产生顺序，比如对值做映射时，映射后的值是有序的，这种顺序就会保留下来。
		 * 	如果进来的流是无序的，出去的流也是无序的。
		 */
		List<Integer> numbers4 = Arrays.asList(1,2,3,4);
		List<Integer> stillOrdered = numbers4.stream()
										   .map(x->x+1)
										   .collect(Collectors.toList());
		// 顺序得到了保留
		assertEquals(Arrays.asList(2,3,4,5), stillOrdered);
		Set<Integer> unordered = new HashSet<>(numbers4);
		List<Integer> stillUnordered = unordered.stream()
												.map(x->x+1)
												.collect(Collectors.toList());
		System.out.println("[stillUnordered]"+stillUnordered);
		// 顺序得不到保证
		// 只能断言 HashSet 中含有某元素，但对其顺序不能作出任何假设.
		// 因为 HashSet 是无序的，使用了映射操作后，得到的集合仍然是无序的。
		assertThat(stillUnordered, Matchers.hasItems(2));
		assertThat(stillUnordered, Matchers.hasItems(3));
		assertThat(stillUnordered, Matchers.hasItems(4));
		assertThat(stillUnordered, Matchers.hasItems(5));
	}
}
