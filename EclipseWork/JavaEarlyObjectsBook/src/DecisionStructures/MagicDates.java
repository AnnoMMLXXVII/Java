package DecisionStructures;

import java.util.Random;

// 06 / 10 / 60; 6 * 10 = 60;
// Month * day = last two digits of the year
// method named isMagic that returns true if the date passed to the constructor is magic or false otherwise
// write program that asks user to enter day, month, and two-year digit as integers

// program should create instance of magicdates. 


public class MagicDates {
    private static MagicDates md = null;

    private static MagicDates getInstance() {
        if (md == null) {
            md = new MagicDates();
        } // endae IF
        return md;
    } // endae getInstance();

    int month, day, twoDigYear;

    public void mainRun(int month, int day, int twoDigYear) {
        this.month = month;
        this.day = day;
        this.twoDigYear = twoDigYear;
        initRun();
    } // endae mainRun

    public void initRun() {
        for (int i = 0; i < 100 && isMagic() == true; i++) {
            System.out.println(i + " " + toString());
        }
    }

    private boolean isMagic() {
        boolean flag = false;
        if ((month * day) == twoDigYear) {
            flag = true;
        } // ednae IF
        return flag;
    } // endae isMagic

    public String toString() {
        String result = "NOT A MAGIC DATE!";
        if (isMagic() == true) {
            result = "MAGIC DATES!!";
        } // endae IF
        return result;
    } // endae toString();

    public static void main(String args[]) {
        Random r = new Random();
        int month = r.nextInt(12) + 1;
        int day = r.nextInt(31) + 1;
        if (month == 2) {
            day = r.nextInt(28) + 1;
        } // endae IF
        int twoDigYear = r.nextInt(99);

        System.out.printf("Month: %d\nDay: %d\nYear: 20%d\n", month, day, twoDigYear);

        MagicDates.getInstance().mainRun(month, day, twoDigYear);
    }
}
