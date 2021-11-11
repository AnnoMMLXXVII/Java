package com.WarmUp.Triplets;

import java.util.Random;

public class Main {
    private Main m;
    private int[] a, b;
    private long[] time;
    private int alicePt, bobPt;
    private long totalTime = 0;
    private Random r;
    long start = 0, end = 0, diff = 0;

    public static void main(String[] args) {
        new Main();

    }

    public Main() {
        time = new long[100];
        for (int i = 0; i < time.length; i++) {
            diff = mainRun();
            if (i % 25 == 0) {
                System.out.printf("%2d || Speed: %d seconds\n", i, (diff));
            }
            time[i] = diff;
        }
        System.out.printf("Alice Point: %d\n", alicePt);
        System.out.printf("Bob Point: %d\n", bobPt);

        double average = 0;
        long sum = 0;
        for (int i = 0; i < time.length; i++) {
            sum = sum + time[i];
        }
        average = sum / time.length;
        System.out.printf("AVG: %f seconds\n", average * Math.pow(10, -9));
        System.out.printf("Total Time: %f", totalTime * Math.pow(10, -9));
    }

//	private Main getInstance() {
//		if (m == null) {
//			return new Main();
//		}
//		return m;
//	}

    private long mainRun() {
        alicePt = 0;
        bobPt = 0;
        start = (long) (System.nanoTime() * Math.pow(10, -9));

        for (int i = 0; i < Math.pow(10, 6); i++) {
            run();
            compareNumbers(a, b);
            totalTime = (long) (totalTime + start);
        }
        end = (long) (System.nanoTime() * Math.pow(10, -9));
        return (end - start);
    }

    private void run() {
        r = new Random();
        a = new int[3];
        b = new int[3];

        int cat1 = r.nextInt(100) + 1;
        int cat2 = r.nextInt(100) + 1;
        int cat3 = r.nextInt(100) + 1;

        a = initArray(a, cat1, cat2, cat3);
//		printArr(a, "Alice");

//		System.out.println();

        cat1 = r.nextInt(100) + 1;
        cat2 = r.nextInt(100) + 1;
        cat3 = r.nextInt(100) + 1;

        b = initArray(b, cat1, cat2, cat3);
//		printArr(b, "Bob");


    }

    private int[] initArray(int[] arr, int... cat) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = cat[i];
        }
        return arr;
    }

    private void printArr(int[] arr, String str) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%s's result: %3d | ", str, arr[i]);
        }
    }

    private void compareNumbers(int[] a, int[] b) {
        for (int i = 0; i < 3; i++) {
            if (a[i] < b[i]) {
                bobPt++;
            } else if (a[i] > b[i]) {
                alicePt++;
            }
        }
    }


}
