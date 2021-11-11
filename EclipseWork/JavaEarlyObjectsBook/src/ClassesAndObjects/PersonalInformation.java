package ClassesAndObjects;

public class PersonalInformation<newInstance> {
    private String name, address;
    private int age;
    private long phoneNum;

    public static void main(String args[]) {
        newInstance ni = new newInstance();
        ni.pi1();
        System.out.println();
        ni.pi2();
        System.out.println();
        ni.pi3();
    } // ednae Main

    // Mutators
    public void setName(String name) {
        this.name = name;
    } // endae Mutator setName

    public void setAddress(String address) {
        this.address = address;
    } // endae Mutator setAddress

    public void setAge(int age) {
        this.age = age;
    } // endae Mutator setAge

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    } // endae mutator setPhoneNum

    // Accessors
    public String getName() {
        return name;
    } // endae Accessor getName

    public String getAddress() {
        return address;
    } // endae Accessor getAddress

    public int getAge() {
        return age;
    } // endae Accessor getAge

    public long getPhoneNum() {
        return phoneNum;
    } // endae Accessor getPhoneNum


    private static class newInstance {
        PersonalInformation<newInstance> pi1 = new PersonalInformation<newInstance>();
        PersonalInformation<newInstance> pi2 = new PersonalInformation<newInstance>();
        PersonalInformation<newInstance> pi3 = new PersonalInformation<newInstance>();


        public PersonalInformation<newInstance> pi1() {
            pi1.setName("Lucas Szydlowski");
            pi1.setAddress("417 Tremont St");
            pi1.setAge(22);
            pi1.setPhoneNum(1234567890);
            System.out.println(toString(pi1));
            return pi1;
        } // endae Mutator pi1

        public PersonalInformation<newInstance> pi2() {
            pi2.setName("Stephany Chinchilla");
            pi2.setAddress("18 Hollywood Boulevard");
            pi2.setAge(22);
            pi2.setPhoneNum(1231231230);
            System.out.println(toString(pi2));
            return pi2;
        } // endae Mutator pi2

        public PersonalInformation<newInstance> pi3() {
            pi3.setName("Yoshi Yukimura");
            pi3.setAddress("999 End of teh Road Road");
            pi3.setAge(30);
            pi3.setPhoneNum(1112223333);
            System.out.println(toString(pi3));
            return pi3;
        }

        public String toString(PersonalInformation<newInstance> pi) {
            String result = "Name:\t " + pi.getName() +
                    "\nAddress: " + pi.getAddress() +
                    "\nAge: \t " + pi.getAge() +
                    "\nNumber:\t " + pi.getPhoneNum();
            return result;

        } // endae Mutator toString

    } // endae Private CLASS
} // endae CLASS
