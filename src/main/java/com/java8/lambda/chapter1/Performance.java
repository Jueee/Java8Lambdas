package com.java8.lambda.chapter1;

import java.util.stream.Stream;


public interface Performance {
	
	public String getName();

	public Stream<Artist> getMusicians();
	
}
