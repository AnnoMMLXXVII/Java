package com.inventory.inventorymanagementsystem;

import com.inventory.inventorymanagementsystem.shared.CONSTANTS;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class InventoryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource(CONSTANTS.HOME_SCREEN_FXML_PATH));
        HomeScreenController homeScreenController = new HomeScreenController();
        fxmlLoader.setController(homeScreenController);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}