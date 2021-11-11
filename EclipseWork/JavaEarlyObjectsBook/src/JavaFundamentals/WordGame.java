package JavaFundamentals;

import java.util.Scanner;

public class WordGame {

    private static String name, city, college, profession, animal, petName;
    private static int age;

    public static void main(String args[]) {
        Scanner z = new Scanner(System.in);
        System.out.println("Enter a Name:");
        name = z.nextLine();
        System.out.println("Enter an Age: ");
        age = z.nextInt();
        z.nextLine();
        System.out.println("Enter name of a City: ");
        city = z.nextLine();
        System.out.println("Enter College Name: ");
        college = z.nextLine();
        System.out.println("Enter Profession");
        profession = z.nextLine();
        System.out.println("Enter a type of Animal");
        animal = z.nextLine();
        System.out.println("Enter a Pet's Name");
        petName = z.nextLine();

        System.out.printf("There once was a person named %s who lived in %s.\n"
                        + "At the age of %d, %s went to college at %s.\n"
                        + "%s graduated and went to work as a %s.\n"
                        + "Then, %s adopted a(n) %s named %s.\n"
                        + "They both lived happiley ever after!",
                name, city, age, name, college, name, profession, name, animal, petName);

        z.close();
    } // endae main
} // endae CLASS
