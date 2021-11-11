import java.util.Random;

public class Exam3Part3 {
    private Random r = new Random();
    private int nulla, unus, duobus, tribus, quartus,
            quintus, sextus, septus, octus, nonus, decus;

    public Exam3Part3() {
        int[] arr = new int[1000];
        arr = init(arr);
        table(arr);
    } // endae CONSTRUCTOR

    private int[] init(int[] arr) {
        int rand = r.nextInt(11) + 0;
        for (int i = 0; i < arr.length; i++) {
            rand = r.nextInt(11) + 0;
            arr[i] = rand;
        } // endae FOR
        return arr;
    } // endae init method

    private int checkNum(int number, int[] arr) {
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number) {
                total++;
            } // endae IF
        } // endae FOR
        return total;
    } // ende checkSum method

    private void list(String desc, int total) {
        System.out.printf("%s :\t%d\n", desc, total);
    } // endae list method

    private void table(int[] arr) {
        System.out.println("LIST OF NUMBERS!\n");
        nulla = checkNum(0, arr);
        list("Zero", nulla);
        unus = checkNum(1, arr);
        list("One", unus);
        duobus = checkNum(2, arr);
        list("Two", duobus);
        tribus = checkNum(3, arr);
        list("Three", tribus);
        quartus = checkNum(4, arr);
        list("Four", quartus);
        quintus = checkNum(5, arr);
        list("Five", quintus);
        sextus = checkNum(6, arr);
        list("Six", sextus);
        septus = checkNum(7, arr);
        list("Seven", septus);
        octus = checkNum(8, arr);
        list("Eight", octus);
        nonus = checkNum(9, arr);
        list("Nine", nonus);
        decus = checkNum(10, arr);
        list("Ten", decus);
    } // endae table method

    public static void main(String args[]) {
        new Exam3Part3();
    } // endae main method
} // endae CLASS
