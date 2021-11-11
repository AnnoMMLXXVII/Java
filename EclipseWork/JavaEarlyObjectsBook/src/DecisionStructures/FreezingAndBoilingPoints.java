package DecisionStructures;

import java.util.Random;
import java.util.Scanner;

// List of freezing/boiling points for several substances in Fahrenheit
// 		Substance		Freezing		Boiling
// 		Ethyl Alcohol	-173			172
// 		Oxygen			-362			-306
// 		Water			32				212
// design class that stores a temp in the temerpature field and has the appropriate
// accessor and mutator methods for the field. 
// Should include the Following methods
// 		isEthylFreezing : boolean --> true : below freezing point | false : otherwise
// 		isEthyBoiling : boolean --> true : above boiling point | false : otherwise
// 		isOxygenFreezing : booean --> " .. .. ";
// 		isOxygenBoiling: boolean --> " .. .. ";
// 		is WaterFreezing : boolean --> " ... ";
// 		is WaterBoiling : boolean --> " ... ";

// Design a program that asks the user to enter a temperature and then display a list of substances that will freeze at that temp 
// and those that will boils at that temperature

// Design method : singleton

public class FreezingAndBoilingPoints {

    private static FreezingAndBoilingPoints fabp = null;

    public static FreezingAndBoilingPoints getInstance() {
        if (fabp == null) {
            fabp = new FreezingAndBoilingPoints();
        }
        return fabp;
    } // endae getInstance

    private int temperature;
    private int ethylF = -172, ethylB = 172;
    private String ethylStringF = "Ethyl is Freezing", ethylStringB = "Ethyl is Boiling";
    private int oxygenF = -362, oxygenB = -306;
    private String oxygenStringF = "Oxygen is Freezing", oxygenStringB = "Oxygen is Boiling";
    private int waterF = 32, waterB = 212;
    private String waterStringF = "Water is Freezing", waterStringB = "Water is Boiling";

    //	private boolean[] flags = {isEthylFreezing(), isEthylBoiling(), isOxygenFreezing(), isOxygenBoiling(), isWaterFreezing(), isWaterBoiling()};
    public void mainRun(int temperature) {
        this.temperature = temperature;
        checkingTemps();
    } // endae mainRun

    private void checkingTemps() {
        ethylTemps();
        oxygenTemps();
        waterTemps();

//		for (int i = 0; i < flags.length; i++) {
//			System.out.println(flags[i]);
//		}
    }

    private void ethylTemps() {
        if (isEthylFreezing() == true) {
            System.out.println(ethylStringF);
        } // endae IF
        if (isEthylBoiling() == true) {
            System.out.println(ethylStringB);
        } // endae IF
    } // endae ethylTemps

    private void oxygenTemps() {
        if (isOxygenFreezing() == true) {
            System.out.println(oxygenStringF);
        } // endae IF
        if (isOxygenBoiling() == true) {
            System.out.println(oxygenStringB);
        } // endae IF
    } // endae oxygenTemps

    private void waterTemps() {
        if (isWaterFreezing() == true) {
            System.out.println(waterStringF);
        } // endae IF
        if (isWaterBoiling() == true) {
            System.out.println(waterStringB);
        } // endae IF
    } // endaea waterTemps

    private boolean isEthylFreezing() {
        if (temperature < ethylF) {
            return true;
        } // endae IF
        return false;
    } // endae isEthylFreezing

    private boolean isEthylBoiling() {
        if (temperature > ethylB) {
            return true;
        } // endae IF
        return false;
    } // endae isEthylBoiling

    private boolean isOxygenFreezing() {
        if (temperature < oxygenF) {
            return true;
        } // endae IF
        return false;
    } // endae isOxygenFreezing

    private boolean isOxygenBoiling() {
        if (temperature > oxygenB) {
            return true;
        } // endae IF
        return false;
    } // endae isOxygenBoiling

    private boolean isWaterFreezing() {
        if (temperature < waterF) {
            return true;
        } // endae IF
        return false;
    } // endae isWaterFreezing

    private boolean isWaterBoiling() {
        if (temperature > waterB) {
            return true;
        } // endae IF
        return false;
    } // endae isWaterBoiling


    public static void main(String args[]) {
        Scanner z = new Scanner(System.in);
        Random r = new Random();
        int rand = r.nextInt(500) - 500;
        System.out.println(rand);
        FreezingAndBoilingPoints.getInstance().mainRun(rand);
        z.close();
    }


}
