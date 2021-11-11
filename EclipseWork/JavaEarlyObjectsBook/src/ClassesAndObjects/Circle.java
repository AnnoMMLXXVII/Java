package ClassesAndObjects;

import java.util.Scanner;

// demonstrate the Circle class by asking user for Circle's radius, 
// creating a circle object, and then 
// reporting the circle's area, diameter, circumference
public class Circle {
    private double radius = 0;
    private double PI = 3.14159;

    public Circle(double radius) {
        setRadius(radius);

    } // endea CONSTRUCTOR

    public void setRadius(double radius) {
        this.radius = radius;
    } // endae Mutator setRadius

    public double getRadius() {
        return radius;
    } // endae Accessor getRadius

    public double getArea() {
        double area = PI * Math.pow(radius, 2);
        return area;
    } // endae getArea

    public double getDiameter() {
        double diameter = radius * 2;
        return diameter;
    } // endae getDiameter

    public double getCircumference() {
        double circumference = 2 * radius * PI;
        return circumference;
    } // endae getCircumference

    public static void main(String args[]) {
        Scanner z = new Scanner(System.in);
        System.out.print("Enter radius: ");
        double radius = z.nextDouble();
        Circle c = new Circle(radius);
        System.out.printf(
                "Radius:\t\t%.5f\nArea:\t\t%.5f\nDiameter:\t%.5f\nCircumference:\t%.5f",
                radius, c.getArea(), c.getDiameter(), c.getCircumference());
        z.close();
    }

}
