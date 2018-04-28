package com.java8.lambda.chapter5;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 	使用定制的收集器 StringCollector 收集字符串
 *	
 *	将 reduce 操作重构为一个收集器 StringCollector，在程序中的任何地方都能使用。
 *	@author hzweiyongqiang
 */
/**
 * 	实现 Collector 接口，由于 Collector 接口支持泛型，因此先得确定一些具体的类型：
 * 	1、待收集元素的类型，这里是 ? String 
 * 	2、累加器的类型 StringCombiner ；
 * 	3、最终结果的类型，这里依然是  String 。
 */
public class StringCollector implements Collector<String, StringCombiner, String>{
	
	private static final Set<Characteristics> characteristics = Collections.emptySet();

	private final String prefix;	// 前缀
    private final String suffix;	// 后缀
    private final String delim;		// 分隔符

    public StringCollector(String delim, String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.delim = delim;
    }
    
    /**
     * 	Supplier 是创建容器的工厂
     * 	和 reduce 操作中的第一个参数类似，它是后续操作的初值
     */
	@Override
	public Supplier<StringCombiner> supplier() {
		return () -> new StringCombiner(delim, prefix, suffix);
	}

	/**
	 * 	accumulator 是一个函数，它将当前元素叠加到收集器
	 * 	作用和 reduce 操作的第二个参数一样，它结合之前操作的结果和当前值，生成并返回新的值。
	 * 	这一逻辑已经在 StringCombiners 的 add 方法中得以实现，直接引用就好了
	 */
	@Override
	public BiConsumer<StringCombiner, String> accumulator() {
		return StringCombiner::add;
	}

	/**
	 * 	combiner 合并两个容器
	 * 	combine 方法很像 reduce 操作的第三个方法。如果有两个容器，我们需要将其合并。
	 */
	@Override
	public BinaryOperator<StringCombiner> combiner() {
		return StringCombiner::merge;
	}

	/**
	 * 	finisher 方法返回收集操作的最终结果
	 */
	@Override
	public Function<StringCombiner, String> finisher() {
		return StringCombiner::toString;
	}

	/**
	 * 	characteristics 方法定义了特征
	 * 	特征是一组描述收集器的对象，框架可以对其适当优化。 
	 */
	@Override
	public Set<Characteristics> characteristics() {
		return characteristics;
	}

}
