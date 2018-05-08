package com.java8.lambda.chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 	依赖反转原则
 *	抽象不应依赖细节，细节应该依赖抽象。	
 */
/**
 *	让程序变得死板、脆弱、难于改变的方法之一是将上层业务逻辑和底层粘合模块的代码混在一起，因为这两样东西都会随着时间发生变化。
 *	依赖反转原则的目的是让程序员脱离底层粘合代码，编写上层业务逻辑代码。
 *	这就让上层代码依赖于底层细节的抽象，从而可以重用上层代码。
 *	这种模块化和重用方式是双向的：既可以替换不同的细节重用上层代码，也可以替换不同的业务逻辑重用细节的实现。	
 */
/**
 * 	具体到 Lambda 表达式，我们之前遇到的很多高阶函数都符合依赖反转原则。
 * 	比如 map 函数重用了在两个集合之间转换的代码。 
 * 	map 函数不依赖于转换的细节，而是依赖于抽象的概念。在这里，就是依赖函数接口： Function 。
 */
/**
 * 	高阶函数提供了反转控制，这就是依赖反转的一种形式，可以很容易地和 Lambda 表达式一起使用。
 * 	依赖反转原则另外值得注意的一点是待依赖的抽象不必是接口。
 */
/**
 *	@author hzweiyongqiang
 */
public class Course33DependencyInversion {
	/**
	 * 	让我们看一个具体的、自动化构建地址簿的例子，实现时使用了依赖反转原则达到上层的解耦。
	 * 
	 * 	该应用以电子卡片作为输入，使用某种存储机制编写地址簿。
	 * 
	 * 	可将代码分成如下三个基本模块：
	 * 	1、一个能解析电子卡片格式的电子卡片阅读器； 
	 * 	2、能将地址存为文本文件的地址簿存储模块； 
	 * 	3、从电子卡片中获取有效信息并将其写入地址簿的编写模块。
	 */
	
	/**
	 * 	为了具备能在系统中替换组件的灵活性，必须保证编写模块不依赖阅读器或存储模块的实现细节。
	 * 	因此我们引入了对阅读信息和输出信息的抽象，编写模块的实现依赖于这种抽象。
	 * 	在运行时传入具体的实现细节，这就是依赖反转原则的工作原理。
	 * 
	 * 	资源管理是依赖反转的另一个更为复杂的例子。
	 * 	显然，可管理的资源很多，比如数据库连接、线程池、文件和网络连接。
	 * 	这里我将以文件为例，因为文件是一种相对简单的资源，但是背后的原则可以很容易应用到更复杂的资源中。
	 */
	
	/**
	 * 	解析文件中的标题
	 *	@param input
	 *	@return
	 *	@throws HeadingLookupException
	 */
	public List<String> findHeadings(Reader input) throws HeadingLookupException{
		try(BufferedReader reader = new BufferedReader(input)){			// 读取文件
			return reader.lines()										// 逐行检查
						 .filter(line->line.endsWith(":"))				// 滤出标题，从假想的标记语言中提取标题，其中标题以冒号“:”结尾。
						 .map(line->line.substring(0, line.length()-1))
						 .collect(Collectors.toList());
		} catch (IOException e) {
			throw new HeadingLookupException(e);
		}
	}
	// 和读写文件有关的异常封装成接近待解决问题的异常： HeadingLookupException
	class HeadingLookupException extends RuntimeException{
		private static final long serialVersionUID = 1L;
		public HeadingLookupException(IOException e) {}
	}
	
	
	/**
	 * 	代码将提取标题和资源管理、文件处理混在一起。
	 * 	我们真正想要的是编写提取标题的代码，而将操作文件相关的细节交给另一个方法。
	 * 	可以使用 Stream<String> 作为抽象，让代码依赖它，而不是文件。 Stream 对象更安全，而且不容易被滥用。
	 * 	我们还想传入一个函数，在读文件出问题时，可以创建一个问题域里的异常。
	 */
	
	
	/**
	 * 	剥离了文件处理功能后的业务逻辑
	 *	@param input
	 *	@return
	 *	@throws HeadingLookupException
	 */
	public List<String> findHeadings2(Reader input) throws HeadingLookupException{
		return withLinesOf(input, 
						   lines -> lines.filter(line->line.endsWith(":"))				// 滤出标题，从假想的标记语言中提取标题，其中标题以冒号“:”结尾。
							 			 .map(line->line.substring(0, line.length()-1))
							 			 .collect(Collectors.toList()), 
						   HeadingLookupException::new);
	}
	/**
	 * 	定义 withLinesOf 方法
	 *	@param input	处理文件读写
	 *	@param handler	以文件中的每一行组成的 Stream 作为参数
	 *	@param error	输入输出有异常时会调用该方法，它会构建出与问题域有关的异常，出问题时就抛出该异常。
	 *	@return
	 */
	private <T>T withLinesOf(Reader input, Function<Stream<String>, T> handler, Function<IOException, RuntimeException> error){
		try(BufferedReader reader = new BufferedReader(input)){		
			return handler.apply(reader.lines());
		} catch (IOException e) {
			throw error.apply(e);
		}
	}

}
