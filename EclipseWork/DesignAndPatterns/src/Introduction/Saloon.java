package Introduction;

public class Saloon extends AbstractCar {

    public Saloon(Engine engine) {
        this(engine, Vehicle.Color.UNPAINTED);
    }

    public Saloon(Engine engine, Color color) {
        super(engine, color);
        // TODO Auto-generated constructor stub
    }

}
