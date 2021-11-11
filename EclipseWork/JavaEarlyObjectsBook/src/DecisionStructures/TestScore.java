package DecisionStructures;

import java.util.Random;

public class TestScore {
    private int testScore1;
    private int testScore2;
    private int testScore3;

    public TestScore(int testScore1, int testScore2, int testScore3) {
        this.testScore1 = testScore1;
        this.testScore2 = testScore2;
        this.testScore3 = testScore3;
    } // endae constructor

    private int totalSum() {
        return testScore1 + testScore2 + testScore3;
    } // endae totalSum();

    public int getScore() {
        return (int) Math.ceil(totalSum() / 3);
    } // endae getScore();

    public String grade(int getScore) {
        int avg = getScore;
        String grade = null;
        if (avg < 60) {
            return "F";
        } // endae IF
        else if (avg >= 60 && avg < 70) {
            if (avg >= 60 && avg < 63) {
                grade = "D-";
            } // endae IF
            else if (avg >= 63 && avg <= 66) {
                grade = "D";
            } // endae ELSE IF
            else if (avg >= 67 && avg <= 69) {
                grade = "D+";
            } // endae ELSE IF
            return grade;
        } // endae ELSE IF
        else if (avg >= 70 && avg < 80) {
            if (avg >= 70 && avg < 73) {
                grade = "C-";
            } // endae IF
            else if (avg >= 73 && avg <= 76) {
                grade = "C";
            } // endae ELSE IF
            else if (avg >= 77 && avg <= 79) {
                grade = "C+";
            } // endae ELSE IF
        } // endae ELSE IF

        else if (avg >= 80 && avg < 90) {
            if (avg >= 80 && avg < 83) {
                grade = "B-";
            } // endae IF
            else if (avg >= 83 && avg <= 86) {
                grade = "B";
            } // endae ELSE IF
            else if (avg >= 87 && avg <= 89) {
                grade = "B+";
            } // endae ELSE IF
            return grade;
        } // endae ELSE IF
        else if (avg >= 90 && avg <= 100) {
            if (avg >= 90 && avg < 93) {
                grade = "A-";
            } // endae IF
            else if (avg >= 93 && avg <= 96) {
                grade = "A";
            } // endae ELSE IF
            else if (avg >= 97 && avg <= 100) {
                grade = "A+";
            } // endae ELSE IF
            return grade;
        } // endae ELSE IF
        return grade;
    } // endae grade;


    public static void main(String args[]) {
        String[] grades = new String[10];
        int[] scores = new int[10];
        Random r = new Random();
        int rand;
        int rand1;
        int rand2;
        TestScore ts;

        for (int i = 0; i < 10; i++) {
            rand = r.nextInt(100 - 0) + 10;
            rand1 = r.nextInt(100 - 0) + 10;
            rand2 = r.nextInt(100 - 0) + 10;
            ts = new TestScore(rand, rand1, rand2);
            System.out.println("Test Scores: " + rand + " " + rand1 + " " + rand2);
            System.out.println("Number Score: " + ts.getScore());
            System.out.println("Letter Grade: " + ts.grade(ts.getScore()));
            grades[i] = ts.grade(ts.getScore());
            scores[i] = ts.getScore();
        } // endae FOR
        int sum = 0;
        int counter = 0;
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " | " + grades[i] + " || " + scores[i]);
            sum = sum + scores[i];
            counter++;
        } // endae FOR
        int avg = sum / counter;
        System.out.println("Average: " + avg);
    } // endae MAIN

}
