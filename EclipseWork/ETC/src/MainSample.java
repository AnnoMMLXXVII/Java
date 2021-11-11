import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainSample {

    public static void main(String[] args) {
        MainSample mainObject = new MainSample();
        mainObject.run();
    }

    private void run() {
        createFile();

    }

    // Opens the file
    private static void createFile() {
        String fName = "p1-in.txt";
        Scanner in = null;
        try {
            in = new Scanner(new File(fName));
            readFile(in);
        } catch (FileNotFoundException e) {

        } finally {
            // keep here just in case we change where to close file

        }

    }

    /**
     * The readFile method uses the Scanner passed and a while loop to store every
     * integer value in the original file in an ArrayList which is then passed on to
     * the next method for calculation.
     */
    private static void readFile(Scanner in) {
        ArrayList<Integer> inList = new ArrayList<Integer>();
        // scan for integers in the file
        while (in.hasNextLine()) {
            // adds the integers in the file to an array
            String line = in.nextLine();
            String[] arr = line.split("\\s+");
            for (int i = 0; i < arr.length; i++) {
                inList.add(Integer.parseInt(arr[i].trim()));
            }
            // closes the file being scanned
        }
        kListUp(inList);
        in.close();
    }

    // **********************Problem*******************************
    // Arraylist is not adding he k values correctly
    // total runs up

    private static void kListUp(ArrayList<Integer> inList) {
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        ArrayList<Integer> countListUp = new ArrayList<Integer>();// Size of list
        // Initializes countListUp indexes to 0 to avoid errors
        for (int i = 0; i < inList.size(); i++) {
            countListUp.add(0);
        }
        int size = inList.size();
        int i = 0; // counter for index bounds
        int c = 0; // counter to keep track of where each run leaves off
        while (i < inList.size()) {
            // bumps i + 1 for comparison
//			tempList.add(0, inList.get(c));
            for (i = c + 1; i < inList.size() && inList.get(i) > inList.get(i - 1); i++, c++) {
                tempList.add(inList.get(c));
            }
            System.out.println(tempList + " Up");
            int k = tempList.size() - 1;
            if (k > 0) {
                countListUp.add(k, countListUp.get(k) + 1);
                c++;
                tempList.clear();

                // figure out out to read numbers in template
                // add that number to the counter list then empty the list out
            } // total runs down
            // need to create a kListUp/kListDown
            kListDown(inList, countListUp, size);
        }
    }

    //**********************Problem*******************************
    // Arraylist is not adding he k values correctly
    private static void kListDown(ArrayList<Integer> inList, ArrayList<Integer> kListUp, int size) {
        ArrayList<Integer> tempList = new ArrayList<Integer>();
        ArrayList<Integer> countListDown = new ArrayList<Integer>();// Size of list
        for (int i = 0; i < inList.size(); i++) {
            countListDown.add(0);
        }
        int i = 0;
        int c = 0;
        while (i < inList.size()) {
            i = c + 1;
            tempList.add(0, inList.get(c));
            // if(i < inList.size() && inList.get(i) < inList.get(i - 1)) {
            // i++;
            // c++;
            // tempList.add(inList.get(i));
            for (i = c + 1; i > inList.size() && inList.get(i) < inList.get(i - 1); ) {
                i++;
                c++;
                tempList.add(inList.get(c));
            }

            System.out.println(tempList + " Down");
            int k = tempList.size() - 1;
            if (k > 0) {
                countListDown.add(k, countListDown.get(k) + 1);
            }
            c++;
            tempList.clear();
            // figure out out to read numbers in template
            // add that number to the counter list then empty the list out

        }
        kListSum(kListUp, countListDown, size);
    }

    private static void kListSum(ArrayList<Integer> kListUp, ArrayList<Integer> kListDown, int size) {
        ArrayList<Integer> kListSum = new ArrayList<Integer>();
        int sum = 0;
        System.out.println(kListDown);
        System.out.println(kListUp);
        if (kListDown.size() > 0) {
            for (int i = 0; i < kListDown.size(); i++) {
                kListSum.add(i, kListDown.get(i));
            }
        }
        if (kListDown.size() > 0) {
            for (int i = 0; i < kListUp.size(); i++) {
                kListSum.add(i, kListUp.get(i));
            }
        }
        // finds sum and assigns it to the 0 instance in kListSum
        // int sumList= 0;
//      for(int i = 1; i < kListSum.size(); i++){ 
//          sum += kListSum.get(i);
//        }   
        System.out.println(kListSum);
        printFile(kListSum);

    }

    // print the runs
    private static void printFile(ArrayList<Integer> kListSum) {
        // to format of print
        PrintWriter outFile = null;
        try {
            outFile = new PrintWriter(new File("p1-runs.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong");
        }
        outFile.println("runs_total: " + kListSum.get(0));

        for (int i = 1; i < kListSum.size(); i++) {
            outFile.println("run_" + i + ": " + kListSum.get(i));
        }
    }
}