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

### 本书目录及程序实现
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
 - 四、[类库](src/main/java/com/java8/lambda/chapter4)
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
- 五、[高级集合类和收集器](src/main/java/com/java8/lambda/chapter5)
     1. [方法引用](src/main/java/com/java8/lambda/chapter5/Course1MethodReferences.java)       
     2. [元素顺序](src/main/java/com/java8/lambda/chapter5/Course2ElementOrdering.java)    
     3. [使用收集器](src/main/java/com/java8/lambda/chapter5/Course30EnterCollector.java)   
     	3.1 [转换成其他集合](src/main/java/com/java8/lambda/chapter5/Course31IntoOther.java)  
     	3.2	[转换成值](src/main/java/com/java8/lambda/chapter5/Course32ToValues.java)  
     	3.3	[数据分块](src/main/java/com/java8/lambda/chapter5/Course33PartitioningData.java)  
     	3.4	[数据分组](src/main/java/com/java8/lambda/chapter5/Course34GroupingData.java)  
     	3.5	[字符串](src/main/java/com/java8/lambda/chapter5/Course35Strings.java)  
     	3.6	[组合收集器](src/main/java/com/java8/lambda/chapter5/Course36ComposingCollectors.java)   
     	3.7 [重构和定制收集器](src/main/java/com/java8/lambda/chapter5/Course37RefactoringCollectors.java)  
     	3.8 [对收集器的归一化处理](src/main/java/com/java8/lambda/chapter5/Course38ReductionCollector.java)  
     4. [一些细节](src/main/java/com/java8/lambda/chapter5/Course4CollectionNiceties.java)  
     5. [要点回顾](src/main/java/com/java8/lambda/chapter5/Course5KeyPoints.java)
     6. [练习](src/main/java/com/java8/lambda/chapter5/Course6Exercises.java)
- 六、[数据并行化](src/main/java/com/java8/lambda/chapter6)
     1. [并行和并发](src/main/java/com/java8/lambda/chapter6/Course1Parallelism.java)
     2. [为什么并行化如此重要](src/main/java/com/java8/lambda/chapter6/Course2WhyImportant.java)
     3. [并行化流操作](src/main/java/com/java8/lambda/chapter6/Course3StreamOperations.java)
     4. [模拟系统](src/main/java/com/java8/lambda/chapter6/Course4Simulations.java)
     5. [限制](src/main/java/com/java8/lambda/chapter6/Course5Caveats.java)
     6. [性能](src/main/java/com/java8/lambda/chapter6/Course6Performance.java)
     7. [并行化数组操作](src/main/java/com/java8/lambda/chapter6/Course7ArrayOperations.java)
     8. [要点回顾](src/main/java/com/java8/lambda/chapter6/Course8KeyPoints.java)
     9. [练习](src/main/java/com/java8/lambda/chapter6/Course9Exercises.java)
- 七、[测试、调试和重构](src/main/java/com/java8/lambda/chapter7)
     1. [重构候选项](src/main/java/com/java8/lambda/chapter7/Course10RefactoringCandidates.java)  
     	1.1 [进进出出、摇摇晃晃](src/main/java/com/java8/lambda/chapter7/Course11InOut.java)  
     	1.2 [孤独的覆盖](src/main/java/com/java8/lambda/chapter7/Course12LonelyOverride.java)  
     	1.3 [同样的东西写两遍](src/main/java/com/java8/lambda/chapter7/Course13EverythingTwice.java)  
     2. [Lambda 表达式的单元测试](src/main/java/com/java8/lambda/chapter7/Course2UnitTesting.java)
     3. [在测试替身时使用Lambda表达式](src/main/java/com/java8/lambda/chapter7/Course3TestDoubles.java)
     4. [惰性求值和调试](src/main/java/com/java8/lambda/chapter7/Course4LazyEvaluation.java)
     5. [日志和打印消息](src/main/java/com/java8/lambda/chapter7/Course5Logging.java)
     6. [解决方案： peak](src/main/java/com/java8/lambda/chapter7/Course6Peek.java)
     7. [在流中间设置断点](src/main/java/com/java8/lambda/chapter7/Course7Breakpoints.java)
     8. [要点回顾](src/main/java/com/java8/lambda/chapter7/Course8KeyPoints.java)  
- 八、[设计和架构的原则](src/main/java/com/java8/lambda/chapter8)
     1. [Lambda 表达式改变了设计模式](src/main/java/com/java8/lambda/chapter8/Course10DesignPatterns.java)  
     	1.1 [命令者模式](src/main/java/com/java8/lambda/chapter8/Course11CommandPattern.java)  
     	1.2 [策略模式](src/main/java/com/java8/lambda/chapter8/Course12StrategyPattern.java)  
     	1.3 [观察者模式](src/main/java/com/java8/lambda/chapter8/Course13ObserverPattern.java)  
     	1.4 [模板方法模式](src/main/java/com/java8/lambda/chapter8/Course14TemplateMethodPattern.java)
     2. [使用 Lambda 表达式的领域专用语言](src/main/java/com/java8/lambda/chapter8/Course20SpecificLanguages.java)  
     	2.1 [使用 Java 编写DSL](src/main/java/com/java8/lambda/chapter8/Course21DSLinJava.java)  
     	2.2 [实现](src/main/java/com/java8/lambda/chapter8/Course22How.java)  
     	2.3 [评估](src/main/java/com/java8/lambda/chapter8/Course23Evaluation.java)  
     3. [使用 Lambda 表达式的 SOLID 原则](src/main/java/com/java8/lambda/chapter8/Course30SOLID.java)
     	3.1 [单一功能原则](src/main/java/com/java8/lambda/chapter8/Course31SingleResponsibility.java)  
     	3.2 [开闭原则](src/main/java/com/java8/lambda/chapter8/Course32OpenClosed.java)  
     	3.3 [依赖反转原则](src/main/java/com/java8/lambda/chapter8/Course33DependencyInversion.java)  
     4. [进阶阅读](src/main/java/com/java8/lambda/chapter8/Course4FurtherReading.java)
     5. [要点回顾](src/main/java/com/java8/lambda/chapter8/Course5KeyPoints.java)  
- 九、[使用 Lambda 表达式编写并发程序](src/main/java/com/java8/lambda/chapter9)  
     1. [为什么要使用非阻塞式I/O](src/main/java/com/java8/lambda/chapter9/Course01NonblockingIO.java)
     2. [回调](src/main/java/com/java8/lambda/chapter9/Course02Callbacks.java)
     3. [消息传递架构](src/main/java/com/java8/lambda/chapter9/Course03MessagePassing.java)
     4. [末日金字塔](src/main/java/com/java8/lambda/chapter9/Course04DoomPyramid.java)
     5. [Future](src/main/java/com/java8/lambda/chapter9/Course05Futures.java)
     6. [CompletableFuture](src/main/java/com/java8/lambda/chapter9/Course06CompletableFutures.java)
     7. [响应式编程](src/main/java/com/java8/lambda/chapter9/Course07ReactiveProgramming.java)
     8. [何时何地使用新技术](src/main/java/com/java8/lambda/chapter9/Course08WhenWhere.java)
     9. [要点回顾](src/main/java/com/java8/lambda/chapter9/Course09KeyPoints.java)
     10. [练习](src/main/java/com/java8/lambda/chapter9/Course10Exercises.java)
- 十、下一步该怎么办
