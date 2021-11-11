import java.util.Arrays;

public class InsertionSortSample {

    public static void main(String[] args) {
        String[] words = {"big", "ALL", "all", "1", "FOX", "zebra", "END", "great", "FOX", "quit", "episode", "zebra",
                "big", "all",
//				"1" 
        };
        System.out.println("Unsorted array: " + Arrays.toString(words));
        stringInsertionSort(words);
    }

    public static void stringInsertionSort(String array[]) {
        int size = 0;
        String[] sortedArray = new String[size];
        System.out.println(sortedArray.length);
        for (int i = 0; i < array.length; i++) {
//			if (Arrays.asList(sortedArray).contains(array[i])) {
//				break;
//			}
            int k = i + 1;
            size = k;
            System.arraycopy(sortedArray, 0, sortedArray, size, sortedArray.length);
            boolean flag = (array[i].compareTo(sortedArray[size]) < 0);
//			System.out.printf("%s ? %s = %s\n", array[i], sortedArray[k], flag);
            String key = array[i];
            while (flag && k < sortedArray.length) {
                System.out.println(key);
//			if (flag) {
//				sortedArray[k + 1] = sortedArray[k];
//				sortedArray[k] = array[i];
//
//			} else {
//				sortedArray[k + 1] = array[i];
//			}
//				System.out.println("Sorted array:   " + Arrays.toString(sortedArray));
            }
        }
        System.out.println("Sorted array:   " + Arrays.toString(sortedArray));
    }
}
