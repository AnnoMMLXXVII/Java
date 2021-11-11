package ClassesAndObjects;

/*
 * Widget factory simulation
 * 	- number of widgets that must be produced
 * 	- method that calculates how many days it will take tp produce the number of widgets
 *  - assume 10 widgets can be produced each hour
 *  	- (10*24 = 240 can be produce per day or 80 per 8-hour shift)
 *  	- (Plant operates on two 8-hour shifts per day : 160 widgets total);
 *  demoonstate calss by writing a separate program that creates an instance of the class
 *  the program should pass a number of widgets to the object
 *  and call the object's method that displays the number of days it will take to produce that many widgets
 */
public class WidgetFactory {
    private int shift = 8; // Hours in a shift
    private int wPh = 10; // widgets Per Hour
    private int widgets; // number of widgets will be decided based on parameter
    private double hours, minutes, seconds;

    public WidgetFactory(int widgets) {
        this.widgets = widgets;

    } // endae CONSTRUCTOR

    public int getWidgets() {
        return widgets;
    }

    private double widPerDayArithm() {

        int wPs = shift * wPh; // widgets Per Shift; should be 80;
        int wPd = wPs * 2; // widgets Per Day; should be 160; due to 2 -working
        // shifts
        double daysToMakeWidgets = (double) widgets / (double) wPd;
        System.out.println(daysToMakeWidgets + " days");
        hours = ((daysToMakeWidgets % .1)) * 16;
        System.out.println(hours + " hours");
        minutes = (hours % .1) * 60;
        System.out.println(minutes + " mins");
        seconds = (minutes % .1) * 60;
        System.out.println(seconds + " secs");
        // System.out.println(sec);

        return daysToMakeWidgets;
    }

    public void answer() {
        System.out.printf(
                "\n%.2f Day(s), %d Hours, %d Minutes, %d seconds" + "\n",
                (double) widPerDayArithm(), (int) hours, (int) minutes,
                (int) seconds);
    }

} // endae CLASS
