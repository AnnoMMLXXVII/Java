package Introduction;

public abstract class AbstractVehicle implements Vehicle {

    private Engine engine;
    private Vehicle.Color color;

    public AbstractVehicle(Engine engine) {
        this(engine, Vehicle.Color.UNPAINTED);
    }

    public AbstractVehicle(Engine engine, Vehicle.Color color) {
        this.engine = engine;
        this.color = color;
    }

    public Engine getEngine() {
        // TODO Auto-generated method stub
        return engine;
    } // endae getEngine

    public Color getColor() {
        // TODO Auto-generated method stub
        return color;
    } // endae getColor

    @Override
    public void paint(Color color) {
        this.color = color;
    } // endae paint

    public String toString() {
        return getClass().getSimpleName() + "(" + engine + ", " + color + ")";
    } // endae toString

} // endae CLASS
