package com.java8.lambda.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 	手动实现并行化蒙特卡洛模拟法的代码。
 * 	[注]模拟系统 Course4Simulations 拓展。
 * 
 * 	可以看到，大多数代码都在处理调度和等待线程池中的某项任务完成。
 * 	而使用并行化的流时，这些都不用程序员手动管理。
 *	
 *	@author hzweiyongqiang
 */
public class ManualDiceRolls {

	private static final int N = 1000000;
	
	private final double fraction;
	private final Map<Integer, Double> results;
	private final int numberOfThreads;
	private final ExecutorService executor;
	private final int workPerThread;
	
	public ManualDiceRolls() {
		fraction = 1.0 / N;
		results = new ConcurrentHashMap<>();
		numberOfThreads = Runtime.getRuntime().availableProcessors();
		executor = Executors.newFixedThreadPool(numberOfThreads);
		workPerThread = N / numberOfThreads;
	}
	
	public ManualDiceRolls(int times) {
		fraction = 1.0 / times;
		results = new ConcurrentHashMap<>();
		numberOfThreads = Runtime.getRuntime().availableProcessors();
		executor = Executors.newFixedThreadPool(numberOfThreads);
		workPerThread = times / numberOfThreads;
	}
	
	public void simulateDiceRoles() {
		List<Future<?>> futures = submitJobs();
		awaitCompletion(futures);
		printResults();
	}
	
	private void printResults() {
		results.entrySet()
			   .forEach(System.out::println);
	}
	
	private List<Future<?>> submitJobs() {
		List<Future<?>> futures = new ArrayList<>();
		for (int i = 0; i < numberOfThreads; i++) {
			futures.add(executor.submit(makeJob()));
		}
		return futures;
	}
	
	private Runnable makeJob() {
		return () -> {
			ThreadLocalRandom random = ThreadLocalRandom.current();
			for (int i = 0; i < workPerThread; i++) {
				int entry = twoDiceThrows(random);
				accumulateResult(entry);
			}
		};
	}
	
	private void accumulateResult(int entry) {
		results.compute(entry, (key,previous)->{
			return previous == null ? fraction : previous+fraction;
		});
	}
	
	private int twoDiceThrows(ThreadLocalRandom random) {
		int firstThrow = random.nextInt(1,7);
		int secondThrow = random.nextInt(1,7);
		return firstThrow + secondThrow;
	}
	
	private void awaitCompletion(List<Future<?>> futures) {
		futures.forEach((future)->{
			try {
				future.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		executor.shutdown();
	}
	
	public static void main(String[] args) {
		ManualDiceRolls roles = new ManualDiceRolls();
		roles.simulateDiceRoles();
	}
}
