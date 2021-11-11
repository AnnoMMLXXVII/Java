package ClassesAndObjects;

import java.util.Scanner;

public class PetTester {

    private static Pet p = new Pet();

    //	private static Pet pet = new Pet();
    public static void main(String[] args) {
        Scanner z = new Scanner(System.in);
        System.out.print("Enter Pet Name:\nEnter Pet Type:\nEnter Pet Age:\n");
        String name = z.next();
        p.setName(name);
        String type = z.next();
        p.setType(type);
        int age = z.nextInt();
        p.setAge(age);
        info();
        z.close();
    }

    public static void info() {
        System.out.printf("Name: %s\nType: %s\nAge: %d", p.getName(),
                p.getType(), p.getAge());
    }


}
