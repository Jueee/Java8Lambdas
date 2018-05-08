package com.java8.lambda.chapter8;

/**
 * 	使用Lambda表达式的领域专用语言
 *	
 *	领域专用语言（Domain-Specific Languages，简称 DSL ）是针对软件系统中某特定部分的编程语言。
 *	它们通常比较小巧，表达能力也不如 Java 这样能应对大多数编程任务的通用语言强。
 *	DSL 高度专用：不求面面俱到，但求有所专长。
 *
 *
 *	人们通常将 DSL 分为两类：
 *
 *	一、	外部 DSL。
 *		外部 DSL 脱离程序源码编写，然后单独解析和实现。比如级联样式表（CSS）和正则表达式，就是常用的外部 DSL。
 *
 *	二、	内部 DSL。
 *		内部 DSL 嵌入编写它们的编程语言中。
 *		如果读者使用过 JMock 和 Mockito 等模拟类库，或用过 SQL 构建 API，如 JOOQ 或 Querydsl，那么就知道什么是内部 DSL。、
 *		从某种角度上说，内部 DSL 就是普通的类库，提供 API 方便使用。
 *		虽然简单，内部 DSL 却功能强大，让你的代码变得更加精炼、易读。
 *		理想情况下，使用 DSL 编写的代码读起来就像描述问题所使用的语言。
 *
 *	@author hzweiyongqiang
 */
public class Course20SpecificLanguages {
	/**
	 * 	我们将通过实现一个用于行为驱动开发（behavior-driven development，简称 BDD ）的 DSL：LambdaBehave，来探索其中遇到的各种问题。
	 * 
	 * 	BDD 是测试驱动开发（test-driven development，TDD）的一个变种，它的重点是描述程序的行为，而非一组需要通过的单元测试。
	 * 	我们的设计灵感源于一个叫 Jasmine 的 JavaScript BDD 框架，前端开发中会大量使用该框架。
	 */
	
	/**
	使用 Jasmine 创建测试用例：
	
	describe("A suite is just a function", function() {
		it("and so is a spec", function() {
			var a = true;
			expect(a).toBe(true);
		});
	});
	
	**/
	
	/**
	 * 	在 JavaScript 中我们使用 function() { … } 来表示 Lambda 表达式。
	 * 	让我们分别来看看这些概念：
	 * 	1、每一个【规则】 描述了程序的一种行为；
	 * 	2、【期望】是描述应用行为的一种方式，在规则中定义；
	 * 	3、多个规则合在一起，形成一个【套件】。
	 * 
	 * 	这些概念在传统的测试框架，比如 JUnit 中，都有对应的概念：
	 * 	规则对应一个测试方法，期望对应断言，套件对应一个测试类。
	 */
}
