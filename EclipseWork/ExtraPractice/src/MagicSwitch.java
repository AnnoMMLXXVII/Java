import java.util.Scanner;

public class MagicSwitch {

    public static void main(String args[]) {
//		states();
        sentence();
        //loop1();
    }


    private static void loop1() {
        for (int x = 0; x > -1; ) {
            System.out.println(x);
        }
    }

    private static void sentence() {
        String a = "ABCD";
        String b = a;
        String c = b;
        b = new String("XYZ");
        a = b;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    private static void loop2() {
        for (int x = 1; ; x += 2) {
            System.out.println(x);
        }
    }


    private static void magicNumber() {
        int magicNum = 100;
        switch (magicNum % 7) {
            case 1:
                magicNum *= 3;
            case 3:
                magicNum += 5;
            case 5:
                magicNum -= 2;
            default:
                magicNum = 7;
        } // endae SWITCH
        System.out.println("Magic Mike Number: " + magicNum);
    }

    private static void whileStates() {
        int a = 20;
        while (a > 0) {
            if (a % 2 == 0) {
                System.out.println("a : " + a);
                a = a / 2;
            } else {
                a = a - 1;
            }
        }
    }


    private static void whileStates01() {
        int a = 10;
        while (a > 0) {
            System.out.println("a : " + a);
            a = a / 2;
        }
    }


    private static void states() {
        int a, b;
        Scanner z = new Scanner(System.in);
        System.out.println("Input 1: ");
        a = z.nextInt();
        System.out.println("Input 2: ");
        b = z.nextInt();

        if (a < 0 && b > 0 || a > 0 && b < 0) {
            System.out.println("a and b have different signs");
            if (a < 0) {
                System.out.println("a is a neagative value");
            } else {
                System.out.println("b is a negative value");
            }
        } else if (a == 0 || b == 0) {
            System.out.println("At least one of them is ZERO!");
        } else {
            System.out.println("they have the same sign!");
        }

        z.close();
    }

}
