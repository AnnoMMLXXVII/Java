package ClassesAndObjects;

import java.util.Scanner;

/*
 * fields
 * 	- that holds three test scores
 * class should have accessor and mutator methods
 * 		- this should be related to the three fields
 *  another method to return the average of all three test scores
 *  demonstrate this class but writing another program to create an instance of this class
 *  	--> program will ask user to enter three scores (which will be stored in the TestScore object)
 *  	--> program will display three scores and the average
 */
public class TestScores {
    private int score1, score2, score3;

    public TestScores(int score1, int score2, int score3) {
        setScore1(score1);
        setScore2(score2);
        setScore3(score3);
        getAverage();
    }

    public void setScore1(int score) {
        this.score1 = score;
    } // endae mutator setScore

    public int getScore1() {
        return score1;
    } // endae Accessor getScore

    public void setScore2(int score) {
        this.score2 = score;
    } // endae mutator setScore2

    public int getScore2() {
        return score2;
    } // endae Accessor getScore2

    public void setScore3(int score) {
        this.score3 = score;
    } // endae mutator setScore

    public int getScore3() {
        return score3;
    } // endae Accessor getScore3

    private double average(int... testScore) {
        double avg = 0;
        int sum = 0;
        int indicator = 0;
        for (int i = 0; i < testScore.length; i++) {
            sum = testScore[i] + sum;
            indicator = i;
        } // endae FOR
        avg = (double) sum / (double) (indicator + 1);
        return avg;
    } // ednae average

    public double getAverage() {
        return average(getScore1(), getScore2(), getScore3());
    }

    public static void main(String args[]) {
        Scanner z = new Scanner(System.in);
        System.out.println("Enter three numbers: ");
        int s1 = z.nextInt(), s2 = z.nextInt(), s3 = z.nextInt();
        TestScores ts = new TestScores(s1, s2, s3);
        System.out.println("Scores: " + s1 + "," + s2 + "," + s3);
        System.out.printf("Average: %.2f\nRounded: %.1f", ts.getAverage(),
                Math.ceil(ts.getAverage()));
        z.close();
    }

} // endae CLASS
