package Main;


import model.InHouse;
import model.Product;
import model.Inventory;
import model.Outsourced;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import controller.MainController;
import javafx.scene.control.Alert;
import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;


public class Main extends Application {
    public static StringBuilder errorMessages = new StringBuilder();


    public static void main(String[] args) {
        Product product1 = new Product(++MainController.makeProductId, "Raspberry Pi", 13.59, 2, 1, 8);
        Product product2 = new Product(++MainController.makeProductId, "Freenove Ultimate Starter Kit", 44.95, 10, 1, 12);
        Product product3 = new Product(++MainController.makeProductId, "REXQualis Electronics Component Fun Kit", 15.99, 7, 1, 50);


        InHouse inHouse1 = new InHouse(++MainController.makePartId, "Power Supply Module", 5.99, 5, 1, 10, 111211);
        InHouse inHouse2 = new InHouse(++MainController.makePartId, "Jumper Wire", 2.99, 2, 1, 10, 111212);
        InHouse inHouse3 = new InHouse(++MainController.makePartId, "Active Buzzer", 7.25, 4, 1, 16, 111213);

        Outsourced outsourced1 = new Outsourced(++MainController.makePartId, "Precision Potentiometer", 0.95, 40, 5, 100, "ShenZhou Ltd.");
        Outsourced outsourced2 = new Outsourced(++MainController.makePartId, "IC 4N35", 6.95, 1, 1, 5, "ShenZhou Ltd.");
        Outsourced outsourced3 = new Outsourced(++MainController.makePartId, "IC 74HC595", 2.95, 4, 1, 15, "American Chips Co.");


        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);


        Inventory.addPart(inHouse1);
        Inventory.addPart(inHouse2);
        Inventory.addPart(inHouse3);

        Inventory.addPart(outsourced1);
        Inventory.addPart(outsourced2);
        Inventory.addPart(outsourced3);


        launch(args);
    }

    private void intializeData() {

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));

        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(new Scene(root));


        primaryStage.show();
    }


    public static boolean validate(TextField name, TextField stock, TextField price, TextField max, TextField min) {
        boolean valid = true;


        int invValue = 0;
        double priceValue = 0;

        int maxValue = 0;
        int minValue = 0;


        String intRegex = "^-?\\d+";
        String doubleRegex = "^-?\\d+(\\.\\d+)?";
        String wordRegex = "^\\w+(.*\\w+)*";


        errorMessages.delete(0, errorMessages.length());

        try {
            if (!name.getText().matches(wordRegex)) {
                errorMessages.append("Name: enter a valid name\n");
                valid = false;
            }


            if (!stock.getText().matches(intRegex)) {
                errorMessages.append("Inv: enter a valid integer\n");
                valid = false;
            } else {
                invValue = Integer.parseInt(stock.getText());
            }

            if (!price.getText().matches(doubleRegex)) {
                errorMessages.append("Price: enter a valid number\n");
                valid = false;
            } else {
                priceValue = Double.parseDouble(price.getText());
            }


            if (!max.getText().matches(intRegex)) {
                errorMessages.append("Max: enter a valid integer\n");
                valid = false;
            } else {
                maxValue = Integer.parseInt(max.getText());
            }

            if (!min.getText().matches(intRegex)) {
                errorMessages.append("Min: enter a valid integer\n");
                valid = false;
            } else {
                minValue = Integer.parseInt(min.getText());
            }


            if (maxValue < 0) {
                errorMessages.append("Max: must be greater than 0\n");
                valid = false;
            } else if (maxValue < minValue) {
                errorMessages.append("Max: must be greater than or equal to Min\n");
                valid = false;
            }


            if (minValue < 0) {
                errorMessages.append("Min: must be greater than 0\n");
                valid = false;
            } else if (minValue > maxValue) {
                errorMessages.append("Min: must be less than or equal to Max\n");
                valid = false;
            }


            if (invValue > maxValue || invValue < minValue) {
                errorMessages.append("Inv: must be between Max and Min\n");
                valid = false;
            } else if (invValue < 0) {
                errorMessages.append("Inv: must be greater than or equal to 0\n");
                valid = false;
            }

            if (priceValue < 0) {
                errorMessages.append("Price: must be greater than 0\n");
                valid = false;
            }


            System.out.println(errorMessages);
        } catch (Exception e) {
            e.printStackTrace();
            Alert errorDialog = new Alert(Alert.AlertType.ERROR, "An error has occurred.");

            errorDialog.setTitle("Error");
            errorDialog.showAndWait();
        }


        return valid;
    }


    public static boolean validateRadioButtonAction(TextField machineIdOrCompany, RadioButton inHouse, RadioButton outsourced) {
        boolean valid = true;
        String intRegex = "^-?\\d+";
        String wordRegex = "^\\w+(.*\\w+)*.?";
        if (inHouse.isSelected() && !machineIdOrCompany.getText().matches(intRegex)) {
            Main.errorMessages.append("Machine ID: enter a valid integer\n");
            valid = false;
        } else if (outsourced.isSelected() && !machineIdOrCompany.getText().matches(wordRegex)) {
            Main.errorMessages.append("Company Name: enter a valid string\n");
            valid = false;
        }
        System.out.println(Main.errorMessages);
        return valid;
    }
}


