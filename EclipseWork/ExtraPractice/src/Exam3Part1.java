import java.util.Random;

public class Exam3Part1 {
    private static void printNums(int n, int userNum) {
        userNum++;
        int i, j, num = 1;
        for (i = 0; i < n; i++) {
            for (j = 0; j <= i; j++) {
                if (num == userNum) {
                    break;
                }
                System.out.print(num + " ");
                num = num + 1;
            } // endae FOR
            System.out.println();
        } // endae FOR
    } // endae printNums

    private static void run() {
        Random r = new Random();
        int rand = r.nextInt(54) + 1;
        System.out.printf("Value Chosen: %d\n", rand);
        int n = 10;  // 55 is MAX Value if number of rows == 10;
        printNums(n, rand);
    }

    // Main Method
    public static void main(String args[])
    // Run this 3 times
    {
        for (int i = 0; i < 3; i++) {
            run();
        }
    }
} // endae CLASS
