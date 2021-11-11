
public class AdvancedAstrology {

    public static void main(String... args) {
        printTrianlge(5);
    }

    private static void printTrianlge(int size) {
        for (int i = 1; i <= size; i++) {
            printSpace(i, size);
            printStars(i);
            System.out.println();
        }
    }

    private static void printSpace(int i, int size) {
        for (int j = i; j <= size; j++) {
            System.out.print(" ");
        }
    }

    private static void printStars(int i) {
        for (int j = 1; j <= i; j++) {
            System.out.print("*");
        }
    }
}
