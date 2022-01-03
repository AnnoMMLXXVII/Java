package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Interface controller class that extend Initializable
 * Generics Controller Interface that will contain the basic features on the UI
 * Cancel, Remove, Add, Update
 *
 * @param <T> Generic Object
 */
public interface Controller<T> extends Initializable {

    /**
     * Method from the Initializable Interface
     * @param url URL
     * @param rb ResourceBundle
     */
    void initialize(URL url, ResourceBundle rb);

    /**
     * Remove Action
     * @param event ActionEvent
     */
    @FXML
    void removeAction(ActionEvent event);

    /**
     * Add Action
     * @param event ActionEvent
     */
    @FXML
    void addAction(ActionEvent event);

    /**
     * Update Action
     * @param event ActionEvent
     */
    @FXML
    void updateAction(ActionEvent event);

    /**
     * Cancel Action
     * @param event ActionEvent
     */
    @FXML
    void cancelAction(ActionEvent event);

}
