package ClassesAndObjects;

public class ExampleEmployee3 {
    private String person;

    public ExampleEmployee3() {
        Employee sm = new Employee();
        sm.setName("Joy Rogers");
        sm.setID(81774);
        sm.setDepart("Manufacturing");
        sm.setPosition("Engineer");
        person = sm.getName() + "\t\t" + sm.getID() + "\t\t" + sm.getDepart() + "\t" + sm.getPosition();

    }

    public String toString() {
        return person;
    }
}
