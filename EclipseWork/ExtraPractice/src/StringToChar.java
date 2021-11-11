import java.util.Random;

public class StringToChar {
    // DATA FIELDS
    private static Random r = new Random(); // USING RANDOM CLASS
    private static int min; // DECLARING VARIABLE MIN
    private static int max; // DECLARING VARIABLE MAX

    public static void main(String args[]) {
        // UNCOMMENT FOR LOOP AND END BRACKET TO RUN THE CODE 5 OR ANY NUMBER OF TIMES
//		for (int i = 0; i< 5; i++) { 
//			autoRun();	
//		} // endae FOR


        run();

    } // endae MAIN


    private static void run() {
        String str = "ABCDEFGH";
        int id1 = findSpecificRange(str, 'C', 0, 5);
        int id2 = findSpecificRange(str, 'C', 4, str.length());
        System.out.println("String: " + str);
        if (id1 < 0) {
            System.out.println("Letter C NOT FOUND between 0 - 5");
        } // endae IF
        else {
            System.out.println("Letter C FOUND between 0 - 5");
        }
        System.out.println(id1);

        if (id2 < 0) {
            System.out.println("Letter C NOT FOUND between 4 - 7");
        } // endae IF
        else {
            System.out.println("Letter C FOUND between 0 - 7");
        }
        System.out.println(id2);
    }

    public static void autoRun() {
        String str; // DECLARING VARIABLE STR
        str = randomString(); // method to randomly choose a random string of text
        setRange(str.length()); // sets the ranges of minx and maxes
        min = getMin(); // Accessor method to get min
        max = getMax(); // Accessor method to get max
        char let = randomLetter(); // method that returns random letter to variable let
        System.out.printf("LETTER: \' %s \'\n", let); // prints the random letter
        int id1 = findSpecificRange(str, let, min, max); // using the predefined method to find letter with a set range
        System.out.printf("String: \"%s\"\n", str); // prints the string that is chosen to read through
        // IF statement to check if return is -1
        if (id1 < 0) {
            System.out.printf("'%s' between %d - %d NOT found: %d\n\n", let, min, max, id1); // prints the message if the return is -1, meaning it has NOT found it
        } // endae IF
        else { // else statement when id1 is greater than 0;
            System.out.printf("'%s' between %d - %d found @ index %d: \n\n", let, min, max, id1); // prints the message when the letter is found!
            System.out.println(">>>>>>>>FOUND!!<<<<<<<<<<\n"); // prints a more notable message for when the letter is found in the message
        } // endae ELSE
    } // endae run method

    // method to find the specific key and return the index
    // returns -1 if letter is not found within the range
    public static int findSpecificRange(String s, char key, int x, int y) {
        int flag = -1; // variable that is set to -1
        char arr[] = new char[s.length()]; // creates an array called arr
        arr = s.toCharArray(); // using the toCharArray method and storing it into the Char arr array
        // Check if max and min is less than the character length
        if (y > arr.length || x > arr.length) {
            System.out.println("X||Y > Length");
            flag = -1; // will keep the flag to -1  and return it thus error
        } // endae IF
        // Check if min and max are negative
        if (x < 0 || y < 0) {
            System.out.println("X||Y  < 0");
            flag = -1; // keeps the flag to -1 and returns it thus error
        } // endae IF
        // Check if min is less than max
        if (x > y) {
            System.out.println("Y > X");
            flag = -1; // keep the flag to -1 and returns it thus error
        } // endae IF
        // for loop to iterate throught he char arr
        else {
            // FOR loop to that iterates through series of characters within the set range
            for (int i = x; i < y; i++) {
                // IF statement to check if key character value is equal to the series of characters value
                if (key == arr[i]) {
                    flag = i; // Flag will be equivalent to the index in which the key and array char values match
//					return flag; // returns the newly updated Flag
                } // endae IF
            } // endae FOR
        } // endae ELSE
        return flag; // returns flag
    } // endae findSpecificRange

    // METHOD  TO CHOOSE A RANDMON UPPERCASE LETTER
    private static char randomLetter() {
        char randLet = ' '; // EMPTY INITIALIZED CHAR RANDLET
        String str4 = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        char[] letter;
        letter = str4.toCharArray(); // ASSIGNS ALL UPPER CASE OR LOWER CASE LETTERS TO THE CHAR LETTER ARRAY
        int j = r.nextInt((letter.length - 0) + 0) + 0; // RANDOM NUMBER THAT WILL RANGE FROM CHAR LETTER ARRAY (0-26)
        // LOOP TO CHOOSE A RANDOM LETTER
        for (int i = 0; i < letter.length; i++) {
            if (i == j) {
                randLet = letter[i]; // ASSIGNS LETTER TO THE RANDLET VARIABLE
            } // endae IF
        } // endae FOR
        return randLet;
    } // ednae randomLetter method

    // RANDOM STRING GENERATOR THAT WILL CHOOSE A RANDOM SET OF STRINGS TO ITERATE THROUGH
    private static String randomString() {
        String chosenStr = " ";
        try {
            // Sample series of string
            String str = "The Quick Brown Fox Jumped Over the Lazy Dog";
            String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String str2 = "abcdefghijaklmnopqrstuvwxyz";
            String str3 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
            String str4 = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
            String[] arrStringae = {str, str1, str2, str3, str4}; // ARRAY THAT WILL HOLD ALL LOCAL STRING VARIABLES
            int j = r.nextInt((arrStringae.length - 0) + 0) + 0; // CHOOSING RANDOM NUMBER FROM 0-4(INCLUSIVE)
            System.out.println("RANDO STRING NUMBER : " + j);
            // FOR LOOP THAT WILL CHOOSE THE STRING TO USE TO BE ITERATED THROUGH
            for (int i = 0; i < arrStringae.length; i++) {
                if (i == j) {
                    chosenStr = arrStringae[i];
                } // endae IF
            } // endae FOR
        } // enae TRY
        catch (IllegalArgumentException iae) {
            System.out.println("RANDOM STRING ERROR");
        } // ednae CATCH
        return chosenStr;
    } // endae randomString method

    public static void setRange(int StrLength) {
        try {
            int randMax = r.nextInt((StrLength - 0) + 1) + 0; // RANDOMLY CHOOSEING MAX
            int randMin = r.nextInt((randMax - 0) + 0) + 0; // RANDOMLY CHOOSING MIN
            // IF STATEMENT TO CHECK IF MIN IS LESS THAN THE MAX
            // IF MIN IS >= MAX --> will redo a bound
            if (randMin >= randMax || randMin == randMax) {
                randMin = r.nextInt((randMax - 0) + 0) + 0; // WILL RANDOMIZE THE MIN
            } // endae IF
            // CHECK IF MAX IS ZERO
            if (randMax <= 0) {
                // WILL RANDMZIE THE MAX AGAIN WITH THE WITH THE LOWER BOUND BEING AT LEAST 1
                randMax = r.nextInt((StrLength - 0) + 0) + 1;
            } else {
                setMin(randMin); // SETS THE NEWLY RANDOMIZED MIN
                setMax(randMax); // SETS THE NEWLY RANDOMIZED MAX
            } // endae ELSE
        } // endae TRY
        catch (IllegalArgumentException iae) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SET RANGE ERROR");
        } // endae CATCH
    } // endae setRange method

    private static void setMin(int incomingMin) {
        min = incomingMin;
    } // endae Mutator method setMin

    private static void setMax(int incomingMax) {
        max = incomingMax;
    } // endae Mutator method setMax

    private static int getMin() {
        return min;
    } // endae Accessor method getMin

    private static int getMax() {
        return max;
    } // endae Accessor method getMax


} // endae StringToChar class
