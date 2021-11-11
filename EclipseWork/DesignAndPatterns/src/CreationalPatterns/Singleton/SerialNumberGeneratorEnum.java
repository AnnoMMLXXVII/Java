package CreationalPatterns.Singleton;

public enum SerialNumberGeneratorEnum {
    INSTANCE,

    // Creating Multiple Singleton (Multi-ton)
    ENGINE, VEHICLE;

    private int count;

    public synchronized int getNextSerial() {
        return count++;
    } // endae getNextSerial
} // endae enumeration CLASS 
