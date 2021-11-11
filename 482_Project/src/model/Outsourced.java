package model;

/*
 * Implemented from the UML Diagram of the requirements
 * This class is the subclass or child of the super class Part
 */

public class Outsourced extends Part{
    // private instance field : companyName
    private String companyName;

    /*
       @param id
       @param name
       @param price
       @param stock
       @param min
       @param max
       @param machineId
       Constructor that calls super constructor from super class (Part)
   */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /*
       @return companyName
   */
    public String getCompanyName() {
        return companyName;
    }

    /*
       @param companyName
   */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
