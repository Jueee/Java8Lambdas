package com.java8.lambda.chapter8.lambdabehave;

import java.util.Stack;

/**
 * 	描述 Stack 的案例（实现后的 Java BDD 框架）
 *
 * 	执行结果：

	a stack
		should be empty when created
		should push new elements onto the top of the stack
		should pop the last element pushed onto the stack[expected:<2> but was:<1>]

 *	@author hzweiyongqiang
 */
public class StackSpec {
	
	/**
	 * 	在类定义的开头和结尾使用了双括号
	 * 	是一个匿名构造函数，可以执行任意的 Java 代码块
	 * 	
	 * 	等价于一个完整的构造函数，只是少了一些样板代码。
	 * 	这段代码也可以写作：
		public class StackSpec {
			public StackSpec() {
				...
			}
		}
	 */
	{
		// 使用动词 describe 为套件起头，然后定义一个名字表明这是描述什么东西的行为
		
		Lets.describe("a stack", it -> {				// it 指正在描述的对象

	        it.should("be empty when created", expect -> {
	            expect.that(new Stack()).isEmpty();		// 使用 expect.that 做前缀，描述期待的行为
	            										// 检查规则时，会从命令行得到一个简单的报告，表明是否有规则失败。
	        });

	        it.should("push new elements onto the top of the stack", expect -> {
	            Stack<Integer> stack = new Stack<>();
	            stack.push(1);

	            expect.that(stack.get(0)).isEqualTo(1);
	        });

	        it.should("pop the last element pushed onto the stack", expect -> {
	            Stack<Integer> stack = new Stack<>();
	            stack.push(2);
	            stack.push(1);

	            expect.that(stack.pop()).isEqualTo(2);
	        });
	        
	    });
	}
	
	
}
