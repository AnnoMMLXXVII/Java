package Introduction;

public abstract class AbstractCar extends AbstractVehicle {

    public AbstractCar(Engine engine) {
        this(engine, Vehicle.Color.UNPAINTED);
    } // endae CONSTRUCTOR

    public AbstractCar(Engine engine, Color color) {
        super(engine, color);
        // TODO Auto-generated constructor stub
    } // endae CONSTRUCTOR
} // endae CLASS
