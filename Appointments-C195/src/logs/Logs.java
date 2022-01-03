package logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static shared.Common.*;

/**
 * Generics Abstract Class for the two Loggers
 * Activity and Application Logger
 *
 * @param <T> Generic Object
 */
public abstract class Logs<T> {

    protected Logger log;
    protected FileHandler fh;
    protected SimpleFormatter sf;

    /**
     * Logs the INFO related Level Messages
     *
     * @param message String
     */
    public synchronized void logINFO(String message) {
        log.log(Level.INFO, formatMessage(message));
    }

    /**
     * Logs the WARN related Level Messages
     *
     * @param message String
     */
    public synchronized void logWARN(String message) {
        log.log(Level.WARNING, formatMessage(message));
    }

    /**
     * Logs the ERROR related Level Messages
     *
     * @param message String
     */
    public synchronized void logERROR(String message) {
        log.log(Level.SEVERE, formatMessage(message));
    }

    /**
     * initializes the Loggers
     *
     * @param file String
     */
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

    /**
     * Protected method that will format the message
     *
     * @param message String
     * @return String
     */
    protected String formatMessage(String message) {
        return String.format("%s %s", formatDateTimeForDB(getCurrentDate(), getCurrentTime()), message);
    }

}
