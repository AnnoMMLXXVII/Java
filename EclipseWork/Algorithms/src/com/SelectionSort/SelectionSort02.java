package com.SelectionSort;

import java.util.Random;

public class SelectionSort02 {

    private int[] arr = new int[10];
    //	ArrayList<Integer> arr = new ArrayList<Integer>();
    private Random r = new Random();

    public SelectionSort02() {
//		int x = sort(arr, arr.length, numRand);
        long start = System.nanoTime();
        sort(arr);
        System.out.printf("Time (ns): %10d", (System.nanoTime() - start));
    }

    void sort(int arr[]) {
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];
            int j = i - 1;
            int mid = (0 + arr.length) / 2;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }

            arr[j + 1] = key;

        }
    }

    void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(i + "\t" + arr[i]);
        }
    }

//	int binSearch(int arr[], int s, int e, int key) {
//		int mid = (int) Math.floor((e+s)/2);
//		if (e>=s) {
//			if (arr[mid] > key) {
//				System.out.println("MID > : "+mid);
//				mid++;
//			}
//			else {
//				System.out.println("MID < : "+mid);
//				mid--;
//			}
//		}
//		return mid;
//	}


    int binSearch(int arr[], int s, int e, int key) {
        int mid = (int) Math.floor((e + s) / 2);
        System.out.println("MID: " + mid);
        if (e >= s) {
//			printArr(arr);
            if (arr[mid - 1] == key) {
//				System.out.println(mid + "\t\t"+arr[mid-1]);
                return mid;
            }

            if (arr[mid - 1] > key) {
//				System.out.println(mid + "\t\t"+arr[mid-1]);
                binSearch(arr, s, mid - 1, key);
            }

            return binSearch(arr, mid + 1, e, key);
        }
        return -1;
    }


    public static void main(String[] args) {
        new SelectionSort02();
    }

}
