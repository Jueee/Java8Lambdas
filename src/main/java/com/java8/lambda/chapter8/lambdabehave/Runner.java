package com.java8.lambda.chapter8.lambdabehave;

import com.java8.lambda.chapter8.lambdabehave.StackSpec;
import com.java8.lambda.chapter8.lambdabehave.reporting.ConsoleFormatter;
import com.java8.lambda.chapter8.lambdabehave.reporting.Report;
import com.java8.lambda.chapter8.lambdabehave.reporting.ReportFormatter;
import com.java8.lambda.chapter8.lambdabehave.reporting.Result;
import com.java8.lambda.chapter8.lambdabehave.reporting.SpecificationReport;

public enum Runner {

    current;

    private final Report report;

    private Runner() {
        report = new Report();
    }

    void recordSuccess(String suite, String specification) {
        report.newSpecification(suite, new SpecificationReport(specification));
    }

    void recordFailure(String suite, String specification, AssertionError cause) {
        SpecificationReport specificationReport = new SpecificationReport(specification, Result.FAILURE, cause.getMessage());
        report.newSpecification(suite, specificationReport);
    }

    void recordError(String suite, String specification, Throwable cause) {
        cause.printStackTrace();
        SpecificationReport specificationReport = new SpecificationReport(specification, Result.ERROR, cause.getMessage());
        report.newSpecification(suite, specificationReport);
    }

    public static void main(String[] args) {
        current.run(StackSpec.class);
        current.printReport();
    }

    private void printReport() {
        ReportFormatter formatter = new ConsoleFormatter();
        formatter.format(report);
    }

    private void run(Class<StackSpec> stackSpecClass) {
        try {
            stackSpecClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
