package JavaFundamentals;

public class StockTransactionProgram {
    private static double pctg = 100;
    private static int stocks = 1000;
    private static double brokerFee = 2 / pctg;

    public static void main(String[] args) {
        double priceAnte = 32.87;
        double joePaidA = priceAnte * stocks;
        double commissA = joePaidA * brokerFee;

        double pricePost = 33.92;
        double joeSoldStock = pricePost * stocks;
        double commissP = joeSoldStock * brokerFee;

        double stockProfit = joeSoldStock - joePaidA;
        double brokerProfit = commissP - commissA;

        System.out.printf("Stock Before:\t\t$%.2f\n"
                        + "StockAfter:\t\t$%.2f\n"
                        + "Stock Profit:\t\t$%.2f\n"
                        + "Broker Fee Ante:\t$%.2f\n"
                        + "Broker Free Post:\t$%.2f\n"
                        + "Broker Commission:\t$%.2f",
                joePaidA, joeSoldStock,
                stockProfit, commissA, commissP, brokerProfit);

    } // endae main
} // endae CLASS
