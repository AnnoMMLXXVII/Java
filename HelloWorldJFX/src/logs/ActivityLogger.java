package logs;


import java.util.logging.Logger;

import static shared.Constants.LOG_FILE;

/**
 * Subclass of the Logs class
 */
public class ActivityLogger extends Logs<ActivityLogger> {

    /**
     * @param fileName For Activity log
     */
    public ActivityLogger(String fileName) {
        log = Logger.getLogger(LOG_FILE.login_activity.toString());
        initLoggerConfigs(fileName);
    }


}
