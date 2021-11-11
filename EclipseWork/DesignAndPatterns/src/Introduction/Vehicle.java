package Introduction;

public interface Vehicle {
    public enum Color {
        UNPAINTED, BLUE, BLACK, GREEN,
        WHITE, RED, SILVER, YELLOW
    }

    ; // endae Color enum

    public Engine getEngine();

    public Vehicle.Color getColor();

    public void paint(Vehicle.Color color);
}
