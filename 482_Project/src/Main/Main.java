package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import utils.Utils;

import java.net.URL;

public class Main extends Application {

    // Instance field or Data Fields
    private static Inventory AllInventory = new Inventory();

    /*
        @Overriden
        @param primaryStage
        using helper method -- setFirstDataSet()
        Shall load the Home Screen on Start
    */
    @Override
    public void start(Stage primaryStage) throws Exception {
        setFirstDataSet();
        URL resourceURL = getClass().getResource(Utils.VIEWS.MAINCONTROLLER.getValue());
        Parent root = FXMLLoader.load(resourceURL);
        primaryStage.setTitle("Inventory Manager");
        primaryStage.setScene(new Scene(root, 1000, 525));
        primaryStage.show();
    }

    /*
        Accessor Method that will return Inventory Object
    */
    public static Inventory getAllInventory() {
        return AllInventory;
    }

    /*
        Helper method that initializes all Sample/Test Data
        Data will NOT persist -- no Database
    */
    public void setFirstDataSet(){
        System.out.println("Initial Set Starting.");
        Product prod1 = new Product(4587, "lace", 23.56, 17, 6, 15);
        AllInventory.addProduct(prod1);
        Product prod2 = new Product(1265,"money",31.98, 32,7,26);
        AllInventory.addProduct(prod2);
        Product prod3 = new Product(2589,"table",21.96, 10,2,20);
        AllInventory.addProduct(prod3);
        Product prod4 = new Product(3999,"pair of knitting needles",45.14, 16,2,22);
        AllInventory.addProduct(prod4);
        Product prod5 = new Product(4588,"glass",12.06, 8,6,24);
        AllInventory.addProduct(prod5);

        Outsourced out1 = new Outsourced(1467,"Endurance Running",6.16, 7,4,6,"Systemax Inc");
        AllInventory.addPart(out1);
        Outsourced out2 = new Outsourced(1002,"Soccer",25.16, 6,5,6,"Target Corp");
        AllInventory.addPart(out2);
        Outsourced out3 = new Outsourced(225,"Polo",44.46, 28,9,25,"Tech Data Corporation");
        AllInventory.addPart(out3);
        Outsourced out4 = new Outsourced(30,"Horse Racing",31.69, 31,6,28,"TECO Energy Inc");
        AllInventory.addPart(out4);
        Outsourced out5 = new Outsourced(49,"Wiffleball",2.43, 2,2,3,"Primedia Inc");
        AllInventory.addPart(out5);

        InHouse inh = new InHouse(5556,"tiger",46.19, 21,5,21,12321321);
        AllInventory.addPart(inh);
        InHouse inh2 = new InHouse(6784,"bison",34.12, 16,6,18,879879);
        AllInventory.addPart(inh2);
        InHouse inh3 = new InHouse(7194,"gila monster",38.48, 9,3,9,43564);
        AllInventory.addPart(inh3);
        InHouse inh4 = new InHouse(8981,"bunny",22.41, 6,6,3,124234);
        AllInventory.addPart(inh4);
        InHouse inh5 = new InHouse(9391,"waterbuck",5.21, 24,7,24,54789);
        AllInventory.addPart(inh5);
     }

     /*
        Main method calling launch method
      */
    public static void main(String[] args) {
        launch(args);
    }
}
