package JavaFundamentals;

public class StockCommission {
    private static double pctg = 100;
    private static double brokerFee = 2 / pctg;
    private static int stockShares = 1000;
    private static double pricePerStock = 25.50;

    public static void main(String args[]) {
        double totalPaidStockNullCommission = stockShares * pricePerStock;
        double commission = brokerFee * totalPaidStockNullCommission;
        double totalAmountPaid = commission + totalPaidStockNullCommission;
        System.out.printf("Stock:\t\t$%.2f\n"
                        + "Commission:\t$%.2f\n"
                        + "Total Amount:\t$%.2f",
                totalPaidStockNullCommission, commission, totalAmountPaid);
    } // endae main
} // endae CLASS
