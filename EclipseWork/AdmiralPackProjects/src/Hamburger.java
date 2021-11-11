public class Hamburger {

    private String name;
    private String meat;
    private double price;
    private String breadrolltype;

    private String addition1name;
    private double addition1price;

    private String addition2name;
    private double addition2price;

    private String addition3name;
    private double addition3price;

    private String addition4name;
    private double addition4price;

    private double hamburgerprice;

    // public void getpriceofburger(String name , double price){
    // this.addition1name = addition1name;
    // this.addition1price = price;
    // // return addition1price;
    // }
    public Hamburger(String name, String meat, double price, String breadrolltype) {
        this.name = name;
        this.meat = meat;
        this.price = price;
        this.breadrolltype = breadrolltype;
    }

    public void addhamburgeraddition1(String name, double price) {
        addition1name = name;
        addition1price = price;
        // return this.addition1price;
    }

    public void addhamburgeraddition2(String name, double price) {
        addition2name = name;
        addition2price = price;
        // return this.addition2price;
    }

    public void addhamburgeraddition3(String name, double price) {
        addition3name = name;
        addition3price = price;
        // return this.addition3price;

    }

    public void addhamburgeraddition4(String name, double price) {
        addition4name = name;
        addition4price = price;
    }

    public double itemizehamburger() {
        hamburgerprice = price;
        System.out.println(this.name + " hamburger on a " + this.breadrolltype + " roll with " + this.meat + " , The price is " + this.price);

        if (addition1name != null) {
            hamburgerprice = hamburgerprice + addition1price;
            System.out.println("Added " + addition1name + " for an extra " + this.addition1price);

        }
        if (addition2name != null) {
            hamburgerprice = hamburgerprice + addition2price;
            System.out.println("Added " + this.addition2name + " for an extra " + this.addition2price);

        }
        if (addition3name != null) {
            hamburgerprice = hamburgerprice + addition3price;
            System.out.println("Added " + this.addition3name + " for an extra " + this.addition3price);

        }
        if (addition4name != null) {
            hamburgerprice = hamburgerprice + addition4price;
            System.out.println("Added " + this.addition4name + " for an extra " + this.addition4price);

        }

        System.out.println(hamburgerprice);

        return hamburgerprice;
    }
}

// JAVA OOPS MASTER CHALLENGE
// MAIN CLASS

