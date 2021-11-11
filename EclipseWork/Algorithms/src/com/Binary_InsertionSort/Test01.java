package com.Binary_InsertionSort;

import java.util.Random;

public class Test01 {

    private int InsertTime;
    private int[] arr;

    public static void main(String args[]) {
        new Test01();
    }

    Test01() {
        initRun();
    }

    void initRun() {

//		arr = initArr(arr, 10);
        arr = new int[]{2, 4, 7, 1, 2, 90, 4, 2, 5, 1, 2, 48, 23, 45, 4, 35, 64, 35, 35, 35335, 35, 534, 5353, 35345345, 34535, 3534, 5435, 123, 2423, 4, 8, 976, 6, 90};
//		printArr(arr);
//		int rand = getRandom();
//		System.out.printf("Random #: %d\n",rand);
//		int j = isFound(arr, 0, arr.length-1, rand);
//		int z, j = -1;
//		for (z = 1;z <=arr.length;z++) {
//			j = binSearch(arr, z, (z+1), arr[z]);
//		}

//		System.out.printf("Found @ : %d\n\n",j);

//		int j = binarySearch(arr, 0, arr.length-1,  11);
//		
//		if(j < 0) {
//			System.out.printf("DNE || %d", j);
//		}
//		else {
//			System.out.printf("FOUND @ %d", j);
//		}


        insertSort(arr, arr.length);
//		inSort(arr, arr.length);
        System.out.printf("TIME: %14d", getInsertTime());
        printArr(arr);
    }

    private int[] initArr(int[] arr, int n) {
        Random r = new Random();
        int rand = r.nextInt(10) + 0; // generates random number
        arr = new int[n]; // new instance of array with init size
        for (int i = 0; i < n; i++) {
            arr[i] = rand; // populates with random number
            rand = r.nextInt(10) + 0; // generates another new number
        } // endae for
        return arr; // returns the arr
    } // endae initArr method

    private int getRandom() {
        Random r = new Random();
        return r.nextInt(100) + 0; // generates random number
    }

    public void printArr(int[] arr) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(" %2d | ", arr[i]);
        }
        System.out.println();
    }

    public void printArrViaBounds(int[] arr, int low, int high) {
        System.out.println();
        System.out.printf("%2d <--", low);
        for (int i = low; i < high; i++) {
            System.out.printf("%2d, ", arr[i]);
        }
        System.out.printf("-->%2d", high);
        System.out.println();
    }

    int isFound(int[] arr, int low, int high, int tmp) {
        int found = -1;
        int mid = (high + low) / 2;
//		System.out.printf("KEY: %d || MID: %d\n",tmp, mid);
        int midVal = 0;
        while (arr[mid] != tmp) {
            midVal = 0;
            if (tmp == arr[mid]) {
                mid = midVal;
                ;
            } else {
                if (tmp > arr[mid]) {
                    return mid = ((mid + 1) + high) / 2;
                } else {
                    return mid = ((mid - 1) + low) / 2;
                }
            }
            found = mid;

        }
        return found;
    }

    int insertVal(int[] arr, int low, int high, int x) {
//		printArrViaBounds(arr, low, high);
//		System.out.printf("KEY VAL: %2d\n", x);
        if (low == high) {
            int midVal = -1;
//			printArrViaBounds(arr, low, high);
//			System.out.printf("---> %2d @ %2d : %2d\n",arr[high], (high+1), high);
            midVal = (x > arr[low]) ? (low + 1) : low;
//			return (x > arr[low]) ? (low+1): low;
//			System.out.printf("Key Val %2d && return VAL : %2d\n",x, midVal);
            return midVal;
        }
        int mid = (high + low) / 2;

        if (arr[mid] == x) {
            return mid;
        }
        if (arr[mid] > x) {
            return insertVal(arr, low, (mid - 1), x);
        } else {
            return insertVal(arr, (mid + 1), high, x);
        }
    }

    void inSort(int a[], int n) {
        long start = System.nanoTime();
        for (int j = 1; j < n; j++) {
            int key = a[j];
            int i = j - 1;
            while ((i > -1) && (a[i] > key)) {
                a[i + 1] = a[i];
                i--;
//                printArr(arr);
            }
            a[i + 1] = key;
        }
        setInsertTime((int) (System.nanoTime() - start));
    }

    void insertSort(int a[], int n) {
        int z;
        long start = System.nanoTime();
        for (int j = 1; j < n; j++) {
            int key = a[j];
            int i = j - 1;
//            printArr(arr);
            z = insertVal(arr, 0, j, key);
//            	int k = binarySearch(arr, 0, j, key);
//            	System.out.printf("INSERT: %2d\n", k);
//            	System.out.printf("KEY: %2d\n",key);
//            if (z == (-1)) {
            while ((i > (-1)) && (a[i] > key)) {
                a[i + 1] = a[i];
                i--;
//						printArr(arr);
//				}
//            	if(z == (-1)) { // DNE
//            	a[i+1] = key;
            }
            a[z] = key;
//            System.out.println(i+ "--");
//            System.out.printf("Post I=%d ---- ", i);
//            printArr(arr);
        }
        setInsertTime((int) (System.nanoTime() - start));
    }

    public int getInsertTime() {
        return InsertTime;
    }

    public void setInsertTime(int insertTime) {
        InsertTime = insertTime;
    }

}
