package com.java8.lambda.chapter8;

import org.junit.jupiter.api.Test;

class Course13ObserverPatternTest {

	Course13ObserverPattern test = new Course13ObserverPattern();
	
	@Test
	void test() {
		test.useLandingObserver();
		
		test.useLandingObserverByLambda();
	}

}
