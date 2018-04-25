package com.java8.lambda.chapter4;

/**
 * 	多重继承
 * 
 * 	接口允许多重继承，因此有可能碰到两个接口包含签名相同的默认方法的情况。
 * 
 * 	如果对默认方法的工作原理，特别是在多重继承下的行为没有把握，如下三条简单的定律可以帮助大家。
 * 	1. 类胜于接口。如果在继承链中有方法体或抽象的方法声明，那么就可以忽略接口中定义的方法。
 * 	2. 子类胜于父类。如果一个接口继承了另一个接口，且两个接口都定义了一个默认方法，那么子类中定义的方法胜出。
 * 	3. 没有规则三。如果上面两条规则不适用，子类要么需要实现该方法，要么将该方法声明为抽象方法。
 * 	[注]其中第一条规则是为了让代码向后兼容。
 * 
 * 	@author hzweiyongqiang
 */
public class Course07MultExtends {

	public interface JukeBox{
		public default String rock() {
			return "... all over the world!";
		}
	}
	
	public interface Carriage{
		public default String rock() {
			return "... from side to side!";
		}
	}
	/**
	 * 	此时， javac 并不明确应该继承哪个接口中的方法，因此编译器会报错：
	 * 	Duplicate default methods named rock with the parameters () and () are inherited from the types Course07MultExtends.JukeBox and Course07MultExtends.Carriage
	 * 	@author hzweiyongqiang
	 *
	public class MusicalCarriage implements Carriage,JukeBox{
		
	}
	 */
	
	/**
	 * 	在类中实现 rock 方法就能解决这个问题
	 * 	该例中使用了增强的 super 语法，用来指明使用接口 Carriage 中定义的默认方法。
	 * 	@author hzweiyongqiang
	 */
	public class MusicalCarriage implements Carriage,JukeBox{
		@Override
		public String rock() {
			return JukeBox.super.rock();
		}
	}
	
	public static void main(String[] args) {
		Course07MultExtends test = new Course07MultExtends();
		MusicalCarriage carriage = test.new MusicalCarriage();
		System.out.println(carriage.rock());
	}
}
