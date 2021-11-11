import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private int sampleLength = 1000;
    private int[] randomNumber = new int[sampleLength];
    private Random r;
    DecimalFormat decimalFormat = new DecimalFormat("0.00");
    List<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        new Main();
    }

    // Product --> id, name, price, stock, min, max;
    void readFile() {
        File file = new File("src/input.txt");
        r = new Random();
        try (Scanner z = new Scanner(new FileReader(file))) {
            int id = 0;
            double price = r.nextInt(100) / 100.0;
            price = price + (r.nextInt(50) + 0);
            int min = r.nextInt(10) + 0;
            int max = r.nextInt(30) + 0;
            int stock = r.nextInt(max) + min;
//			String machId = generateString();
            while (z.hasNextLine()) {
                String name = z.nextLine();
                products.add(new Product(id, name, price, stock, min, max, ""));
                id++;
                price = r.nextInt(100) / 100.0;
                price = price + (r.nextInt(50));
                min = r.nextInt(10) + 0;
                max = r.nextInt(30) + 0;
                stock = r.nextInt(max) + min;
//				machId = generateString();
            }
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        }
//		for (Product p : products) {
        try (Scanner z = new Scanner(new FileReader(new File("src/output.txt")))) {
            int i = 0;
            while (z.hasNextLine()) {
//                String name = z.nextLine();
//                name.length() > 6
//                products.get(i).setMachineId(name);
//                i++;
            }
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        }
//		}

        for (Product p : products) {
            System.out.println(p.toStringWitMachineId());
        }
    }

    private String generateString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }


    Main() {
        readFile();
//		r = new Random();
//		fillArray(randomNumber);
//		for (int i = 0; i < 10; i++) {
//			HashPortion(100 * (i + 1));
//		}
    }

    private void fillArray(int[] randomNumber) {
        int rand = r.nextInt(1000) + 0;
        for (int i = 0; i < randomNumber.length; i++) {
            randomNumber[i] = rand;
//             System.out.printf("%d |\n",randomNumber[i]);
            rand = r.nextInt(1000) + 0;
        }

    }

    private void HashPortion(int z) {
        int size = z;
        System.out.println("\t\tSIZE = " + size);
        HashLinQuad linHashTable = new HashLinQuad(size);
        int linCollisionCount = 0;
        for (int i = 0; i < randomNumber.length; ++i) {
            linCollisionCount += linHashTable.addLin(randomNumber[i]);
        }

//          linHashTable.addLin(randomNumber[0]);
        System.out.println("Collision: LinHashTable  = " + linCollisionCount);
    }

}