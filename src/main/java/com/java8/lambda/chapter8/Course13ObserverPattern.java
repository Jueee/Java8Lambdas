package com.java8.lambda.chapter8;

import java.util.ArrayList;
import java.util.List;

/**
 * 	观察者模式
 *	
 *	在观察者模式中，被观察者持有一个观察者列表。
 *	当被观察者的状态发生改变，会通知观察者。
 *	观察者模式被大量应用于基于 MVC 的 GUI 工具中，以此让模型状态发生变化时，自动刷新视图模块，达到二者之间的解耦。
 *
 *	将大量代码塞进一个方法会让可读性变差是决定如何使用 Lambda 表达式的黄金法则。
 *
 *	@author hzweiyongqiang
 */
public class Course13ObserverPattern {

	/**
	 * 	NASA 和外星人都对登陆到月球上的东西感兴趣，都希望可以记录这些信息。
	 * 	NASA 希望确保阿波罗号上的航天员成功登月；外星人则希望在 NASA 注意力分散之时进犯地球。
	 */
	/**
	 * 	用于观察登陆到月球的组织的接口
	 */
	public interface LandingObserver{
		// 当有东西登陆到月球上时会调用该方法
		public void observeLanding(String name);
	}
	/**
	 * 	被观察者是月球 Moon
	 * 	它持有一组 LandingObserver 实例，有东西着陆时会通知这些观察者，
	 * 	还可以增加新的 LandingObserver 实例观测 Moon 对象
	 */
	public class Moon {
		private final List<LandingObserver> observers = new ArrayList<>();
		public void load(String name) {
			for(LandingObserver observer : observers) {
				observer.observeLanding(name);
			}
		}
		public void startSpying(LandingObserver observer) {
			observers.add(observer);
		}
	}
	/**
	 * 	外星人观察到人类登陆月球
	 */
	public class Aliens implements LandingObserver{
		@Override
		public void observeLanding(String name) {
			if (name.contains("Apollo")) {
				System.out.println("They're distracted,lets invade earth!");
			}
		}
	}
	/**
	 * 	NASA 也能观察到有人登陆月球
	 */
	public class Nasa implements LandingObserver{
		@Override
		public void observeLanding(String name) {
			if (name.contains("Apollo")) {
				System.out.println("We made it!");
			}
		}
	}
	
	/**
	 * 	使用类的方式构建用户代码
	 */
	public void useLandingObserver() {
		Moon moon = new Moon();
		moon.startSpying(new Nasa());
		moon.startSpying(new Aliens());
		
		moon.load("An asteriud");
		moon.load("Apollo 11");
	}
	
	/**
	 * 	使用 Lambda 表达式构建用户代码
	 */
	public void useLandingObserverByLambda() {
		Moon moon = new Moon();
		moon.startSpying(name->{
			if (name.contains("Apollo")) {
				System.out.println("We made it!");
			}
		});
		moon.startSpying(name->{
			if (name.contains("Apollo")) {
				System.out.println("They're distracted,lets invade earth!");
			}
		});
		
		moon.load("An asteriud");
		moon.load("Apollo 11");
	}
}
