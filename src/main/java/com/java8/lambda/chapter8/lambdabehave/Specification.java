package com.java8.lambda.chapter8.lambdabehave;

import com.java8.lambda.chapter8.lambdabehave.expectations.Expect;
/**
 * 	每条规则都是一个实现该接口的 Lambda 表达式
 *	
 *	@author hzweiyongqiang
 */
public interface Specification {

    public void specifyBehaviour(Expect expect);

}
