package JavaFundamentals;

public class PersonalInformation {
    public static void main(String args[]) {
        String name = "Stephany Elizabeth Chinchilla";
        String address = "1234 Chinchilla Road";
        String city = "Attleboro";
        String state = "MA";
        int ZIP = 27099;
        int areaCode = 508, midNum = 203, lastFour = 3210;
        String phoneNum = areaCode + "-" + midNum + "-" + lastFour;
        String major = "Nurse Practitioner";
        System.out.printf("Name: %s\nAddress: %s, %s, %s %d\nPhone Number: %s\nCollege Major: %s", name, address, city,
                state, ZIP, phoneNum, major);
    } // endae MAIN
} // endae CLASS
