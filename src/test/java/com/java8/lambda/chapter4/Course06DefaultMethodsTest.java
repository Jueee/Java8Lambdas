package com.java8.lambda.chapter4;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class Course06DefaultMethodsTest {

	@Test
	void test() {
		Course06DefaultMethods test = new Course06DefaultMethods();
		
		// 在客户代码中使用默认方法
		Course06DefaultMethods.Parent parent = test.new ParentImpl();
		parent.welcome();
		System.out.println(parent.getLastMessage());
		assertEquals("Parent: Hi!", parent.getLastMessage());
		
		// 调用 Child 接口的客户代码
		Course06DefaultMethods.Child child = test.new ChildImpl();
		child.welcome();
		System.out.println(child.getLastMessage());
		assertEquals("Child: Hi!", child.getLastMessage());
		
		// 调用的是类中的具体方法，而不是默认方法
		Course06DefaultMethods.Parent parent2 = test.new OverridingParent();
		parent2.welcome();
		System.out.println(parent2.getLastMessage());
		assertEquals("OverridingParent: Hi!", parent2.getLastMessage());
		
		// 类中重写的方法优先级高于接口中定义的默认方法
		Course06DefaultMethods.Child child2 = test.new ChildImpl();
		child2.welcome();
		System.out.println(child2.getLastMessage());
		assertEquals("Child: Hi!", child2.getLastMessage());
	}

}
