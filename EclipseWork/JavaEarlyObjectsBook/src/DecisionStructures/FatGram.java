package DecisionStructures;

import java.util.Scanner;

import javax.swing.JOptionPane;

// design a clas with a method that stores the number of calories and fat grams in a food item
// class should have a method that returns the percentage of calories that come from fat. 
// One gram of fat has 9 calories: 1g fat = 9 calories or Calories from fat = fat grams * 9;
// percentage of calories from fat can be calculated as:
// Calories from fat ÷ total calories
// Demonsrate the class in a program that asks user to enter number of calories and the number of fat grams from food item.
// Program sohuld display percentage of calories that come from fat
// If calories < 30% of total calories,
// should dispaly messae indicating the food is low in fat;
// ELSE if calories from fat is > than total number of calories {
// user errror, and must display an error message indicating that the numbers are invalid;

// Personally, will desgin using singleton method traditional method;


public class FatGram {
    private static FatGram fgInstance = null;
    private int calories, fatFromFood, caloriesFromFat;
    private boolean flag = false; // indicator that arguments have been initialized

    public static FatGram getInstance() {
        if (fgInstance == null) {
            fgInstance = new FatGram();
        }
        return fgInstance;
    }

    public void run() {
        if (getFlag() == true) {
            calculate();
        } else {
            return;
        } // endae ELSE
    } // endae CONSTRUCTOR

    public void init(int calories, int fatFromFood) {
        if (isDivisable(calories, fatFromFood) == true) {
            this.calories = calories;
            this.fatFromFood = fatFromFood;
            setFlag(true);
        } // endae IF
        else {
            JOptionPane.showMessageDialog(null, "Numbers are not Compatible", "Number Error", JOptionPane.ERROR_MESSAGE);
        }
    } // endae Mutator init

    private void setFlag(boolean flag) {
        this.flag = flag;
    } // endae Mutator setFlag

    private boolean getFlag() {
        return flag;
    } // endae Accessor getFlag

    private boolean isDivisable(int calories, int fatFromFood) {
        if (calories >= fatFromFood) {
            return true;
        } // endae IF
        else {
            return false;
        } // endae ELSE
    }

    private void calculate() {
        caloriesFromFat = caloriesFromFat(getFatFromFood());
        System.out.printf("\nCalories From Fat: %d", caloriesFromFat);
        double returnedPercent = percentageOfCaloriesFromFat(caloriesFromFat, getTotalCalories());
        System.out.printf("\nPercentage of Calories FromFat: %.2f", returnedPercent);
        showMessage(returnedPercent);
    } // endae calculate;

    private int getFatFromFood() {
        return fatFromFood;
    } // enae Accessor getFatFromFood

    private int caloriesFromFat(int fatFromFood) {
        return fatFromFood * 9;
    } // endae caloriesFromFat

    private int getTotalCalories() {
        return calories;
    } // endae getTotalCalories

    private double percentageOfCaloriesFromFat(int caloriesFromFat, int totalCalories) {
        return ((double) caloriesFromFat / (double) totalCalories);
    } // enae percentageOfCaloriesFromFat

    private void showMessage(double returnedPercent) {
        if (returnedPercent < .3) {
            JOptionPane.showMessageDialog(null, "Food is LOW in Fat!", "Caloric/Fat", JOptionPane.INFORMATION_MESSAGE);
        } // endae IF
        else {
            JOptionPane.showMessageDialog(null, "Food is HIGH in Fat!", "Caloric/Fat", JOptionPane.INFORMATION_MESSAGE);
        } // endae ELSE IF
    } // enae showMessage

    public static void main(String args[]) {
        Scanner z = new Scanner(System.in);
        System.out.print("Enter Calories: ");
        int calories = z.nextInt();
        System.out.print("Enter Fats from food item: ");
        int fats = z.nextInt();
        System.out.printf("%d calories, %d fats", calories, fats);
        FatGram fg = FatGram.getInstance();
        fg.init(calories, fats);
        fg.run();
        z.close();

    }


}
