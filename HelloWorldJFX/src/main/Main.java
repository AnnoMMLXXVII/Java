package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logs.ActivityLogger;
import logs.ApplicationLogger;
import logs.Logs;
import shared.Common;
import shared.Constants;
import shared.Constants.FXML;
import shared.JDBC;

import java.util.Locale;

public class Main extends Application {
    private Logs<ActivityLogger> activityLogger;
    private Logs<ApplicationLogger> applicationLogger;

    @Override
    public void start(Stage primaryStage) throws Exception{
        initializeLoggers();
        Parent root = FXMLLoader.load(getClass().getResource(FXML.LOGIN.getValue()));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Appointment Scheduler V.1");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
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
        activityLogger = new ActivityLogger(Constants.LOG_FILE.activity_log.toString());
        applicationLogger = new ApplicationLogger(Constants.LOG_FILE.application_log.toString());
        Common.setActivityLogger(activityLogger);
        Common.setApplicationLogger(applicationLogger);
        Common.getApplicationLogger().logINFO("\n--------Program initialized---------\n");
    }
}
