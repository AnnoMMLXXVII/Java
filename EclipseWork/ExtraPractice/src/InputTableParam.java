import java.util.Scanner;

public class InputTableParam {

    public InputTableParam() {
        Scanner z = new Scanner(System.in);
        System.out.println("Enter 4 Numbers Please! ");
        int num1 = z.nextInt(),
                num2 = z.nextInt(),
                num3 = z.nextInt(),
                num4 = z.nextInt();
        setTable(num1, num2, num3, num4);
        z.close();
    }

    public static void main(String args[]) {
        new InputTableParam();
    }

    public void setTable(int num1, int num2, int num3, int num4) {
        for (int i = num3; i <= num4; i++) {
            for (int j = num1; j <= num2; j++) {
                System.out.print(j + " * " + i + " ");
            }
            System.out.println();
        }

    }

}
