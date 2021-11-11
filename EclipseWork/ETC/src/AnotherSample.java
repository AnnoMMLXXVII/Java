

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AnotherSample {

    private static Random r = new Random();

    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        initializeList(numbers, 10);
        Collections.shuffle(numbers);
        Integer[] list = new Integer[numbers.size()];

        String message = "";

        int index = r.nextInt(numbers.size()) + 0;
        for (int i = 0; i < list.length; i++) {
            list[i] = numbers.remove(index);
            index = (numbers.size() == 1) ? r.nextInt(numbers.size()) + 0 : 0;
            message = message + String.format("A[%d] = %d\n", i, list[i]);
        }
        System.out.println(message + 5);
    }

    private static void initializeList(List<Integer> list) {
        int values = 0;
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0 && i > 0) {
                values++;
            }
            list.add(values);
        }
    }

    private static void initializeList(List<Integer> list, int n) {
        int values = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0 && i > 0) {
                values++;
            }
            list.add(values);
        }
    }
}

