package com.java8.lambda.chapter8.lambdabehave.expectations;

import static org.junit.Assert.assertEquals;

public class BoundExpectation {

    private final Object objectUnderTest;

    public BoundExpectation(Object value) {
        this.objectUnderTest = value;
    }
    
    public void isEqualTo(Object expected) {
        assertEquals(expected, objectUnderTest);
    }

}
