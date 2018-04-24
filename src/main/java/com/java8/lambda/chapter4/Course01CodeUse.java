package com.java8.lambda.chapter4;

import java.util.function.Supplier;

/**
 * 在代码中使用 Lambda 表达式
 * 
 * @author hzweiyongqiang
 *
 */
public class Course01CodeUse {

	private boolean debug = true;

	public boolean isDebugEnabled() {
		return debug;
	}

	public void debug(String message) {
		if (isDebugEnabled()) {
			System.out.println(message);
		}
	}

	private String expensiveOperation() {
		return "";
	}

	/**
	 * 	使用 isDebugEnabled 方法降低日志性能开销
	 */
	public void example() {
		Course01CodeUse logger = new Course01CodeUse();
		if (logger.isDebugEnabled()) {
			logger.debug("Look at this: " + expensiveOperation());
		}
	}

	/**
	 * 	使用 Lambda 表达式简化日志代码
	 */
	public void exampleWithLambda() {
		Course01CodeUse logger = new Course01CodeUse();
		logger.debug(() -> "Look at this: " + expensiveOperation());
	}

	/**
	 * 	启用 Lambda 表达式实现的日志记录器
	 * @param message
	 */
	public void debug(Supplier<String> message) {
		if (isDebugEnabled()) {
			debug(message.get());	// 如果使用 Predicate ，就应该调用 test 方法，如果使用 Function ，就应该调用 apply 方法。
		}
	}
}
