package JavaFundamentals;

public class EnergyDrinkConsumption {
    private static final double pctg = 100;
    private static final int population = 15000;
    private static double pctgDrinkPerWeek = 18 / pctg;
    private static int popPerWeek = (int) Math.floor(population * pctgDrinkPerWeek);
    private static double popPerWeekpctg = 58 / pctg;
    private static double popPerWeekCitrus = popPerWeek * popPerWeekpctg;

    public static void main(String args[]) {
        int oneAutMorePerWeek = (int) Math.floor(popPerWeekCitrus);

        System.out.printf(
                "Of the %d, Approximately %d (18%%) purchase one or more drinks per week\n"
                        + "And Of those, %d (58%%) prefer citrus-flavored energy drinks.",
                population, popPerWeek, oneAutMorePerWeek);
    } // endae main
} // endae CLASS
