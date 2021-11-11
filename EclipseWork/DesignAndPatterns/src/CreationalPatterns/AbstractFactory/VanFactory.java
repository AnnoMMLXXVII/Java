package CreationalPatterns.AbstractFactory;

public class VanFactory extends AbstractVehicleFactory {

    @Override
    public Body createBody() {
        return new VanBody();
    } // endae createBody

    @Override
    public Chassis createChassis() {
        return new VanChassis();
    } // endae createChassis

    @Override
    public Window createWindow() {
        return new VanWindow();
    } // endae createWindow

} // endae CLASS
