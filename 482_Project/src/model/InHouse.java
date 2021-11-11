package model;
/*
 * Implemented from the UML Diagram of the requirements
 * This class is the subclass or child of the super class Part
 */

public class InHouse extends Part {
    // private instance field : machineId
    private int machineId;

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
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /*
        Accessor method that returns the machineId
    */
    public int getMachineId() {
        return machineId;
    }

    /*
        @param machineId
        Mutator method that updates the machineId value
    */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
