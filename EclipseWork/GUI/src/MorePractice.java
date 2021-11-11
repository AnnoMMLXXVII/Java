
public class MorePractice {
    public static void printX(int i) {
        if (i <= 0) {
            return;
        }
        printX(i - 1);
        System.out.println(i);
    }

    public static int doY(int i) {
        if (i >= 3) {
            return 0;
        }
        return i - doY(i + 1);
    }

    public static void main(String args[]) {
//		printX(4);
//		System.out.println("___________\n");
        int x = doY(0);
        System.out.println(x);
    }

}
