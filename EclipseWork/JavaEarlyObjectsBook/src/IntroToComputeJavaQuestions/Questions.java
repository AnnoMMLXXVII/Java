package IntroToComputeJavaQuestions;

import java.util.Random;
import java.util.Scanner;

public class Questions {

    private String chp101 = "Why is the Computer use by so many Different people, in so many different professions?";
    private String chp102 = "List five major hardware components of a computer system.";
    private String chp103 = "Internally, the CPU consists of what two Units?";
    private String chp104 = "Describe the steps in the fetch/decode/execute cycle.";
    private String chp105 = "What is a memory address? What is its purpose?";
    private String chp106 = "Explain why computers have both main memory and secondary storage.";
    private String chp107 = "What does the term \"multitasking\" mean?";

    private String[] chp1 = {chp101, chp102, chp103, chp104, chp105, chp106,
            chp107};

    public Questions() {
        printArr();
    }

    public static void main(String args[]) {
        new Questions();
    }

    String question;

    private void setVal(String val) {
        this.question = val;
    }

    private String getVal() {
        return question;
    }

    private int chp1Q(String[] chp1) {
        Random r = new Random();
        int size = 0;
        for (int i = 0; i < chp1.length; i++) {
            int rand = r.nextInt(chp1.length);
            question = chp1[rand];
            setVal(question);

        }
        return size;
    }

    private void printArr() {
        boolean flag = true;
        Scanner z = new Scanner(System.in);
        int flagger = z.nextInt();
        while (flag && flagger != -1) {
            flagger = z.nextInt();
            chp1Q(chp1);
            System.out.println(getVal());
        }
    }
}
