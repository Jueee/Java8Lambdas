package com.java8.lambda.chapter8.lambdabehave;

/**
 * 	每个测试套件都由一个实现该接口的 Lambda 表达式实现
 * 
 *	该接口接收一个 Description 对象作为参数，我们在 describe 方法里将其传入。
 *
 *	@author hzweiyongqiang
 */
public interface Suite {
	
    public void specifySuite(Description description);

}
