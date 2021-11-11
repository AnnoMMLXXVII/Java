package Introduction;

public class Pickup extends AbstractVan {

    public Pickup(Engine engine) {
        this(engine, Vehicle.Color.UNPAINTED);
    }

    public Pickup(Engine engine, Color color) {
        super(engine, color);
        // TODO Auto-generated constructor stub
    }

}
