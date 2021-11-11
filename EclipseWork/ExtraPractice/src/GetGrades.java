import java.util.Scanner;

public class GetGrades {

    double att = .05, hw1 = .1, hw2 = .1, hw3 = .1, pro1 = .2, ex1 = .2, ex2 = .25;


    public GetGrades() {
        Scanner z = new Scanner(System.in);
        System.out.println("attendance ");
        double input = z.nextDouble();
        System.out.println("hw1 ");
        double input2 = z.nextDouble();
        System.out.println("hw2 ");
        double input3 = z.nextDouble();
        System.out.println("hw3 ");
        double input4 = z.nextDouble();
        System.out.println("ex1 ");
        double input5 = z.nextDouble();
        System.out.println("ex2 ");
        double input6 = z.nextDouble();
        System.out.println("proj1 ");
        double input7 = z.nextDouble();

        att = att * input;
        hw1 = hw1 * input2;
        hw2 = hw2 * input3;
        hw3 = hw3 * input4;
        ex1 = ex1 * input5;
        ex2 = ex2 * input6;
        pro1 = pro1 * input7;

        double total = att + hw1 + hw2 + hw3 + ex1 + ex2 + pro1;

        System.out.println("Grade: " + total);
    }

    public static void main(String args[]) {
        new GetGrades();
    }


}
