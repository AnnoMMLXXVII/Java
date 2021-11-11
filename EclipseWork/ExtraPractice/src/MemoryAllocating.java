
public class MemoryAllocating {
    static int x = 1;

    static void method() {
        ++x;
        System.out.println("X: " + x);
    }

    static void methodA(int x) {
        x = x + 100;
        System.out.println("X: " + x);
    }

    public static void main(String args[]) {
        int x = 2;
        method();
        methodA(x);

        System.out.println("X : " + x);
    }
}
