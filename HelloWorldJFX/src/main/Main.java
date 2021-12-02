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

import java.util.Locale;

import static shared.Common.*;
import static shared.Constants.LOG_FILE;
import static shared.Constants.FXMLVIEW;

public class Main extends Application {
    private Logs<ActivityLogger> activityLogger;
    private Logs<ApplicationLogger> applicationLogger;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeLoggers();
        Parent root = FXMLLoader.load(getClass().getResource(FXMLVIEW.LOGIN.getValue()));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Appointment Scheduler V.1");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        setSystemToFrench();
        launch(args);
        JDBC.closeConnection();
    }

    /**
     * Optional Method that can be used to test French
     */
    private static void setSystemToFrench() {
        Locale french = new Locale("fr", "fr");
        Locale.setDefault(french);
    }

    private void initializeLoggers() {
//        setSystemToFrench();
        activityLogger = new ActivityLogger(LOG_FILE.activity_log.toString());
        applicationLogger = new ApplicationLogger(LOG_FILE.application_log.toString());
        setActivityLogger(activityLogger);
        setApplicationLogger(applicationLogger);
        getApplicationLogger().logINFO("\n--------Program initialized---------\n");
    }
}
