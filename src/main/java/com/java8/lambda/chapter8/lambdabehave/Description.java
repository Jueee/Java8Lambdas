package com.java8.lambda.chapter8.lambdabehave;

import com.java8.lambda.chapter8.lambdabehave.expectations.Expect;
/**
 * 	来回传递的 Description 实例
 *	
 *	@author hzweiyongqiang
 */
public final class Description{
	private final String suite;
	public Description(String suite) {
		this.suite = suite;
	}
	/**
	 * 	真正做事的地方
	 * 	该方法通过调用 specifySuite 执行 Lambda 表达式
	 * 
	 * 	如果规则失败，会抛出一个标准的 Java  AssertionError ，而其他任何 Throwable 对象则认为是一个错误
	 * 
	 *	@param description
	 *	@param specification
	 */
	public void should(String description,Specification specification) {
		try {
	        Expect expect = new Expect();
	        specification.specifyBehaviour(expect);
	        Runner.current.recordSuccess(suite, description);
	    } catch (AssertionError cause) {
	        Runner.current.recordFailure(suite, description, cause);
	    } catch (Throwable cause) {
	        Runner.current.recordError(suite, description, cause);
	    }
	}
}
