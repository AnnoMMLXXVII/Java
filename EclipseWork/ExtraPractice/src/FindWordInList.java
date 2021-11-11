import java.util.Scanner;

public class FindWordInList {

    private String word;
    private int pos;
    private Scanner z = new Scanner(System.in);
    private String[] dictList = new String[]
            {"the", "quick", "BROWN", "FoX", "JumP", "OveR", "the", "lazy", "DOG"};

    public static void main(String[] args) {
        new FindWordInList();
    }


    public FindWordInList() {
        initRun();
    }

    private void initRun() {
        printArr();
        prompt();
        System.out.printf("\n%s Found @ %d\n", getFoundWord(), getPos());
    }

    private void prompt() {
        System.out.print("\nEnter word to find: ");
        word = z.next();
        findWord(word, dictList);
    }

    private void findWord(String word, String... dictList) {
        word.trim();
        for (int i = 0; i < dictList.length; i++) {
            if (dictList[i].trim().equalsIgnoreCase(word)) {
                setFoundWord(dictList[i]);
                setPos(i);
            }
        }
    }

    private void setPos(int pos) {
        this.pos = pos;
    }

    private int getPos() {
        return pos;
    }

    private void setFoundWord(String word) {
        this.word = word;
    }

    private String getFoundWord() {
        return word;
    }


    private void printArr() {
        System.out.println("Dictionary List");
        for (int i = 0; i < dictList.length; i++) {
            System.out.printf("%s , ", dictList[i]);
        }
    }
}
