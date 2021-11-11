package ClassesAndObjects;

public class ExampleEmployee2 {
    private String person;

    public ExampleEmployee2() {
        Employee sm = new Employee();
        sm.setName("Mark Jones");
        sm.setID(39119);
        sm.setDepart("IT");
        sm.setPosition("Programmer");
        person = sm.getName() + "\t\t" + sm.getID() + "\t\t" + sm.getDepart() + "\t\t" + sm.getPosition();
    }

    public String toString() {
        return person;
    }
}
