package com.home;

/**
 * A Fibonacci calculator.
 */

public class FibCalcImpl implements FibCalc, Runnable {
    private int n;
//    private PerformanceTester tester;
    private PerformanceTesterImpl tester;

    FibCalcImpl(int n, PerformanceTesterImpl tester){
        this.n = n;
        this.tester = tester;
    }

    @Override
    public void run() {
        TimeDiff watcher = new TimeDiff();
        long res = fib(n);
        long tm = watcher.stop();
        tester.syncTime(tm);
        System.out.println("tm="+tm);
    }

    /**
     * Calculates the Fibonacci number with the given index.
     * Examples:
     * fib(1) = 1    <br>
     * fib(2) = 1    <br>
     * fib(3) = 2    <br>
     * fib(4) = 3    <br>
     * fib(5) = 5    <br>
     */
    public long fib(int n){
       if (n<2){
           return n;
       }
       long a = 0;
       long b = 1;
       long res = 0;
       for (int i=0; i<n-1; i++){
           res = a + b;
           a = b;
           b = res;
       }
       return res;
    }
}

class TimeDiff {
    private long start = System.nanoTime();

    long stop() {
        return System.nanoTime() - start;
    }
}