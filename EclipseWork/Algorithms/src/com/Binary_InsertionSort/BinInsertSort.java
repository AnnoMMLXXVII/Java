package com.Binary_InsertionSort;

import java.util.Random;

public class BinInsertSort {
    private int[] arr;
    private Random r = new Random();
    private int time, InsertTime;

    public static void main(String[] args) {
        new BinInsertSort();
    }

    public BinInsertSort() {
        initRun();
    }

    public BinInsertSort(int size, boolean flag) {
        arr = initArr(arr, size);
        setArr(arr);
        // if flag == false, will call the bin-Insert sort method
        if (flag == false) {
            binInsertSort(arr, arr.length);
        } // endae if
        // else flagg == true, will call original insertion sort method
        else {
            insertSort(arr, arr.length);
        } // endae else
    } // endae BinInsertSort CONSTRUCTOR


    public void initRun() {
        int n = 6, nn = 10; // initialize number of iteration n and nn
        double avg = 0, avgInsert = 0;
        long sum = 0, sumInsert = 0;
        // variable initializations
        for (int i = 1; i <= n; i++) {
            // during for loop iteration, outer for loop's i will help initialize the array size
//			for (int j = 1; j < nn; j++) {
            // inner for loop will make new object with size as param 9 times
//				BinInsertSort bis = new BinInsertSort((int)Math.pow(10, i), false);
//				System.out.printf("%d | ",bis.getArr()[i]);
//				sum = sum + bis.getTime(); 
            // everytime it finishes sorting, a setter and getter method will act
            // the object will call the getTime() method to get the calculated sorting time
            // that time will be summed
//			} // endae first nested for loop
//			avg = sum/(nn-1); // findin the average time for Bin-Insert Aglorithm
//			System.out.printf("%6d || Bin-Insert:\t %13.2f\n",(int)Math.pow(10, i),avg);
//			System.out.printf("\n\n------- AVG Time (ns) for N = %6d : %13.2f ----------\n\n", (int)Math.pow(10, i), avg);

//	-------- BELOW CODE DOES THE SAME AS CODE ABOVE, COMPUTES ORIGINAL INSERTION SORT TIME ---------------------------
            for (int j = 1; j < nn; j++) {
                BinInsertSort bisI = new BinInsertSort((int) Math.pow(10, i), true);
                sumInsert = sumInsert + bisI.getInsertTime();
            } // endae second nested for loop
            avgInsert = sumInsert / (nn - 1);
//			System.out.printf("%6d || Insert  : \t %13.2f\n\n",(int)Math.pow(10, i),avgInsert);
            System.out.printf("Avg : %12.2f\n", avgInsert);
//			System.out.printf("\n\n------- AVG Time (ns) for N = %6d : %13.2f ----------\n\n", (int)Math.pow(10, i), avgInsert);
        }    // endae outer for loop
    } // endae initRun method

    // Setters and Getters for instance variables, time, InsertTime;
    private void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public int getInsertTime() {
        return InsertTime;
    }

    public void setInsertTime(int insertTime) {
        InsertTime = insertTime;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    // method to initialize array
    // param: int arr[] , int n --> size
    private int[] initArr(int arr[], int n) {
        int rand = r.nextInt(100) + 0; // generates random number
        arr = new int[n]; // new instance of array with init size
        for (int i = 0; i < n; i++) {
            arr[i] = rand; // populates with random number
            rand = r.nextInt(100) + 0; // generates another new number
        } // endae for
        return arr; // returns the arr
    } // endae initArr method


    // basic binary search, through recursion
    int binSearch(int a[], int low, int high, int key) {
        int mid; // init mid value
        if (low == high) {
            return low; // if the low and high bounds are the same, return low
        }
        mid = low + ((high - low) / 2); // finds the middle of the array between low and high
        if (key != a[mid]) {
            if (key > a[mid]) {
                // if the key > value @ mid, return recursive call for upper half of array
                return binSearch(a, mid + 1, high, key);
            } else if (key < a[mid]) {
                // else if key < value @ mid, return recursive call for lower half of array
                return binSearch(a, low, mid, key);
            }
        }
        return mid;
    }

    // 	combination of binary and insertion search/sort method
    // no return
    // param : int a[] , int n --> size
    // n * (lg(n)+(n)) = nlg(n) + (n^2) = O(n^2)
    void binInsertSort(int a[], int n) {
        int ins, i, j, tmp; // init vairables
        long start = System.nanoTime(); // start time!
        for (i = 1; i < n; i++) { // O(n)
            ins = binSearch(a, 0, i, a[i]); // (O(log(n))
            // return value of bin search to find the place to insert
            tmp = a[i]; // stores current value a[i] into tmp
            for (j = i - 1; j >= ins; j--) { // (O(n)
                // inserting current j value into the next value (ascending)
                a[j + 1] = a[j]; // swapping
            }
            // inserting the stored value @ i, into the returned value of binSearch
            a[ins] = tmp; // finished swapping
        }
        setTime((int) (System.nanoTime() - start));
    }

    // REGULAR INSERTION SORT FOR COMPARISION PURPOSES
    // Complexity : O(n^2)
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
