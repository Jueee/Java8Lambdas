package com.java8.lambda.chapter8;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 	开闭原则
 * 	软件应该对扩展开放，对修改闭合。	—— Bertrand Meyer
 */
/**
 * 	开闭原则的首要目标和单一功能原则类似：让软件易于修改。
 * 	一个新增功能或一处改动，会影响整个代码，容易引入新的缺陷。
 * 
 * 	开闭原则保证已有的类在不修改内部实现的基础上可扩展，这样就努力避免了上述问题。
 *	不改变实现怎么能扩展一个类的功能呢？答案是借助于抽象，可插入新的功能。
 */
/**
 *	对开闭原则的另外一种理解和传统的思维不同，那就是使用不可变对象实现开闭原则。
 *	不可变对象是指一经创建就不能改变的对象。
 *	“不可变性”一词有两种解释：
 *	1、观测不可变性，是指在其他对象看来，该类是不可变的；
 *	2、实现不可变性，是指对象本身不可变。
 *	实现不可变性意味着观测不可变性，反之则不一定成立。
 *
 *	java.lang.String 宣称是不可变的，但事实上只是观测不可变。
 *	因为它在第一次调用hashCode 方法时缓存了生成的散列值。
 *	在其他类看来，这是完全安全的，它们看不出散列值是每次在构造函数中计算出来的，还是从缓存中返回的。
 *
 *	我们说不可变对象实现了开闭原则，是因为它们的内部状态无法改变，可以安全地为其增加新的方法。
 *	新增加的方法无法改变对象的内部状态，因此对修改是闭合的；但它们又增加了新的行为，因此对扩展是开放的。
 */
/**
 *	在 Java 8 中，任何传入高阶函数的 Lambda 表达式都由一个函数接口表示，高阶函数负责调用其唯一的方法，根据传入 Lambda 表达式的不同，行为也不同。
 *	这其实也是在用多态来实现开闭原则。
 *
 *	@author hzweiyongqiang
 */
public class Course32OpenClosed {
	/**
	 * 	我们要写的程序用来衡量系统性能，并且把得到的结果绘制成图形。
	 * 	比如，我们有描述计算机花在用户空间、内核空间和输入输出上的时间散点图。
	 * 	我将负责显示这些指标的类叫作 MetricDataGraph 。
	 */
	/**
	 * 	MetricDataGraph 类的公开 API
	 * 	设计 MetricDataGraph 类的方法之一是将代理收集到的各项指标放入该类
	 */
	public class MetricDataGraph1{
		public void updateUserTime(int value) {};
		public void updateSystemTime(int value) {};
		public void updateIoTime(int value) {};
	}
	
	/**
	 * 	但这样的设计意味着每次想往散点图中添加新的时间点，都要修改 MetricDataGraph 类。
	 * 	通过引入抽象可以解决这个问题，我们使用一个新类 TimeSeries 来表示各种时间点。
	 * 	这时，MetricDataGraph 类的公开 API 就得以简化，不必依赖于某项具体指标
	 */
	
	/**
	 * 	MetricDataGraph 类简化之后的 API
	 * 	每项具体指标现在可以实现 TimeSeries 接口，在需要时能直接插入。
	 * 	比如，我们可能会有如下类： UserTimeSeries 、 SystemTimeSeries 和 IoTimeSeries 。
	 * 	如果要添加新的，比如由于虚拟化所浪费的 CPU 时间，则可增加一个新的实现了 TimeSeries 接口的类：StealTimeSeries 。
	 * 	这样，就扩展了 MetricDataGraph 类，但并没有修改它。
	 */
	public class MetricDataGraph2{
		public void addTimeSeries(TimeSeries values) {};
	}
	public interface TimeSeries{}
	public class UserTimeSeries implements TimeSeries{}
	public class SystemTimeSeries implements TimeSeries{}
	public class IoTimeSeries implements TimeSeries{}
	
	
	
	/**
	 * 	高阶函数也展示出了同样的特性：对扩展开放，对修改闭合。
	 * 
	 * 	ThreadLocal 类就是一个很好的例子。
	 * 	ThreadLocal 有一个特殊的变量，每个线程都有一个该变量的副本并与之交互。
	 * 	该类的静态方法 withInitial 是一个高阶函数，传入一个负责生成初始值的 Lambda 表达式。
	 * 
	 * 	这符合开闭原则，因为不用修改 ThreadLocal 类，就能得到新的行为。
	 * 	给 withInitial 方法传入不同的工厂方法，就能得到拥有不同行为的 ThreadLocal 实例。
	 */
	
	
	/**
	 * 	 ThreadLocal 日期格式化器
	 */
	public void formatDate() {
		// 实现
		ThreadLocal<DateFormat> localFormatter = ThreadLocal.withInitial(()->new SimpleDateFormat());
		// 使用
		DateFormat formatter = localFormatter.get();
	}
	/**
	 * 	ThreadLocal 标识符
	 */
	public void getLocalId() {
		// 实现
		AtomicInteger threadId = new AtomicInteger();
		ThreadLocal<Integer> localId = ThreadLocal.withInitial(()->threadId.getAndIncrement());
		// 使用
		int idForThisThread = localId.get();
	}
}
