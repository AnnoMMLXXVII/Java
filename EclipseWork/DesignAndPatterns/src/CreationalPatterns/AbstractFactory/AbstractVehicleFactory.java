package CreationalPatterns.AbstractFactory;

public abstract class AbstractVehicleFactory {
    // more info in the AbstractFactoryDescription textFile

    public abstract Body createBody();

    public abstract Chassis createChassis();

    public abstract Window createWindow();

}
