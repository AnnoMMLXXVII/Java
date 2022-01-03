package logs;

import java.util.logging.Logger;

import static shared.Constants.LOG_FILE;

/**
 * Subclass to of the Logs class
 */
public class ApplicationLogger extends Logs<ApplicationLogger> {

    /**
     * @param file application_log file
     */
    public ApplicationLogger(String file) {
        log = Logger.getLogger(LOG_FILE.application_log.toString());
        initLoggerConfigs(file);
    }
}
