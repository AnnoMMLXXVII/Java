package com.Other.Sorts;

public class PrimeNumbers {

    public static void main(String args[]) {
        new PrimeNumbers(2077);
    }

    private PrimeNumbers(long num) {
        initRun(num);
    }

    private void initRun(long num) {

        if (IsPrime(num)) {
            System.out.println("\nPRIME!");
        }
    }

    private boolean IsPrime(long num) {
        num = num % 2;
        System.out.printf(" %d , ", num);
        return (num == 1) ? true : IsPrime(num);
    }
}
