package CreationalPatterns.Singleton;

public class SerialNumberGeneratorTraditional {
    // static members

    private static SerialNumberGeneratorTraditional instance;


    // instance variables
    private int count;

    public synchronized static SerialNumberGeneratorTraditional getInstance() {
        if (instance == null) {
            instance = new SerialNumberGeneratorTraditional();
        } // endae IF
        return instance;
    } // endae getInstance()

    // CONSTRUCTOR
    private SerialNumberGeneratorTraditional() {
    }

    ;

    // instance methods
    public synchronized int getNextSerial() {
        return count++;
    } // endae getNextSerial

} // enae CLASS

