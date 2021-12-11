package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logs.ActivityLogger;
import logs.ApplicationLogger;
import logs.Logs;
import shared.JDBC;

import java.time.ZoneId;

import static shared.Common.*;
import static shared.Constants.FXMLVIEW;
import static shared.Constants.LOG_FILE;

/**
 * Entry point class that has the main method
 */
public class Main extends Application {

    private Logs<ActivityLogger> activityLogger;
    private Logs<ApplicationLogger> applicationLogger;

    /**
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeLoggers();
        Parent root = FXMLLoader.load(getClass().getResource(FXMLVIEW.LOGIN.getValue()));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Appointment Scheduler");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Entry Point for the Application
     *
     * @param args
     */
    public static void main(String[] args) {
        setCurrentZone(ZoneId.systemDefault());
        launch(args);
        JDBC.closeConnection();
    }

    /**
     * Creates Activity Logger and Application Looger
     */
    private void initializeLoggers() {
        activityLogger = new ActivityLogger(LOG_FILE.activity_log.toString());
        applicationLogger = new ApplicationLogger(LOG_FILE.application_log.toString());
        setActivityLogger(activityLogger);
        setApplicationLogger(applicationLogger);
        getApplicationLogger().logINFO("\n--------Program initialized---------\n");
    }
}
