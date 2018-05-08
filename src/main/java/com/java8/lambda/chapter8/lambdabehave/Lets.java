package com.java8.lambda.chapter8.lambdabehave;

public final class Lets {
	/**
	 * 	从 describe 方法开始定义规则
	 * 	为套件创建一个 Description 实例，在此处理各种各样的规则。 
	 * 	Description 类就是我们定义的 DSL 中的 it 
	 *	@param name
	 *	@param behavior
	 */
	public static void describe(String name, Suite behavior) {
		Description description = new Description(name);
		behavior.specifySuite(description);
	}
}
