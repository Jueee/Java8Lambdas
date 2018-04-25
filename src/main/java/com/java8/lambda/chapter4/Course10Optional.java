package com.java8.lambda.chapter4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

/**
 *	Optional
 *
 *	Optional 是为核心类库新设计的一个数据类型，用来替换 null 值。
 *
 *	人们常常使用 null 值表示值不存在， Optional 对象能更好地表达这个概念。
 *	使用 null 代表值不存在的最大问题在于 NullPointerException 。
 *
 *	使用 Optional 对象有两个目的：
 *	首先， Optional 对象鼓励程序员适时检查变量是否为空，以避免代码缺陷；
 *	其次，它将一个类的 API 中可能为空的值文档化，这比阅读实现代码要简单很多。
 *
 *	@author hzweiyongqiang
 */
public class Course10Optional {
	public static void main(String[] args) {
		/**
		 * 	工厂方法	of：从某个值创建出一个 Optional 对象。 
		 * 	Optional 对象相当于值的容器，而该值可以通过 get 方法提取。
		 */
		// 创建某个值的 Optional 对象
		Optional<String> aOptional = Optional.of("a");
		assertEquals("a", aOptional.get());
		
		/**
		 * 	工厂方法 empty：Optional 创建一个空的 对象。
		 * 	工厂方法 ofNullable：将一个空值转换成 Optional 对象。
		 * 	方法 isPresent：表示一个 Optional 对象里是否有值。
		 */
		// 创建一个空的 Optional 对象，并检查其是否有值
		Optional emptyOptional = Optional.empty();
		Optional alsoEmpty = Optional.ofNullable(null);
		assertFalse(emptyOptional.isPresent());
		assertFalse(alsoEmpty.isPresent());
		assertTrue(aOptional.isPresent());
		
		/**
		 * 	使用 Optional 对象的方式之一是在调用 get() 方法前，先使用 isPresent 检查 Optional对象是否有值。
		 * 	使用 orElse 方法则更简洁，当 Optional 对象为空时，该方法提供了一个备选值。
		 * 	如果计算备选值在计算上太过繁琐，即可使用 orElseGet 方法。该方法接受一个 Supplier 对象，只有在 Optional 对象真正为空时才会调用。
		 */
		// 使用 orElse 和 orElseGet 方法
		assertEquals("a", emptyOptional.orElse("a"));
		assertEquals("c", emptyOptional.orElseGet(() -> "c"));
	}
}
