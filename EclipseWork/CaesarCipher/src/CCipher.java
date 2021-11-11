
public class CCipher {

    public CCipher() {
        setN(7);
        setS("ZALWOHUF");
        System.out.println(enCrypt(getS(), getN()));
        System.out.println(deCrypt(getS()));


    }


    public StringBuffer enCrypt(String text, int s) {

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) + s - 65) % 26 + 65);
                System.out.println(ch);
                result.append(ch);
            } // endae IF
            else {
                char ch = (char) (((int) text.charAt(i) + s - 97) % 26 + 97);
                result.append(ch);
            } // endae ELSE
        } // endae FOR
        return result;
    } // endae encrypt

    private int n;

    private void setN(int n) {
        this.n = n;
    }

    private int getN() {
        return n;
    }

    private String s;

    private void setS(String s) {
        this.s = s;
    }

    private String getS() {
        return s;
    }


    public StringBuffer deCrypt(String text) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) - getN() - 65) % 26 + 65);
                result.append(ch);
            } // endae IF
            else {
                char ch = (char) (((int) text.charAt(i) - getN() - 97) % 26 + 97);
                result.append(ch);
            } // endae ELSE
        } // endae FOR
        return result;

    } //

    public static void main(String args[]) {
        new CCipher();

    }


}
