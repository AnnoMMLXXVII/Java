package DecisionStructures;

import java.util.Random;

public class BankCharges {
    Random r = new Random();
    private int checks, weeks, dollars, balance;
    private double serviceFee;


    public static BankCharges getInstance() {
        return new BankCharges();
    } // endae getInstance

    private BankCharges() {
        balance = randomBalance();
        weeks = randWeeks();
        System.out.printf("Initial Balance:\t$%.2f\n", (double) balance);
        double newBalance = fee(checksWritten(), balance);
        System.out.printf("Checks Written:\t\t%d\n", getChecks());
        System.out.printf("Fees Per Week:\t\t$%.2f\n", getServiceFee());
        System.out.printf("Fees Per Month:\t\t$%.2f\n", 4 * getServiceFee());
        System.out.printf("Fees for %d weeks:\t$%.2f\n", getWeeks(), getServiceFee() * (double) getWeeks());
        System.out.printf("Total Balance:\t\t$%.2f\n", (double) newBalance);
    } // endae Contructor

    private void setWeeks(int weeks) {
        this.weeks = weeks;
    } // endae setWeeks

    public int getWeeks() {
        return weeks;
    } // endae getWeeks

    private int randWeeks() {
        weeks = r.nextInt(52);
        setWeeks(weeks);
        return weeks;
    }  // endae randWeeks

    private void setChecks(int checks) {
        this.checks = checks;
    } // eendae setChecks

    public int getChecks() {
        return checks;
    } // endae Accessor getChecks

    private int checksWritten() {
        checks = r.nextInt(100); // generates random weeks 0 through 52;
        setChecks(checks);
        return checks;
    } // endae Accessor checksWritten

    private int randomBalance() {
        int randBalance = 0;
        dollars = r.nextInt(750) + 100; // generates random number for balance
        System.out.printf("OverUnder400:\t\t$%d\n", dollars);
        randBalance = overUnder(dollars); // overUnder method will check to see if balance is under 400 and add $15
        return randBalance; // returns the balance
    } // endae randomBalance

    private int overUnder(int balance) {
        if (balance < 400) {
            balance = balance + 15;
            return balance;
        } // endae IF
        else {
            return balance;
        }
    } // endae overUnder

    private double fee(int checks, int balance) {
        double newBalance = 0;
        if (checks < 20) {
            newBalance = balance + serviceFee(balance);
        } // endae IF
        else if (checks > 19 && checks < 40) {
            newBalance = balance + serviceFee(balance);
        } // endae ELSE IF
        else if (checks > 39 && checks < 60) {
            newBalance = balance + serviceFee(balance);
        } // endae ELSE IF
        else if (checks > 59) {
            newBalance = balance + serviceFee(balance);
        } // endae ELSE IF
        return newBalance;
    } // endae fee

    private double serviceFee(int balance) {
        double serviceFee = 0;
        if (getChecks() < 20) {
            serviceFee = (balance * .1);
            setServiceFee(serviceFee);
        } // ednae IF
        else if (getChecks() > 19 && getChecks() < 40) {
            serviceFee = (balance * .08);
            setServiceFee(serviceFee);
        } // endae ELSE IF
        else if (getChecks() > 39 && getChecks() < 60) {
            serviceFee = (balance * .06);
            setServiceFee(serviceFee);
        } // endae ELSE IF
        else if (getChecks() > 59) {
            serviceFee = (balance * .04);
            setServiceFee(serviceFee);
        } // endae ELSE IF
        return serviceFee;
    } // endae serviceFee

    private void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    } // endae setServiceFee

    public double getServiceFee() {
        return serviceFee;
    } // endae getServiceFee

    public static void main(String args[]) {
        for (int i = 0; i < 5; i++) {
            if (i > 0) {
                System.out.println();
            } // enae IF
            getInstance();
        } // endae FOR
    } // endae MainMethod
} // endae CLASSS
