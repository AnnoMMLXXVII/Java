package Introduction;

public class Coupe extends AbstractCar {

    public Coupe(Engine engine) {
        this(engine, Vehicle.Color.UNPAINTED);
    }

    public Coupe(Engine engine, Color color) {
        super(engine, color);
        // TODO Auto-generated constructor stub
    }

}
