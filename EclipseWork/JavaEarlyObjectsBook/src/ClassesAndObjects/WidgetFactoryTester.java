package ClassesAndObjects;

import java.util.Scanner;

public class WidgetFactoryTester {

    public static void main(String[] args) {
        Scanner z = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Enter the amount of widgets: ");
            int widInput = z.nextInt();
            if (widInput <= 0) {
                flag = false;
                break;
            }
            WidgetFactory wf = new WidgetFactory(widInput);
            wf.answer();
        }
        z.close();
    } // ednae MAIN

} // endae CLASS
