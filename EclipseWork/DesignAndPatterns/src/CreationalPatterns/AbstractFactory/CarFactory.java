package CreationalPatterns.AbstractFactory;

public class CarFactory extends AbstractVehicleFactory {

    @Override
    public Body createBody() {
        return new CarBody();
    } // endae createBody

    @Override
    public Chassis createChassis() {
        return new CarChassis();
    } // endae createChassis

    @Override
    public Window createWindow() {
        return new CarWindow();
    } // endae createWindows

}
