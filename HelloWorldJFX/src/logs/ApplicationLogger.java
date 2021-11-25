package logs;

import shared.Constants;

import java.util.logging.Logger;

/**
 *
 */
public class ApplicationLogger extends Logs<ApplicationLogger> {

    /**
     * @param file application_log file
     */
    public ApplicationLogger(String file) {
        log = Logger.getLogger(Constants.LOG_FILE.application_log.toString());
        initLoggerConfigs(file);
    }
}
