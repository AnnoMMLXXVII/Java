package ClassesAndObjects;

public class MotorcycleTester {
    private static int speed = 50;

    public static void main(String args[]) {
        Motorcycle c = new Motorcycle();
        String n = "\n";
        c.setMake("Suzuki");
        c.setYearModel(2004);
        c.setSpeed(speed);
        System.out.printf("Speed Before Acc: %d" + n + n, c.getSpeed());

        c.accelerate();
        System.out.printf("Speed First Acc: %d" + n, c.getSpeed());
        c.accelerate();
        System.out.printf("Speed Second Acc: %d" + n, c.getSpeed());
        c.accelerate();
        System.out.printf("Speed Third Acc: %d" + n, c.getSpeed());
        c.accelerate();
        System.out.printf("Speed FourthAcc: %d" + n, c.getSpeed());
        c.accelerate();
        System.out.printf("Speed After Acc: %d" + n + n, c.getSpeed());


        System.out.printf("Speed Before Brake: %d" + n + n, c.getSpeed());
        c.brake();
        System.out.printf("Speed First Brake: %d" + n, c.getSpeed());
        c.brake();
        System.out.printf("Speed Second Brake: %d" + n, c.getSpeed());
        c.brake();
        System.out.printf("Speed Third Brake: %d" + n, c.getSpeed());
        c.brake();
        System.out.printf("Speed Fourth Brake: %d" + n, c.getSpeed());
        c.brake();
        System.out.printf("Speed After Brake: %d" + n, c.getSpeed());

    } // endae main

} // endae CLASS
