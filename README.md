# Java8Lambdas
《Java 8函数式编程》学习笔记  
##### 所用书籍
Java 8 Lambdas，Richard Warburton 著（O’Reilly，2014）。版权所有， 978-1-449-37077-0。
##### 本书将探讨如下主题
* 如何编写出简单、干净、易读的代码——尤其是对于集合的操作？
* 如何简单地使用并行计算提高性能？
* 如何准确地为问题建模，并且开发出更好的领域特定语言？
* 如何写出不易出错，并且更简单的并发代码？
* 如何测试和调试 Lambda 表达式？   

使用 Lambda 表达式，就是将复杂性抽象到类库的过程。
##### 函数式编程
* Lambda 表达式的目的：为了编写处理批量数据的并行类库。
* 面向对象编程是对数据进行抽象，而函数式编程是对行为进行抽象。
* 函数式编程核心：在思考问题时，使用不可变值和函数，函数对一个值进行处理，映射成另一个值。  

### 目录
* 一、简介  
     1. 为什么需要再次修改 Java  
     2. 什么是函数式编程
 - 二、[Lambda 表达式](src/main/java/com/java8/lambda/chapter2)
     1. [第一个 Lambda 表达式 ](src/main/java/com/java8/lambda/chapter2/Course1SwingButton.java) 
     2. [如何辨别 Lambda 表达式](src/main/java/com/java8/lambda/chapter2/Course2Discern.java)
     3. [引用值，而不是变量](src/main/java/com/java8/lambda/chapter2/Course3UseValue.java)
     4. 函数接口
     5. 类型推断
 - 三、[流](src/main/java/com/java8/lambda/chapter3)
     1. [从外部迭代到内部迭代](src/main/java/com/java8/lambda/chapter3/Course1Stream.java)
     2. [实现机制](src/main/java/com/java8/lambda/chapter3/Course2Mechanism.java)
     3. 常用的流操作  
     	 3.1	[collect(toList())](src/main/java/com/java8/lambda/chapter3/Course31Collect.java)  
     	 3.2	[map](src/main/java/com/java8/lambda/chapter3/Course32Map.java)  
     	 3.3	[filter](src/main/java/com/java8/lambda/chapter3/Course33Filter.java)  
     	 3.4	[flatMap](src/main/java/com/java8/lambda/chapter3/Course34FlatMap.java)  
     	 3.5	[max 和  min](src/main/java/com/java8/lambda/chapter3/Course35MaxMin.java)    
     	 3.6	[通用模式](src/main/java/com/java8/lambda/chapter3/Course36Common.java)    
     	 3.7	[reduce](src/main/java/com/java8/lambda/chapter3/Course37Reduce.java)   
     	 3.8	[整合操作](src/main/java/com/java8/lambda/chapter3/Course38Integration.java)   
     4. [重构遗留代码](src/main/java/com/java8/lambda/chapter3/Course4Refactoring.java)
     5. [多次调用流操作](src/main/java/com/java8/lambda/chapter3/Course5MultipleUse.java)
     6. [高阶函数](src/main/java/com/java8/lambda/chapter3/Course6HigherFunction.java)
     7. [正确使用 Lambda 表达式](src/main/java/com/java8/lambda/chapter3/Course7CorrectUse.java)
     8. [要点回顾](src/main/java/com/java8/lambda/chapter3/Course8KeyPoint.java)
     9. [练习](src/main/java/com/java8/lambda/chapter3/Course9Exercises.java)
 - 四、类库(src/main/java/com/java8/lambda/chapter4)
     1. [在代码中使用 Lambda 表达式](src/main/java/com/java8/lambda/chapter4/Course01CodeUse.java)
     2. [基本类型](src/main/java/com/java8/lambda/chapter4/Course02BasicType.java)
     3. [重载解析](src/main/java/com/java8/lambda/chapter4/Course03Overload.java)
     4. [@FunctionalInterface](src/main/java/com/java8/lambda/chapter4/Course04FunctionalInterface.java)
     5. [二进制接口的兼容性](src/main/java/com/java8/lambda/chapter4/Course05BinaryCompatible.java)
     6. [默认方法](src/main/java/com/java8/lambda/chapter4/Course06DefaultMethods.java)
     7. [多重继承](src/main/java/com/java8/lambda/chapter4/Course07MultExtends.java)
     8. [权衡](src/main/java/com/java8/lambda/chapter4/Course08Tradeoffs.java)
     9. [接口的静态方法](src/main/java/com/java8/lambda/chapter4/Course09StaticMethods.java)
     10. [Optional](src/main/java/com/java8/lambda/chapter4/Course10Optional.java)
     11. [要点回顾](src/main/java/com/java8/lambda/chapter4/Course11KeyPoints.java)
     12. [练习](src/main/java/com/java8/lambda/chapter4/Course12Exercises.java)
