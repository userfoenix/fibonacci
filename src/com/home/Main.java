package com.home;

public class Main {
    public static void main(String[] args) {
        int n = 100;
        int count = 10;
        int pool_size = 5;

        PerformanceTesterImpl tester = new PerformanceTesterImpl();
        
        try {
            FibCalcImpl fib = new FibCalcImpl(n, tester);
            PerformanceTestResult res = tester.runPerformanceTest(
               fib, count, pool_size
            );
            System.out.printf("Result: total=%d, min=%d, max=%d, ", res.getTotalTime(), res.getMinTime(), res.getMaxTime());
        }
        catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
    }
}