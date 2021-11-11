public class Main {

    public static void main(String[] args) {
// write your code here

        Hamburger hamburger = new Hamburger("BASIC", "SAUSAGE", 3.56, "WHITE");
// double price = hamburger.itemizehamburger();
        hamburger.addhamburgeraddition1("tomato ", 0.27);
        hamburger.addhamburgeraddition2("letuce", 0.75);
        hamburger.addhamburgeraddition3("cheese", 1.12);

        System.out.println("total burger price is " + hamburger.itemizehamburger());
    }
}