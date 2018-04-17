package com.java8.lambda.chapter1;

import java.util.List;

/**
 * 音乐：
 * 本书中的示例全部都围绕一个常见的问题领域构造：音乐。 
 * 这些示例代表了在专辑上常常看到的信息。
 * 
 * @author hzweiyongqiang
 */
public class Artist {
	
	private String name;
	
	private List<String> members;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}
}
