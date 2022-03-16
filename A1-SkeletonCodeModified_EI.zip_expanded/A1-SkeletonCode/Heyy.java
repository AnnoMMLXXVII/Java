
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Heyy {
    
    public static class Hello {

        private String userDetails;
        private Scanner sc = new Scanner(System.in);
        private String[][] arrayL = new String[2][4];
        private int[][] intarray = new int[2][4];

        public void getUserDetails() {
            System.out.println("{Enter LastName}; ");
            arrayL[0][0] = sc.nextLine();
            System.out.println("{Enter FirstName}; ");
            arrayL[1][1] = sc.nextLine();

            System.out.println("{Enter Age}; ");
            intarray[1][2] = sc.nextInt();
            sc.nextLine();

            saver();
            coma();
            display();
        }

        private void saver() {
            userDetails = ("[Last Name] : " + arrayL[0][0] +","+ "[First Name] : " + arrayL[1][1] +","+"[Age] : "+ intarray[1][2]);
        }

        private void coma() {
            System.out.println(userDetails);
            try {
                FileWriter writer = new FileWriter("C:\\Users\\User\\OneDrive\\Documents\\New folder\\file.txt.txt");
                writer.write(userDetails);
                writer.close();
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }

        private void display(){
            System.out.println("The Data You Input Is "+ userDetails);
        }
    }

    public static void main(String[] args) {

        Hello operation = new Hello();
        operation.getUserDetails();
    }
}