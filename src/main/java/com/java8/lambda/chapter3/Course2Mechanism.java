package com.java8.lambda.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
/**
 * 	判断一个操作是惰性求值还是及早求值很简单：只需看它的返回值。
 * 	如果返回值是 Stream ，那么是惰性求值；
 * 	如果返回值是另一个值或为空，那么就是及早求值。
 * 
 * 	使用这些操作的理想方式就是形成一个惰性求值的链，最后用一个及早求值的操作返回想要的结果。
 * @author hzweiyongqiang
 *
 */
public class Course2Mechanism {

	public static void main(String[] args) {
		List<String> citys = new ArrayList<String>();
		citys.add("abc");
		citys.add("bcd");
		citys.add("cde");
		citys.add("def");
		
		// Stream 是用函数式编程方式在集合类上进行复杂操作的工具。
		
		// filter 只刻画出了 Stream ，但没有产生新的集合。
		Stream<String> stream = citys.stream().filter(city -> city.indexOf("b") != -1);
		System.out.println(stream);
		
		// 像 filter 这样只描述 Stream ，最终不产生新集合的方法叫作惰性求值方法
		citys.stream().filter(city -> city.indexOf("b") != -1);
		System.out.println("---惰性求值方法---e.g.");
		// 运行这段代码，程序不会输出任何信息！
		Stream<String> stream2 = citys.stream().filter(city -> {
			System.out.println(city);
			return city.indexOf("b") != -1;
		});
		
		// 像 count 这样	最终会从 Stream 产生值的方法叫作及早求值方法
		citys.stream().filter(city -> city.indexOf("b") != -1).count();
		System.out.println("---及早求值方法---e.g.");
		citys.stream().filter(city -> {
			System.out.println(city);
			return city.indexOf("b") != -1;
		}).count();
	}
}
