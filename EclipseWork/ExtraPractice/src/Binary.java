import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Binary {
    public static void main(String args[]) throws NoSuchAlgorithmException {
        Random r = new Random();
        int rand = r.nextInt(9999) + 0;
        String bin = Integer.toBinaryString(rand);
        String hex = Integer.toHexString(rand);
        String oct = Integer.toOctalString(rand);
        String tens = Integer.toString(rand);
        byte hash = (byte) Integer.hashCode(rand);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] encoded = md.digest(tens.getBytes(StandardCharsets.UTF_8));
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < encoded.length; i++) {
            String hes = Integer.toHexString(0xff & encoded[i]);
            if (hes.length() == 1) {
                hexString.append('0');
                hexString.append(hes);
            }
        }

        System.out.println("Random Number: " + rand);
        System.out.println("Binary Number: " + bin);
        System.out.println("Hex Number:\t" + hex);
        System.out.println("Octal Number:\t" + oct);
        System.out.println("String Number:\t" + tens);
        System.out.println(hash);
        System.out.println(hexString);

    }
}
