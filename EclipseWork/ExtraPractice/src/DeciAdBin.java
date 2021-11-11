import java.util.Scanner;

public class DeciAdBin {
    public static void main(String[] args) {
        int n, a;
        String x = "";
        Scanner s = new Scanner(System.in);
        System.out.print("Input: ");
        n = s.nextInt();
//		System.out.println("\t\t"+n+ " / 2 = " + n/2);
        while (n > 0) {
            a = n % 2;
//        	System.out.println(n + " % 2 = "+a);
            x = x + "" + a;
//	        System.out.println("\t\t\t\tCombines the Remainders: " + x);
            n = n / 2;
//            System.out.println("\t\t"+n+ " / 2 = " + n);
        }
        System.out.println("Binary number: " + x);
        s.close();
    }
}
