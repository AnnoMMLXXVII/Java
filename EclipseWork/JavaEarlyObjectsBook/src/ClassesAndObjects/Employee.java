package ClassesAndObjects;

public class Employee {


    private String name, department, position;
    private int idNum;

    public static void main(String[] args) {
        String header = "Name\t\t\tID Number\tDepartment\tPositionn";

        System.out.println(header);
        ExampleEmployee1 e1 = new ExampleEmployee1();
        ExampleEmployee2 e2 = new ExampleEmployee2();
        ExampleEmployee3 e3 = new ExampleEmployee3();
        System.out.printf("%s\n%s\n%s", e1.toString(), e2.toString(), e3.toString());


    }

    public void setName(String name) {
        this.name = name;
    } // endae Mutator setName

    public String getName() {
        return name;
    } // endae Accessor getName

    public void setID(int idNum) {
        this.idNum = idNum;
    } // endae Mutator setID

    public int getID() {
        return idNum;
    } // endae Accessor getID

    public void setDepart(String department) {
        this.department = department;
    } // endae Mutator setDepart

    public String getDepart() {
        return department;
    } // endae Accessor getDepart

    public void setPosition(String position) {
        this.position = position;
    } // endae Mutator setPosition

    public String getPosition() {
        return position;
    } // endae Accessor getPosition

    public String toString() {
        String person = name + "\t\t" + idNum + "\t\t" + department + "\t\t" + position;
        return person;
    }


}
