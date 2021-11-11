
public class Sample {
    public static void main(String[] args) {
        int prime1 = 5;
        int prime2 = 14;
        int incomingText = 2;
        System.out.println("incoming Text Code: " + incomingText);
        int e = (int) Math.pow(incomingText, prime1);
        System.out.println(e);
        int outputText = e % prime2;
        System.out.println(outputText);

        int output = cipher(prime1, prime2, incomingText);
        System.out.println(output);
        int output2 = cipher(11, 14, output);
        if (incomingText == output2) {
            System.out.println("CIPHERED!");
        } else {
            System.out.println("ERROR!");
        }


    }


    private static int cipher(int prime1, int prime2, int incomingText) {
        return ((int) Math.pow(incomingText, prime1)) % prime2;
    }

}
