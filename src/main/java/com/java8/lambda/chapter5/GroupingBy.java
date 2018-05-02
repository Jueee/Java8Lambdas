package com.java8.lambda.chapter5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 	用一个定制的收集器实现 Collectors.groupingBy 方法
 *	
 *	@author hzweiyongqiang
 */
public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K,List<T>>> {
	
	private final static Set<Characteristics> characteristics = new HashSet<>();
    static {
        characteristics.add(Characteristics.IDENTITY_FINISH);
    }

	private final Function<? super T, ? extends K> classifier;
	
	public GroupingBy(Function<? super T, ? extends K> classifier) {
		this.classifier = classifier;
	}
	
	@Override
	public Supplier<Map<K, List<T>>> supplier() {
		return HashMap::new;
	}

	@Override
	public BiConsumer<Map<K, List<T>>, T> accumulator() {
		return (map,element) -> {
			K key = classifier.apply(element);
			List<T> elements = map.computeIfAbsent(key, k->new ArrayList<>());
			elements.add(element);
		};
	}

	@Override
	public BinaryOperator<Map<K, List<T>>> combiner() {
		return (left, right) -> {
			right.forEach((key,value) -> {
				left.merge(key,value,(leftValue,rightValue) -> {
					leftValue.addAll(rightValue);
					return leftValue;
				});
			});
			return left;
		};
	}

	@Override
	public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
		return map->map;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return characteristics;
	}

}
