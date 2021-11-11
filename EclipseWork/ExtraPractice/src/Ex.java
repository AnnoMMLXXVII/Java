
public class Ex {
    static int x;

    private static void methodA() {
        System.out.println("x is: " + x);
    }

    private static void methodB() {
        int x = 123;
        methodA();
    }


    public static void main(String args[]) {

        x = 3;
        System.out.println(x);
        methodB();


    }
}
