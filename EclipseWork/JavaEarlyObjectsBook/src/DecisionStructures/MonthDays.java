package DecisionStructures;

import java.util.Random;

import javax.swing.JOptionPane;

// Constructor with two arguments
// Integer --> for the months
// Integer --> for the year
// should have getNumberOfDays : number of days in the month specified
// method should identify if leap year
// 1. determine if year is divisible by 100
// if (divisible == true) {
// then leap year IFF (if and only if) divisible by 400
// 2. else {
// then lear year IFF divisible by 4;
// demoonstrate class if a program that tasks user to enter month 
// via user entering range from 1 - 12 and the year
// program should then display number of days in that month;


public class MonthDays {

    private int month, year;

    public MonthDays(int month, int year) {
        this.month = month;
        this.year = year;
        mainRun();
    } // endae MonthDays

    private void mainRun() {
        if (getNumberOfDays() <= (-1)) {
            JOptionPane.showMessageDialog(null, "WRONG ENTRY!", getMonth(), JOptionPane.ERROR_MESSAGE);
        } // endae IF

        else {
            JOptionPane.showMessageDialog(null, "Days in " + getMonth() + " : " + getNumberOfDays(), "Number of Days in " + getMonth(), JOptionPane.INFORMATION_MESSAGE);
        } // endae ELSE
    } // endae mainRun

    private boolean isLeapYear() {
        int dividingByHundred = (year / 100);
        int dividingByFour = (year / 4);
        int dividingByFourHundred = (year / 400);
        boolean leapYear = false;
        boolean divisibleByHundred = (dividingByHundred == 0) == true;
        boolean divisibleByFour = (dividingByFour == 0) == true;
        boolean divisibleByFourHundred = (dividingByFourHundred == 0) == true;
        if (divisibleByHundred) {
            if (divisibleByFourHundred) {
                leapYear = true; // sets leapYear to TRUE
                return leapYear; // returns TRUE
            } // endae nested IF
        } // endae IF
        else if (!divisibleByHundred) {
            if (divisibleByFour) {
                leapYear = true; // sets leapYear to TRUE
                return leapYear; // returns TRUE
            } // endae nested IF
        } // endae ELSE IF
        return leapYear;
    } // endae isLeapYear

    private String monthS;

    private void setMonth(String monthS) {
        this.monthS = monthS;
    } // endae setMonth

    private String getMonth() {
        return monthS;
    }

    private int getNumberOfDays() {
        int days = 0;
        if (month == 1) { // January
            days = 31;
            setMonth("January");
        } // endae IF
        else if (month == 2) { // Feburary
            days = 28;
            setMonth("February");
            if (isLeapYear() == true) {
                days = 29;
                return days;
            } // endae IF
        } // endae ELSE IF
        else if (month == 3) { // March
            days = 31;
            setMonth("March");
        } // endae ELSE IF
        else if (month == 4) { // April
            days = 30;
            setMonth("April");
        } // endae ELSE IF
        else if (month == 5) { // May
            days = 31;
            setMonth("May");
        } // endae ELSE IF
        else if (month == 6) { // June
            days = 30;
            setMonth("June");
        } // endae ELSE IF
        else if (month == 7) { // July
            days = 31;
            setMonth("July");
        } // endae ELSE IF
        else if (month == 8) { // August
            days = 31;
            setMonth("August");
        } // endae ELSE IF
        else if (month == 9) { // September
            days = 30;
            setMonth("Setpember");
        } // endae ELSE IF
        else if (month == 10) { // October
            days = 31;
            setMonth("October");
        } // endae ELSE IF
        else if (month == 11) { // November
            days = 30;
            setMonth("November");
        } // endae ELSE IF
        else if (month == 12) {
            days = 31;
            setMonth("Decemeber");
        } // endae ELSE IF
        else {
            days = -1;
            setMonth("NULL");
        } // endae ELSE
        return days;
    } // endae getNumberOfDays

    public static void main(String args[]) {
        Random r = new Random();
        int randMonth = r.nextInt(12) + 1;
        int randYear = r.nextInt(2019);

        System.out.println("Year : " + randYear);
        new MonthDays(randMonth, randYear);
    }

}
