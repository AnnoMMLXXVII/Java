/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tester2;

/**
 *
 * @author Morga
 */
import java.util.Scanner;
public class GuessNumberDebug {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         displayWelcomeMessage();

        // create the Scanner object
        Scanner sc = new Scanner(System.in);

        String choice = "y";
        int guessNumber = 0;
        int counter = 0;
        while (choice.equalsIgnoreCase("y")) {
            // generate the random number and invite user to guess it
           int number = getRandomNumber();
            displayPleaseGuessMessage();

            // continue until the user guesses the number
            while (guessNumber != number) {
                // get a valid int from user
                guessNumber = getIntWithinRange(sc, "Enter number: ", 0, 101);
                
                // display result of guess to user               
               if (guessNumber == number) {    
                    displayCorrectGuessMessage(++counter);
                } else {
                    displayGuessAgainMessage(number, ++counter, guessNumber);
                }
             }

            // see if the user wants to continue
            choice = getChoiceString(sc, "Try again? (y/n): ", "y", "n");
            System.out.println();
        }
        System.out.println("Bye - Come back soon!");
        System.out.println();
    }

    private static void displayWelcomeMessage() {
        System.out.println("Welcome to the Guess the Number Game");
        System.out.println("++++++++++++++++++++++++++++++++++++");
        System.out.println();
    }
    
    private static int getRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }
    
    private static void displayPleaseGuessMessage() {
        System.out.println("I'm thinking of a number from 1 to 100.");
        System.out.println("Try to guess it.");
        System.out.println();
    }
    
    private static void displayCorrectGuessMessage(int counter) {
        System.out.println("You got it in " + counter + " tries.");
        if (counter <= 3) {
            System.out.println("Great work! You are a mathematical wizard.\n");
        } else if (counter > 3 && counter <= 7) {
            System.out.println("Not too bad! You've got some potential.\n");
        } else {
            System.out.println("What took you so long? Maybe you should take some lessons.\n");
        }
    }
    
    private static void displayGuessAgainMessage(int number, int counter, int guessNumber) {
        int difference = guessNumber - number;
        if (guessNumber > number) {
            if (difference > 10) {
                System.out.println("Way too high! Guess again.\n" + counter +
            " "  + "times have been attempted");            
            } else {
                System.out.println("Too high! Guess again.\n" + counter + " " +
                        "times have been attempted" );
            }
        } else {
            if (difference < -10) {
                System.out.println("Way to low! Guess again.\n" + counter + " "
                        +"times have been attempted");
            } else {
                System.out.println("Too low! Guess again.\n");
            }
        }
    }

    private static int getInt(Scanner sc, String prompt) {
        int i = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                isValid = true;
            } else {
                System.out.println("Error! Invalid integer value. Try again.");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return i;
    }

    private static int getIntWithinRange(Scanner sc, String prompt,
            int min, int max) {
        int i = 0;
        boolean isValid = false;
        while (!isValid) {
            i = getInt(sc, prompt);
            if (i <= min) {
                System.out.println("Error! Number must be greater than " + min);
            } else if (i >= max) {
                System.out.println("Error! Number must be less than " + max);
            } else {
                isValid = true;
            }
        }
        return i;
    }

    private static String getRequiredString(Scanner sc, String prompt) {
        String s = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            s = sc.nextLine();
            if (s.equals("")) {
                System.out.println("Error! This entry is required. Try again.");
            } else {
                isValid = true;
            }
        }
        return s;
    }

    private static String getChoiceString(Scanner sc, String prompt,
            String s1, String s2) {
        String s = "";
        boolean isValid = false;
        while (!isValid) {
            s = getRequiredString(sc, prompt);
            if (!s.equalsIgnoreCase(s1) && !s.equalsIgnoreCase(s2)) {
                System.out.println("Error! Entry must be '" + s1 + "' or '" + s2 + "'. Try again.");
            } else {
                isValid = true;
            }
        }
        return s;
    }
}
    
    

