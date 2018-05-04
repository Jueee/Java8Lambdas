package com.java8.lambda.chapter7;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter1.SampleData;
import com.java8.lambda.chapter7.Course3TestDoubles.OrderDomain;

class Course3TestDoublesTest {

	@Test
	void test() {
		Course3TestDoubles test = new Course3TestDoubles();
		
		Course13EverythingTwice course13EverythingTwice = new Course13EverythingTwice();
		List<Album> albums = SampleData.getThreeAlbums();

		OrderDomain orderDomain = test.new OrderDomain(course13EverythingTwice, albums);
		assertEquals(6, orderDomain.countFeature(album->2));
		
		/**
		 * 	使用 Lambda 表达式编写测试替身，传给 countFeature 方法
		 * 	对于 countFeature 方法的期望行为是为传入的专辑返回某个数值。
		 * 	这里传入 4 张专辑，测试存根中为每张专辑返回 2，然后断言该方法返回 8，即 2×4。
		 */
		OrderDomain order = test.new OrderDomain(course13EverythingTwice, Arrays.asList(
				new Album("Exile on Main St."),
				new Album("Beggars Banquet"),
				new Album("Aftermath"),
				new Album("Let it Bleed")));
		assertEquals(8, order.countFeature(album->2));
		
		/**
		 * 	结合 Mockito 框架使用 Lambda 表达式
		 * 
		 * 	多数的测试替身都很复杂，使用 Mockito 这样的框架有助于更容易地产生测试替身。
		 * 	考虑一种简单情形，为 List 生成测试替身。
		 * 	我们不想返回 List 本上的长度，而是返回另一个 List 的长度，为了模拟 List 的 size 方法；
		 * 	我们不想只给出答案，还想做一些操作，因此传入一个 Lambda 表达式
		 */
		List<Integer> otherList = Arrays.asList(1, 2, 3);
		List<String> list = mock(List.class);
		when(list.size()).thenAnswer(inv->otherList.size());	// Mockito 使用 Answer 接口允许用户提供其他行为
		assertEquals(3, list.size());
		
	}

}
