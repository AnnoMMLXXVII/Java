package DecisionStructures;

import java.util.Random;

public class TesterCode {
    public static void main(String[] args) {

        long sec = 61; // initalizes long sec variable
        System.out.println("When seconds are 61s\n" + getdurationstring(sec)); // prints for condition when seconds are over 60;
        // prints the method with one parameter
        System.out.println("When minutes are 61m\n" + getdurationstring(61, 0)); // prints for when minutes are over 61
        // prints the method with two parameters

        Random r = new Random(); // declares and initalizes Random object that's stored in the variable 'r'
        long min = r.nextInt(100) + 0; // variable min that holds generated values from 100 - 0;
        sec = r.nextInt(60) + 0; // reuses variable sec that holds generarted values from 60 = 0;
        System.out.println("When Mins: " + min + " and Seconds: " + sec + "\n" + getdurationstring(min, sec)); // prints for whem minutes and seconds are a random number
        // prints the method with two parameters
    } // endae MAIN

    public static String getdurationstring(long miniutes, long seconds) {
        // initializes the result variable for later use
        String result = null; // initalizes result to null
        // if statement for error message
        if ((miniutes < 0) || (seconds < 0) || (seconds > 59)) {
            result = "INVALID VALUE"; // stores error message string into result;
        } // endae IF

        long hours = miniutes / 60; // div miuntes and 60 to find hours
        long remainingminiutes = miniutes % 60; // mods minutes and 60 to find remainingminutes
        result =
                String.format("%02d", hours) + "h "  // reformats hours so it hold it's able to hold two digits even if there is a 0 present
                        + String.format("%02d", remainingminiutes) + "m " // reformats minutes so it hold it's able to hold two digits even if there is a 0 present
                        + String.format("%02d", seconds) + "s ";// reformats seconds so it hold it's able to hold two digits even if there is a 0 present

        return result; // returns the result based on the conditions above
    } // endae getdurationstring


    public static String getdurationstring(long seconds) {
        // initializing the needed local variables
        String result = null;
        long minutes = 0;
        long remainingseconds = 0;

        // tests if the seconds is greater than or equal to 0
        if (seconds < 0) {
            result = "INVALID VALUE"; // assigns string to result
        } // endae IF

        // tests if the seconds are greater then 60
        if (seconds > 60) {
            minutes = seconds / 60; // div seconds by 60 to get minutes
            remainingseconds = seconds % 60; // mods seconds by 60 to get remainder thus the remaining reconds
        } // endae IF

        // tests other condition if condition is between 0-60;
        else {
            remainingseconds = seconds; // just assigns seconds to remainingseconds with 0 minutes
        } // endae ELSE

        result = getdurationstring(minutes, remainingseconds);  // stores overloaded getduration to result with the two parameters
        // uses the overload method that was needed
        return result; // returns result based on the conditions above

    } // endae getdurationstring

} // endae CLASS

