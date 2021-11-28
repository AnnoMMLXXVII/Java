package logs;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static shared.Common.*;

public abstract class Logs<T> {

    protected Logger log;
    protected FileHandler fh;
    protected SimpleFormatter sf;

    public synchronized void logINFO(String message) {
        log.log(Level.INFO, formatMessage(message));
    }

    public synchronized void logWARN(String message) {
        log.log(Level.WARNING, formatMessage(message));
    }

    public synchronized void logERROR(String message) {
        log.log(Level.SEVERE, formatMessage(message));
    }

    public void initLoggerConfigs(String file) {
        try {
            fh = new FileHandler(file, true);
            sf = new SimpleFormatter();
            fh.setFormatter(sf);
            log.addHandler(fh);
        } catch (IOException io) {
            Logger.getLogger(ActivityLogger.class.getName()).log(Level.SEVERE, null, io);
        } catch (SecurityException se) {
            Logger.getLogger(ActivityLogger.class.getName()).log(Level.SEVERE, null, se);
        }
    }

    protected String formatMessage(String message) {
        String time = getCurrentTime() + " " + getCurrentTime();
        try {
            time = formatDateTimeForDB(getCurrentDate(), getCurrentTime());
        } catch (ParseException e) {
            log.log(Level.SEVERE, "**Unable to Parse Current Date and Time**");
        }
        return String.format("%s %s", time, message);
    }

}
