package Introduction;

public abstract class AbstractVan extends AbstractVehicle {

    public AbstractVan(Engine engine) {
        this(engine, Vehicle.Color.UNPAINTED);
    }

    public AbstractVan(Engine engine, Color color) {
        super(engine, color);
        // TODO Auto-generated constructor stub
    }

}
