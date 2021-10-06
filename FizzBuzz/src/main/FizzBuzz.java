package main;

import java.util.function.IntConsumer;

public class FizzBuzz {

    public static void main(String[] args) throws InterruptedException {


        final int input = 15;

        //System.out.println("Input: " + input);
        FizzBuzz fizzBuzz = new FizzBuzz(input);

        Thread threadBuzz = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.println("Buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadFizz = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("Fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread threadFizzBuzz = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("FizzBuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread threadNumber = new Thread(() -> {
            try {
                fizzBuzz.number((n) -> System.out.println(n));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //System.out.println("Output: ");

        threadFizz.start();
        threadBuzz.start();
        threadFizzBuzz.start();
        threadNumber.start();

    }

    private int n;
    private int actual = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {

        while (actual <= n) {
            if (actual % 3 == 0 && actual % 5 != 0) {
                printFizz.run();
                actual = actual + 1;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    // printBuzz() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {

        while (actual <= n) {
            if (actual % 3 != 0 && actual % 5 == 0) {
                printBuzz.run();
                actual = actual + 1;
                notifyAll();
            } else {
                wait();
            }
        }
    }

    // printFizzBuzz() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {

        while (actual <= n) {
            if (actual % 3 == 0 && actual % 5 == 0) {
                printFizzBuzz.run();
                actual = actual + 1;
                notifyAll();
            } else {
                wait();
            }
        }
    }


    // printNumber(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {

        while (actual <= n) {
            if (actual % 3 != 0 && actual % 5 != 0) {
                printNumber.accept(actual);
                actual = actual + 1;
                notifyAll();
            } else {
                wait();
            }
        }
    }



}

// First approach without Threads
    /*
    private void init() {

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                fizzbuzz();
            } else if (i % 3 == 0 && i % 5 != 0) {
                fizz();
            } else if (i % 3 != 0 && i % 5 == 0) {
                buzz();
            } else {
                number(i);
            }
            this.n = n++;
        }
    }
    */
