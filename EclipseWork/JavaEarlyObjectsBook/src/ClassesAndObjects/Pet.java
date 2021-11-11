package ClassesAndObjects;

public class Pet {
    String name;
    String type;
    int age;
    Pet[] pets;

    public Pet() {
        pets = new Pet[10];
    }

    public void setName(String name) {
        this.name = name;
    } // endae Mutator setName

    public void setType(String type) {
        this.type = type;
    } // endae Mutator setType

    public void setAge(int age) {
        this.age = age;
    } // endae Mutator setAge

    public String getName() {
        return name;
    } // endae Accessor getName

    public String getType() {
        return type;
    } // endae Accessor getType

    public int getAge() {
        return age;
    } // endae Accessor getAge

}
