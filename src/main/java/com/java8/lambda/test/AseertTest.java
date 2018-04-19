package com.java8.lambda.test;

public class AseertTest {
	public boolean flag = true;// 改变flag值然后查看控制台输出

	public boolean isValid() {

		return flag;
	}

	public static void main(String args[]) {

		AseertTest assertion = new AseertTest();
		assert assertion.isValid() : "Flag must be true!";
		System.out.println("assert end");
	}
}
