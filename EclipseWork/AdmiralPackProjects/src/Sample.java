import java.util.Scanner;

public class Sample {

    public static void main(String args[]) {

        int number1, number2, number3;

        Scanner input = new Scanner(System.in);

        System.out.println("Please input 3 Numbers: ");

        number1 = input.nextInt();
        number2 = input.nextInt();
        number3 = input.nextInt();


//		System.out.println("Number 1: "+number1);
//		System.out.println("Number 2: "+number2);
//		System.out.println("Number 3: "+number3);

        comparing(number1, number2, number3);

        input.close();


    } // endae main method


    public static void comparing(int num1, int num2, int num3) {
        if (num1 < num2 && num2 < num3) {
            System.out.println("IN ORDER!");
        } // enae IF

        else {
            System.out.println("NOT IN ORDER!");
        } // endae ELSE


    } // endae comparing


} // endae Class
