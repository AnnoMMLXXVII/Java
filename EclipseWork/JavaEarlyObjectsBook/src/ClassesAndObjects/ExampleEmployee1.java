package ClassesAndObjects;

public class ExampleEmployee1 {
    private String person;

    public ExampleEmployee1() {
        Employee sm = new Employee();
        sm.setName("Susan Meyer");
        sm.setID(47899);
        sm.setDepart("Accounting");
        sm.setPosition("Vice President");
        person = sm.getName() + "\t\t" + sm.getID() + "\t\t" + sm.getDepart() + "\t" + sm.getPosition();
    }

    public String toString() {
        return person;
    }

}
