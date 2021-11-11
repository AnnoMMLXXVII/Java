
public class FindingRange {

    public static void main(String args[]) {
        run();
    }

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
            System.out.println("Letter C NOT FOUND between 4 - 8");
        } // endae IF
        else {
            System.out.println("Letter C FOUND between 0 - 8");
        }
        System.out.println(id2);
    }

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


}
