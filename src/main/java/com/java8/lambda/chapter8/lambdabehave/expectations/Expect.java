package com.java8.lambda.chapter8.lambdabehave.expectations;

import java.util.Collection;
/**
 * 	规则通过 expect.that 描述期望的行为
 *	
 *	@author hzweiyongqiang
 */
public final class Expect {
	/**
	 * 	封装传入的对象，然后暴露一些常用的方法，如 isEqualTo 。
	 * 	如果规则失败，抛出相应的断言。
	 *	@param value
	 *	@return
	 */
    public BoundExpectation that(Object value) {
        return new BoundExpectation(value);
    }

    public CollectionExpectation that(Collection<?> collection) {
        return new CollectionExpectation(collection);
    }

}
