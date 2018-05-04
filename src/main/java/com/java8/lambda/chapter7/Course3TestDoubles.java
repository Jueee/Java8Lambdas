package com.java8.lambda.chapter7;

import java.util.List;

import com.java8.lambda.chapter1.Album;
import com.java8.lambda.chapter7.Course13EverythingTwice.OrderStreamNew;

/**
 * 	在测试替身时使用Lambda表达式
 *	
 *	编写单元测试的常用方式之一是使用测试替身描述系统中其他模块的期望行为。
 *	这种方式很有用，因为单元测试可以脱离其他模块来测试你的类或方法，测试替身让你能用单元测试来实现这种隔离。
 *
 *	测试替身也常被称为模拟，事实上测试存根和模拟都属于测试替身。区别是模拟可以验证代码的行为。
 *
 *	测试代码时，使用 Lambda 表达式的最简单方式是实现轻量级的测试存根。
 *	如果交互的类本身就是一个函数接口，实现这样的存根就非常简单和自然。
 *
 *	@author hzweiyongqiang
 */
public class Course3TestDoubles {


	class OrderDomain extends OrderStreamNew{
		
		public OrderDomain(Course13EverythingTwice course13EverythingTwice, List<Album> albums) {
			course13EverythingTwice.super(albums);
		}
		
	}
}
