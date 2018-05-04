package com.java8.lambda.chapter8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * 	策略模式
 *	
 *	策略模式能在运行时改变软件的算法行为。
 *	如何实现策略模式根据你的情况而定，但其主要思想是定义一个通用的问题，使用不同的算法来实现，然后将这些算法都封装在一个统一接口的背后。
 *
 *	@author hzweiyongqiang
 */
public class Course12StrategyPattern {
	/**
	 * 	文件压缩就是一个很好的例子。
	 * 	我们提供给用户各种压缩文件的方式，可以使用 zip 算法，也可以使用 gzip 算法，我们实现一个通用的 Compressor 类，能以任何一种算法压缩文件。
	 */
	// 定义压缩数据的策略接口
	public interface CompressionStrategy{
		public OutputStream compress(OutputStream data) throws IOException;
	}
	// 使用 gzip 算法压缩数据
	public class GzipCompressionStrategy implements CompressionStrategy{
		@Override
		public OutputStream compress(OutputStream data) throws IOException {
			return new GZIPOutputStream(data);
		}
	}
	// 使用 zip 算法压缩数据
	public class ZipCompressionStrategy implements CompressionStrategy{
		@Override
		public OutputStream compress(OutputStream data) throws IOException {
			return new ZipOutputStream(data);
		}
	}
	/**
	 * 	在构造类时提供压缩策略，这里就是使用策略模式的地方。
	 * 	该类有一个 compress 方法，读入文件，压缩后输出。
	 */
	public class Compressor {
		private final CompressionStrategy strategy;
		public Compressor(CompressionStrategy strategy) {
			this.strategy = strategy;
		}
		public void compress(Path inFile,File outFile) throws FileNotFoundException, IOException {
			try(OutputStream outputStream = new FileOutputStream(outFile)){
				Files.copy(inFile, strategy.compress(outputStream));
			}
		}
	}
	/**
	 * 	使用具体的策略类初始化 Compressor
	 *	@param inFile
	 *	@param outFile
	 *	@throws FileNotFoundException
	 *	@throws IOException
	 */
	public void useCompressor(Path inFile,File outFile) throws FileNotFoundException, IOException {
		Compressor gzipCompressor = new Compressor(new GzipCompressionStrategy());
		gzipCompressor.compress(inFile, outFile);
		Compressor zipCompressor = new Compressor(new ZipCompressionStrategy());
		zipCompressor.compress(inFile, outFile);
	}
	/**
	 * 	使用方法引用初始化 Compressor
	 * 	在这里，我们可以去掉具体的策略实现，使用一个方法实现算法，这里的算法由构造函数中对应的 OutputStream 实现。
	 *	@param inFile
	 *	@param outFile
	 *	@throws FileNotFoundException
	 *	@throws IOException
	 */
	public void useCompressorByMethod(Path inFile,File outFile) throws FileNotFoundException, IOException  {
		Compressor gzipCompressor = new Compressor(GZIPOutputStream::new);
		gzipCompressor.compress(inFile, outFile);
		Compressor zipCompressor = new Compressor(ZipOutputStream::new);
		zipCompressor.compress(inFile, outFile);
	}
}
