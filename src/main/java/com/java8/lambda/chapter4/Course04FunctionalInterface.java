package com.java8.lambda.chapter4;

/**
 * 	事实上，每个用作函数接口的接口都应该添加 @FunctionalInterface 注解。
 * 
 * 	
 * 	一个可关闭的对象必须持有某种打开的资源，比如一个需要关闭的文件句柄。
 * 	同样，该接口也不能是一个纯函数，因为关闭资源是更改状态的另一种形式。
 * 
 * 	和 Closeable 和 Comparable 接口不同，为了提高 Stream 对象可操作性而引入的各种新接口，都需要有 Lambda 表达式可以实现它。
 * 	它们存在的意义在于将代码块作为数据打包起来。因此，它们都添加了 @FunctionalInterface 注释。
 * 
 * 	该注释会强制 javac 检查一个接口是否符合函数接口的标准。
 * 	如果该注释添加给一个枚举类型、类或另一个注释，或者接口包含不止一个抽象方法， javac 就会报错。
 * 	重构代码时，使用它能很容易发现问题。
 * 
 * 
 * @author hzweiyongqiang
 *
 */
public class Course04FunctionalInterface {

}
