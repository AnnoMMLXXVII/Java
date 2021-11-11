package com.question.questions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Testfogg {

    public static void main(String[] args) {
        String question, questionType, userAnswer, fileName, userFile;
        int amount, totalPoints = 0, pointsEarned;
        Scanner kb = new Scanner(System.in);
        PrintWriter outfile = null;
        System.out.println("Do you wish to make or take a quiz? (Make or Take) ");
        String answer = kb.nextLine();
        if (answer.equals("Make")) {
            System.out.println("Type in a file name to save it to: ");
            fileName = kb.nextLine();
            try {
                outfile = new PrintWriter(new File(fileName + ".txt"));
            } catch (Exception e) {
                System.out.println("Error!");
                System.exit(0);
            }
            System.out.println("How many questions do you want to create? ");
            amount = kb.nextInt();
            kb.nextLine();
            Question[] category = new Question[amount];
//			outfile.println(amount);
            for (int i = 0; i < amount; i++) {
                System.out.println("What kind of question are you asking? ");
                System.out.println("Must be True/False, Multiple Choice, or Fill in the Blank. ");
                questionType = kb.next();
                kb.nextLine();
                System.out.println("What is your question? ");
                question = kb.nextLine();
                System.out.println("What is the correct answer? ");
                userAnswer = kb.nextLine();
                System.out.println("How many points can the user earn? ");
                pointsEarned = kb.nextInt();
                kb.nextLine();
                if (questionType.equals("T")) {
//					new TFQuestion(question, pointsEarned, category);
                } else if (questionType.equals("M")) {
                    new MCQuestion(question, pointsEarned, category);
                } else {
//					new FBQuestion(question, pointsEarned, category);
                }
                totalPoints += pointsEarned;
                System.out.println("The total points are: " + totalPoints);
                outfile.println(
                        questionType + "-" + question + "-" + userAnswer + "-" + pointsEarned + "-" + totalPoints);
                System.out.println("File saved!");
            }
            outfile.flush();
            outfile.close();
            kb.close();
        }
        if (answer.equals("Take")) {
            String userInput = "";
            System.out.println("What is the name of your file? ");
            try (Scanner input = new Scanner(System.in)) {
                userFile = input.nextLine();
                try (Scanner z = new Scanner(new FileReader(new File(userFile + ".txt")))) {
                    int sumPointsEarned = 0;
                    String line = "";
                    String[] lineArray = null;
                    while (z.hasNextLine()) {
                        line = z.nextLine();
                        lineArray = line.split("-");
                        System.out.println(lineArray[0] + " : " + lineArray[1]);
                        userInput = input.nextLine();
                        if (!userInput.isEmpty() && userInput.equalsIgnoreCase(lineArray[2])) {
                            sumPointsEarned = sumPointsEarned + Integer.parseInt(lineArray[3]);
                        }
                    }
                    System.out.printf("RESULTS\nScore : %d out of %d", sumPointsEarned, Integer.parseInt(lineArray[4]));
                } catch (FileNotFoundException e) {
                    System.out.println("Error!");
                    e.printStackTrace();
                }
            }
        }
    }
}
