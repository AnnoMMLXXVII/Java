public class CoinMachine {

    int toonieTracker = 0;
    int loonieTracker = 0;
    int quarterTracker = 0;
    int dimeTracker = 0;
    int nickelTracker = 0;
    private static int QUARTER = 0;
    private static int DIME = 0;
    private static int NICKEL = 0;

    public static void main(String... args) {
        int cash = Integer.parseInt(args[0]);
        int price = Integer.parseInt(args[1]);
        assert price >= 0 : "Price Value is Negative!";
        System.out.println("Input Cash : " + cash);

    }

    private static boolean isInputGreaterThanOrEqualTo(int input, int product) {
        if (input % product == 0) {
            assert (input == 0) : new Exception("Radius cannot be less than Zero!");
        }
        return false;
    }

}
