package com.java8.lambda.chapter2;

import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

public class Course2Discern {
	
	public static void main(String[] args) {
		// Lambda 表达式不包含参数，使用空括号 () 表示没有参数
		Runnable noArguments = () -> System.out.println("Hello World");
		
		// Lambda 表达式包含且只包含一个参数，可省略参数的括号
		ActionListener oneArgument = event -> System.out.println("button clicked");
		
		// Lambda 表达式的主体不仅可以是一个表达式，而且也可以是一段代码块，使用大括号（ {} ）将代码块括起来
		Runnable multiStatement = () -> {
			System.out.println("Hello");
			System.out.println("World");
		};
		
		// Lambda 表达式也可以表示包含多个参数的方法
		// 这行代码并不是将两个数字相加，而是创建了一个函数，用来计算两个数字相加的结果。
		// 变量 add 的类型是 BinaryOperator<Long> ，它不是两个数字的和，而是将两个数字相加的那行代码。
		BinaryOperator<Long> add = (x,y) -> x + y;
		
		// 有时最好也可以显式声明参数类型，此时就需要使用小括号将参数括起来
		BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
		
		// 等号右边的代码并没有声明类型，系统根据上下文推断出类型信息
		final String[] array = {"hello","world"};
	}
}
