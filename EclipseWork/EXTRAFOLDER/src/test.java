import java.util.ArrayList;

public class test {
    public static void main(String args[]) {

        GameParlour p1 = new GameParlour();
        p1.add("PUBG colsole", "PUBG", 500);
        p1.add("cs colsole", "cs:go", 350);
        p1.add("gta colsole", "gta 5", 500);
        p1.add("mario  colsole", "mario", 100);

        ArrayList<VideoGameStation> arr = p1.getArray();
        // changed p1.arr.size() ---> p1.getArray().size();
        // getArray() is a getter method that has been added in the GameParlour class
//        System.out.println(p1.getArray().size());

        p1.bookingGameStation(2, "Ram", "regular", "25 jan", 2);
        p1.bookingGameStation(3, "Shyam", "irregular", "25 jan", 3);

        p1.clearBooking(3);

        p1.stationCurrentlyAvailable();
        System.out.println("BEFORE SORT");
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        } // endae ENHANCED FOR

        p1.ascendingcollect();

        System.out.println("AFTER SORT");

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        } // endae ENHANCED FOR
    }
}