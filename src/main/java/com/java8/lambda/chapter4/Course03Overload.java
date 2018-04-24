package com.java8.lambda.chapter4;

import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * 	在 Java 中可以重载方法，造成多个方法有相同的方法名，但签名确不一样。
 * 	这在推断参数类型时会带来问题，因为系统可能会推断出多种类型。
 * 	这时， javac 会挑出【最具体】的类型。
 * 
 * 
 * 	Lambda 表达式作为参数时，其类型由它的目标类型推导得出，推导过程遵循如下规则：
 * 	---	如果只有一个可能的目标类型，由相应函数接口里的参数类型推导得出； 
 * 	---	如果有多个可能的目标类型，由最具体的类型推导得出； 
 * 	---	如果有多个可能的目标类型且最具体的类型不明确，则需人为指定类型。 
 * 
 * @author hzweiyongqiang
 *
 */
public class Course03Overload {

	
	public static void overloadedMethod(Object o) {
		System.out.println("Object");
	}
	
	public static void overloadedMethod(String string) {
		System.out.println("String");
	}
	
	private interface IntegerBiFunction<Integer> extends BinaryOperator<Integer>{}
	
	public static void overloadedMethod(BinaryOperator<Integer> lambad) {
		System.out.println("BinaryOperator");
	}

	public static void overloadedMethod(IntegerBiFunction<Integer> lambad) {
		System.out.println("IntegerBiFunction");
	}
	
	private interface IntPredicate{
		public boolean test(int value);
	}

	public static void overloadedMethod(Predicate<Integer> predicate) {
		System.out.println("Predicate");
	}

	public static void overloadedMethod(IntPredicate predicate) {
		System.out.println("IntPredicate");
	}
	
	public static void main(String[] args) {
		// 调用重载方法时，输出 String ，而不是 Object 。
		Course03Overload.overloadedMethod("abc");
		
		// Java 推导出的 Lambda 表达式的类型正是最具体的函数接口的类型。
		Course03Overload.overloadedMethod((x,y) -> x + y);
		
		/**
		 * 	传入 overloadedMethod 方法的 Lambda 表达式和两个函数接口 Predicate 、 IntPredicate 在类型上都是匹配的。
		 * 	在这段代码块中，两种情况都定义了相应的重载方法，这时， javac 就无法编译，在错误报告中显示 Lambda 表达式被模糊调用。 
		 * 	IntPredicate 没有继承 Predicate ，因此编译器无法推断出哪个类型更具体。
		 * 
		 * 	error:The method overloadedMethod(Predicate<Integer>) is ambiguous for the type Course03Overload
		 */
//		Course03Overload.overloadedMethod((x) -> true);
		
		/**
		 * 	将 Lambda 表达式强制转换为 IntPredicate 或 Predicate<Integer> 类型可以解决这个问题
		 */
		Course03Overload.overloadedMethod((IntPredicate)(x) -> true);
		Course03Overload.overloadedMethod((Predicate<Integer>)(x) -> true);
	}
}
