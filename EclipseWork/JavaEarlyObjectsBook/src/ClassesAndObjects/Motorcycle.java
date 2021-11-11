package ClassesAndObjects;


/*
 * Purpose of the class if to create an object Motorcycle
 * call Accelerate 5x to see if current speed of motorcycle increases
 * call brake 5x to see if current speed of car motorcycle decreases
 * Will display current speed of the car
 */
public class Motorcycle {

    private int yearModel;
    private String make;
    private int speed;

    // Constructor
    public Motorcycle() {

    } // endae CONSTRUCTOR

    // Mutators!!
    public void setYearModel(int yearModel) {
        this.yearModel = yearModel;
    } // endae Mutator getYearModel

    public void setMake(String make) {
        this.make = make;
    } // endae Mutator getMake

    public void setSpeed(int speed) {
        this.speed = speed;
    } // endae Mutator getSpeed

    public int accelerate() {
        setSpeed(speed + 5);
        return getSpeed();
    } // endae Mutator accelerate

    public int brake() {
        setSpeed(speed - 5);
        return getSpeed();
    } // endae Mutator brake

    // Accessors!!
    public int getYearModel() {
        return yearModel;
    } // endae Accessor getYearModel

    public String getMake() {
        return make;
    } // endae Accessor getMake

    public int getSpeed() {
        return speed;
    } // endae Accessor getSpeed

}
