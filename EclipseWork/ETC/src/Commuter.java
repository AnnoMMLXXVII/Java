import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Commuter {
    int size = 0;
    private String[] omnes = new String[size];
    Scanner z;

    public Commuter() throws IOException {
        readFile();
        initArray(z);
        storeIntoArray(z);
//		printData();
    }

    public static void main(String args[]) throws IOException {
        new Commuter();
    }

    public void readFile() throws IOException {
        z = new Scanner(new FileReader("opal.txt"));
    }

    private void initArray(Scanner z) {
//		int size = 0;
        z.useDelimiter(" ");
        while (z.hasNextLine()) {
            if (z.next().equals(" ")) {
                continue;
            } else {
                size++;
            }

//			System.out.print(z.next());
            System.out.println(z.nextLine());
        }
//		return new String[size];	
    }

    private void storeIntoArray(Scanner z) {
        z.useDelimiter(" ");
        int i = 0;
//		System.out.println(omnes.length);
        while (z.hasNextLine() && i < omnes.length) {
            if (z.next().equals(" ")) {
                continue;
            } else {
                omnes[i] = z.nextLine();
                System.out.println(z.next());
            }
//			System.out.println( i + " " +omnes[i]);
        }
        i++;
//		}
    }


    public void printData() throws IOException {
        String[] headerLine = {"User", "Mon", "Tue", "Wedn", "Thur", "Fri", "Sat", "Sun", "Total"};

        // prints the output Headers
        for (int a = 0; a < headerLine.length; a++) {
            System.out.print(headerLine[a] + "\t");
        }
        System.out.println("\n");
        for (int i = 0; i < omnes.length; i++) {
            System.out.print(i + " " + omnes[i] + "\t");
        }
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 5; j++) {
//				if( i == 0) {
//					
//				}
//				
//			}
//		}

    }
}
 