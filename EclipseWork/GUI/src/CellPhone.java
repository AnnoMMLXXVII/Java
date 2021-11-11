import java.util.Random;

public class CellPhone {
    String numOfCellPhoneObj = " ";
    boolean checkInUse = false;
    static boolean inUse; // false == not being used, true == being used
    static Random r = new Random();

    private static boolean returnInUse() {
        int z = r.nextInt((2 + 0) + 0);
        System.out.println("Z Rand: " + z);
        if (z == 1) {
            inUse = false;
        } else {
            inUse = true;
        }
        return inUse;
    }

    public CellPhone(String init) {
        checkInUse = makeCall(init);
        if (checkInUse == false) {
            System.out.println("Call is being processed...");
        } else {
            System.out.println("Call is in session...");
        }
        finishCall();
        System.out.println("CALL ENDED\n");
    }

    private boolean makeCall(String init) {
        boolean callIsMade;
        if (inUse == false) { // NOT BEING USED thus call can be made
            callIsMade = true;
        } else { // BEING USED thus CALL CANNOT BE MADE
            callIsMade = false;
        }
        return callIsMade;
    }

    private void finishCall() {
        inUse = false;
    }

    public static void main(String args[]) {
        for (int i = 0; i < 10; i++) {
            inUse = returnInUse();
            new CellPhone("THIS");
        }
    }


}
