
public class wifi {

    private int signal;
    private int WIFI = 0, Wcount = 0, LTE4G = 1, Lcount = 0;
    private final double rate = .15;


    public static void main(String args[]) {
        wifi wax = new wifi();

        wax.calcCount();
        wax.printPrice();
    }

    private void calcCount() {
        int x = 0;
        while (x < 300) {
            signal = (int) (2 * Math.random());
//			System.out.print(signal);
            if (signal == WIFI) {
                Wcount++;
//				System.out.println(Wcount);
            } else if (signal == LTE4G) {
                Lcount++;
//				System.out.println(Lcount);
            }
            x++;
        } // endae WHILE
    } // endae calcCount

    public void printPrice() {
        double price = Lcount * (rate * 1.000);
        System.out.println("Seconds Free Wifi Used: " + Wcount);
        System.out.println("Seconds Free 4G LTE Used: " + Lcount);
        System.out.printf("Total Price: $%.2f", price);
    }


}
