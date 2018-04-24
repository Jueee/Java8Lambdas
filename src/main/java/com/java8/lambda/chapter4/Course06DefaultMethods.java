package com.java8.lambda.chapter4;

import static org.junit.Assert.assertEquals;

/**
 * 	默认方法
 * 
 * 	和类不同，接口没有成员变量，因此默认方法只能通过调用子类的方法来修改子类本身，避免了对子类的实现做出各种假设。
 * 
 * 	增加默认方法主要是为了在接口上向后兼容。让类中重写方法的优先级高于默认方法能简化很多继承问题。
 * 
 * 
 * @author hzweiyongqiang
 *
 */
public class Course06DefaultMethods {

	// 默认方法示例： forEach 实现方式
	/**
	 * 
	 * 
	default void forEach(Consumer<? super T> action) {
		for(T t:this) {
			action.accept(t);
		}
	}
	
	 */
	
	public interface Parent{
		public void message(String body);
		public default void welcome() {
			message("Parent: Hi!");
		}
		public String getLastMessage();
	}
	
	class ParentImpl implements Parent{
		private String body;
		@Override
		public void message(String body) {
			this.body = body;
		}
		@Override
		public String getLastMessage() {
			return body;
		}
	}
	
	public interface Child extends Parent{
		@Override
		default void welcome() {
			message("Child: Hi!");
		}
	}
	
	class ChildImpl extends ParentImpl implements Child{}
	
	class OverridingParent extends ParentImpl{
		@Override
		public void welcome() {
			message("OverridingParent: Hi!");
		}
	}
	
	class OverridingChile extends OverridingParent implements Child{}
	
	
	public static void main(String[] args) {
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
