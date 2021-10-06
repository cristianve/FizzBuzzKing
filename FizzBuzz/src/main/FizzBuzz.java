package main;

public class FizzBuzz {

    int n;

    FizzBuzz(int n) {
        this.n = n;
    }

    void fizz() {
        System.out.println("fizz");
        // printFizz() outputs "fizz".
    }

    void buzz() {
        System.out.println("buzz");
        // printBuzz() outputs "buzz".
    }

    void fizzbuzz() {
        System.out.println("fizzbuzz");
        // printFizzBuzz() outputs "fizzbuzz".
    }

    void number(int i) {
        System.out.println(i);
        // printNumber(x) outputs "x", where x is an integer.
    }


    public static void main(String[] args) {
        int n = 15;
        FizzBuzz fizzBuzz = new FizzBuzz(n);
        fizzBuzz.init();
    }

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

}
