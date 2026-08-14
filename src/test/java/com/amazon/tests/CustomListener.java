package com.amazon.tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[STARTING TEST]: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[SUCCESS]: " + result.getName() + " passed successfully.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.err.println("[FAILURE]: " + result.getName() + " failed! Triggering screenshot logic...");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[SKIPPED]: " + result.getName() + " was skipped.");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("[SUITE COMPLETED]: " + context.getName());
    }
}