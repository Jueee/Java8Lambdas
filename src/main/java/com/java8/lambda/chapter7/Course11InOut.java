package com.java8.lambda.chapter7;

import com.java8.lambda.chapter4.Course01CodeUse;

/**
 * 	进进出出、摇摇晃晃
 *	
 *
 *	如果你发现自己的代码不断地查询和操作某对象，目的只为了在最后给该对象设个值，那么这段代码就本该属于你所操作的对象。
 *	与其查询并设置一个对象的值，不如传入一个 Lambda 表达式，该表达式按照计算得出的值执行相应的行为。
 *
 *	@author hzweiyongqiang
 */
public class Course11InOut {

	/**
	 * 	logger 对象使用 isDebugEnabled 属性避免不必要的性能开销
	 * 
	 * 	这段代码先调用 isDebugEnabled 方法抽取布尔值，用来检查是否启用调试级别
	 * 	如果启用，则调用 Logger 对象的相应方法记录日志。
	 */
	public void useLogger() {
		Course01CodeUse logger = new Course01CodeUse();
		if (logger.isDebugEnabled()) {
			logger.debug("Look at this: " + logger.expensiveOperation());
		}
	}
	
	/**
	 * 	使用 Lambda 表达式简化记录日志代码
	 * 	当程序处于调试级别，并且检查是否使用 Lambda 表达式的逻辑被封装在 Logger 对象中时，才会调用 Lambda 表达式。
	 */
	public void useLoggerNew() {
		Course01CodeUse logger = new Course01CodeUse();
		logger.debug(() -> "Look at this: " + logger.expensiveOperation());
	}
}
