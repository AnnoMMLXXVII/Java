package controller;

import dao.AppointmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.Appointment;
import shared.DataAccessObject;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.ResourceBundle;

import static shared.Common.*;
import static shared.Constants.FXMLVIEW;

public class HomeScreenController implements Initializable {

    private DataAccessObject<Appointment> appointmentDAO;
    private StringBuilder sb;
    private Timestamp current;
    private boolean isMeetingsSoon = false;
    @FXML
    private Button customerBtn;

    @FXML
    private Button appointmentsBtn;

    @FXML
    private Button reportsBtnHomeScreen;

    @FXML
    private Button databaseLogoutBtn;

    @FXML
    void onReportsBtnAction(ActionEvent event) {
        closePreviousWindow(reportsBtnHomeScreen);
        navigateToWindow(FXMLVIEW.REPORT, "Reports Screen");
    }

    @FXML
    void apptViewBtn(ActionEvent event) {
        closePreviousWindow(appointmentsBtn);
        navigateToWindow(FXMLVIEW.APPOINTMENT, "Appointments Screen");
    }

    @FXML
    void customerViewBtn(ActionEvent event) throws SQLException {
        closePreviousWindow(customerBtn);
        navigateToWindow(FXMLVIEW.CUSTOMER, "Customer Screen");
    }

    @FXML
    void onDBLogoutBtn(ActionEvent event) throws SQLException {
        closePreviousWindow(databaseLogoutBtn);
        navigateToWindow(FXMLVIEW.LOGIN, "Login Screen");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentDAO = new AppointmentDAO();
        sb = new StringBuilder();
        try {
            current = getTimestamp(getCurrentDate().toString(), getCurrentTime(formatUsingDTF(getCurrentTime(), "HH:mm:ss")).toString(), getCurrentZone().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        searchForUpcomingAppointments();
        if(isMeetingsSoon) {
            confirmationPopup(sb.toString());
            isMeetingsSoon = false;
        }
    }

    private void searchForUpcomingAppointments() {
        ObservableList<Appointment> appointments = appointmentDAO.getAll();
        ObservableMap<Timestamp, Appointment> mappedAppt = FXCollections.observableHashMap();
        appointments.stream().forEach(e -> {
            Timestamp ts = getTimestamp(e.getStart().split(" ")[0], e.getStart().split(" ")[1],
                    ZoneId.systemDefault().toString());
            int month = ts.toLocalDateTime().toLocalDate().getMonth().getValue();
            int day = ts.toLocalDateTime().toLocalDate().getDayOfMonth();
            if ((month == current.toLocalDateTime().toLocalDate().getMonth().getValue()) && day == current.toLocalDateTime().toLocalDate().getDayOfMonth()) {
                mappedAppt.put(ts, e);
            }
        });

        mappedAppt.forEach((e,v) -> {
            int diff = e.toLocalDateTime().toLocalTime().getMinute() - current.toLocalDateTime().toLocalTime().getMinute();
            if (diff > 0 && diff < 15) {
                sb.append(String.format("Appointment: %s\nStart Time %s\n",v.getTitle(), e.toLocalDateTime().toLocalTime().toString()));
                isMeetingsSoon = true;
            }
        });
    }



}

