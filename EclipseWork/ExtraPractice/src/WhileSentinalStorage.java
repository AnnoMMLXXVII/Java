import java.util.ArrayList;
import java.util.Scanner;

public class WhileSentinalStorage {

    public static void main(String args) {

        Scanner z = new Scanner(System.in);
        System.out.println("Input Numbers: ");
        int input = z.nextInt();
        ArrayList<Integer> arrList = new ArrayList<Integer>();

        while (input != (-1)) {
            input = z.nextInt();
            arrList.add(input);

            for (Integer i : arrList) {
                if (i == input) {
                    System.out.println("MATCHES");
                }
            }
        }


    } // endae Main


}	
