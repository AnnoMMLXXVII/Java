package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public interface Controller<T> extends Initializable {

    void initialize(URL url, ResourceBundle rb);

    @FXML
    void removeAction(ActionEvent event);

    @FXML
    void addAction(ActionEvent event);

    @FXML
    void updateAction(ActionEvent event);

    @FXML
    void cancelAction(ActionEvent event);

}
