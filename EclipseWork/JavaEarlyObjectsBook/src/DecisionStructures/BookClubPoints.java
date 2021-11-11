package DecisionStructures;

import java.util.Random;

// Book purchases and points
// 0 --> 0
// 1 --> 5
// 2 --> 15
// 3 --> 30
// 4+ --> 60

public class BookClubPoints {

    private static BookClubPoints bcp = null;

    public static BookClubPoints getInstance() {
        if (bcp == null) {
            bcp = new BookClubPoints(); // new instantiation of BookClubPoints
        } // endae IF
        return bcp;
    } // endae

    private int numberOfBooks;

    public void mainRun(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
        System.out.printf("Points: %d", calculatePoints());
    }

    private int calculatePoints() {
        int points;
        if (numberOfBooks <= 0) {
            points = 0;
        } // endae IF
        else if (numberOfBooks == 1) {
            points = 5;
        } else if (numberOfBooks == 2) {
            points = 15;
        } // endae ELSE IF
        else if (numberOfBooks == 3) {
            points = 30;
        } // endae ELSE IF
        else {
            points = 60;
        } // endae ELSE
        return points;
    } // endae calculatePoints

    public static void main(String args[]) {
        Random r = new Random();
        int randBookNumber = r.nextInt(10);
        System.out.printf("Books: %d\n", randBookNumber);
        BookClubPoints.getInstance().mainRun(randBookNumber);
    }

}
