package com.Binary_InsertionSort;

import java.util.Random;

public class BinSertSort {
    private int[] arr;
    private Random r = new Random();
    private int time, InsertTime;
    ;

    public static void main(String args[]) {
        new BinSertSort();
    }

    public BinSertSort() {
        initRun();
    }

    public BinSertSort(boolean flag, int size) {
        arr = initArr(arr, size);
        if (flag == true) {
            binInsertSort(arr, arr.length);
        } else {
            insertSort(arr, arr.length);
        }
    }

    void initRun() {
        int n = 6, nn = 10;
        double avg = 0, avgI = 0;
        long sum = 0, sumI = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= nn; j++) {
                BinSertSort bis = new BinSertSort(true, (int) Math.pow(10, i));
                sum = sum + bis.getTime();
            }
            avg = sum / nn;
            System.out.printf("Avg : %12.2f\n", avg);

//			for (int j = 1; j <=nn; j++) {
//				BinInsertSort bisI = new BinInsertSort((int)Math.pow(10, i), false);
//				sumI = sumI + bisI.getInsertTime();
//			}
//			avgI = sum/nn;
//			System.out.printf("AvgI: %12.2f\n", avgI);
//			System.out.println("------------------------------------------");
        }


        System.out.println("------------------------------------------");
        new BinInsertSort();
    }

    public void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf(" %d | ", arr[i]);
        }
        System.out.println();
    }

    private int[] initArr(int arr[], int n) {
        int rand = r.nextInt(100) + 0; // generates random number
        arr = new int[n]; // new instance of array with init size
        for (int i = 0; i < n; i++) {
            arr[i] = rand; // populates with random number
            rand = r.nextInt(100) + 0; // generates another new number
        } // endae for
        return arr; // returns the arr
    } // endae initArr method

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getInsertTime() {
        return InsertTime;
    }

    public void setInsertTime(int insertTime) {
        InsertTime = insertTime;
    }

//	void sort (int array[]) {
//		for (int i = 1; i < array.length; i++) 
//        { 
//            int x = array[i]; 
//            
//            // Find location to insert using binary search 
//            int j = Math.abs(Arrays.binarySearch(array, 0, i, x) + 1); 
//  
//            //Shifting array to one location right 
//            System.arraycopy(array, j, array, j+1, i-j); 
//  
//            //Placing element at its correct location 
//            array[j] = x; 
//        } 
//    }

    int binSearch(int a[], int low, int high, int key) {
        if (high <= low) {
            return (key > a[low]) ? (low + 1) : low;
        }
        int mid = (low + high) / 2;
        if (key == a[mid]) {
            return mid + 1;
        }
        if (key > a[mid]) {
            return binSearch(arr, mid + 1, high, key);
        }
        return binSearch(arr, low, mid - 1, key);
    }


    void binInsertSort(int a[], int n) {
        int ins, i, j, tmp; // init vairables
        long start = System.nanoTime(); // start time!
        for (i = 1; i < n; ++i) { // O(n)
            j = i - 1;
            tmp = a[i];
            ins = binSearch(a, 0, j, a[i]); // (O(log(n))
            if (ins >= 0 && ins < arr.length) {
                System.out.println("INSERT: " + ins);
            }
            // return value of bin search to find the place to insert
            while (j >= ins) { // (O(n)
                // inserting current j value into the next value (ascending)
                a[j + 1] = a[j]; // swapping
                j--;
            }
            // inserting the stored value @ i, into the returned value of binSearch
            a[ins] = tmp; // finished swapping
        }
        setTime((int) (System.nanoTime() - start));
    }


    void insertSort(int a[], int n) {
        long start = System.nanoTime();
        for (int j = 1; j < n; j++) {
            int key = a[j];
            int i = j - 1;
            while ((i > -1) && (a[i] > key)) {
                a[i + 1] = a[i];
                i--;
            }
            a[i + 1] = key;
        }
        setInsertTime((int) (System.nanoTime() - start));
    }


}
