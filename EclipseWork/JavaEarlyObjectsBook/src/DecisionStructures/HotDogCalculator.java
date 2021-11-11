package DecisionStructures;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.JFrame;
//Hot dog packs come in 10s
// and Hot dog buns comes in 8s
// Calculate number of packages of hot dogs and the number of packages of hot dog buns needed for a cookout
// while minimizzing the amoutn of lefotovers
// program should ask for number of people attending
// and ask for number of hot dogs each person will be given
// program should display the following
// Min # of packages of hot dogs required
// Min # of packagees of buns required
// number of hot dogs that will remain for leftovers
// number of buns that will remain for leftovers

public class HotDogCalculator {

    private static HotDogCalculator hdc = null;

    // singleton design
    public static HotDogCalculator getInstance() {
        if (hdc == null) {
            hdc = new HotDogCalculator();
        } // endae IF
        return hdc;
    } // endae getInstance

    // DATA FIELDS or Instance Variables
    // Are private
    // Global
    private int hotDogsPerPack = 10; // should be a whole integer
    private int packsOfHotDog = 0; // should be a whole integer --> 10 in a pack
    private int leftOverHotDog = 0; // should be a whole integer; --> should be less than 10
    private int bunsPerPack = 8; // should be a whole integer
    private int packsOfBun = 0; // should be a whole intenger --> 8 in a pack
    private int leftOverBun = 0; // should be a whole integer --> should be less than 8


    private int numberOfPeople;
    private int numberOfHotDogsNeeded;

    // method to intialize user's inputs
    public void init(int numberOfPeople, int numberOfHotDogsNeeded) {
        this.numberOfPeople = numberOfPeople;
        this.numberOfHotDogsNeeded = numberOfHotDogsNeeded;
        mainRun(); // calls mainRun method
    } // endae mutator init method


//	private int getMod() {
//		return mod;
//	} // endae

    private void mainRun() {
        calcNumberOfHotDogPacksNeeded(getNumberOfHotDogsNeeded());
        calcNumberOfBunPacksNeeded(getNumberOfHotDogsNeeded());

        calcLeftOverHotDog();
        calcLeftOverBun();

        prints();

        GUIPART gp = new GUIPART();
        gp.setBackground(Color.RED);
    } // endae mainRun()

    private void prints() {
        System.out.printf("People Attending: \t%d\n", getNumberOfPeople());
        System.out.printf("Hot Dogs Needed: \t%d\n", getNumberOfHotDogsNeeded());
        System.out.printf("Hot Dog Packs: \t\t%d\n", getHotDogPack());
        System.out.printf("Bun Packs: \t\t%d\n", getBunPack());
        System.out.printf("LeftOver HotDogs:\t%d\n", getLeftOverHotDog());
        System.out.printf("LeftOver Buns:\t\t%d\n", getLeftOverBun());
        System.out.printf("Price for Hot Dogs: \t$%.2f\n", getCalcTotalHotDogCost());
        System.out.printf("Price for Buns: \t$%.2f\n", getCalcTotalBunCost());
        System.out.printf("Total Price: \t\t$%.2f\n", getCalcTotalHotDogCost() + getCalcTotalBunCost());

    }


    private int getNumberOfHotDogsNeeded() {
        return numberOfHotDogsNeeded;
    } // endae Accessor getNumberOfHotDogsPerPerson

    private int getNumberOfPeople() {
        return numberOfPeople;
    } // endae Accessor getNumberOfPeople


    private void setHotDogPack(double packsOfHotDog) {
        this.packsOfHotDog = (int) packsOfHotDog;
    } // endae mutator seteHotDogPack

    private int getHotDogPack() {
        return (int) Math.ceil(packsOfHotDog); // returns the integer version of div
    } // endae Accessor getHotDogPack

    //
    private void setBunPack(double packsOfBun) {
        this.packsOfBun = (int) packsOfBun;
    } // endae mutator setBunPack

    private int getBunPack() {
        return (int) Math.ceil(packsOfBun);
    } // endea Accessor getPacksOfBun()

    private int getLeftOverHotDog() {
        return leftOverHotDog;
    } // endae Accessor getLeftOverHotDog

    private int getLeftOverBun() {
        return leftOverBun;
    }

    // method to calcualte the number of hotdog packs needed
    private void calcNumberOfHotDogPacksNeeded(int numberOfHotDogsNeeded) {
        // total number of hot dogs needed divided by hot dogs per pack (10) then ceil rounds it to the whole integer
        double dogPack = (double) numberOfHotDogsNeeded / (double) hotDogsPerPack; // stores result in packsOfHotDog
        setHotDogPack(Math.ceil(dogPack));

    } // endae Mutator calcNumberOfHotDogPacksNeeded

    // method to calculate the number of bun packs needed
    private void calcNumberOfBunPacksNeeded(int numberOfHotDogsNeeded) {
        // total number of hot dogs needed divided by buns per pack (8) then ceil rounds it to the whole integer
        double bunPack = (double) numberOfHotDogsNeeded / (double) bunsPerPack; // stores result in packsOfBun
        setBunPack(Math.ceil(bunPack));
    } // endae Mutator calcNumberOfBunPacksNeeded

    // method to calcualte the left over hot dogs
    private void calcLeftOverHotDog() {
        // rounded ceiling number of hot dogs * 10 (dogs perpack) then subtracted by the non-rounded ceiling number of hot dogs needed
        leftOverHotDog = (getHotDogPack() * hotDogsPerPack) - getNumberOfHotDogsNeeded(); // stores result in leftOverHotDog
    } // ednae calcLeftOverHotDog

    // method to calculate the left over buns
    private void calcLeftOverBun() {
        // rounded ceiling number of buns * 8 (buns perpack) then subtracted by the non-rounded ceiling number of hot dogs needed
        leftOverBun = (getBunPack() * bunsPerPack) - getNumberOfHotDogsNeeded(); // stores result in leftOverBun
    } // endae calcLeftOverBun


    // additonal code --> find the price total price for all supplies
    // hot dog buns cost
    private final double tenPackHotDogUnitPrice = 7.99;
    private final double eightPackBunUnitPrice = 5.99;

    // method to calcaulte the total cost of just hot dogs
    private double getCalcTotalHotDogCost() {
        return getHotDogPack() * tenPackHotDogUnitPrice; // packs of hot dogs times unit price and result is returned
    } // endae getCaclTotalHotDogCost

    // method to calcualte the total cost of just buns
    private double getCalcTotalBunCost() {
        return getBunPack() * eightPackBunUnitPrice; // packs of buns times unit price and result is returned
    } // endae getCalcTotalBunBost


    /////////// PRIVATE CLASSS /////////////////////
    @SuppressWarnings("unused")
    private class GUIPART extends JFrame {

        private static final long serialVersionUID = 1L;
        private int x = 480, y = 340;

        public GUIPART() {
            super("Total Price");
            setPreferredSize(new Dimension(x, y));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } // endae CONSTRUCTOR
    } // endae PRIVATE CLASS GUIPART

//////////////END OF PRIVATE GUIPART CLASS //////////////////////


    public static void main(String[] args) {
        Random r = new Random();
        int numberOfPeople = r.nextInt(99999);
        int numberOfHotDogsPerPerson = r.nextInt(4) + 1;
        HotDogCalculator.getInstance().init(numberOfPeople, (numberOfPeople * numberOfHotDogsPerPerson));
        // ClassName.methodName().methodName(param1, param2);
    } // endae MAIN

} // endae CLASS









