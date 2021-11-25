package logs;


import shared.Constants;

import java.util.logging.Logger;

public class ActivityLogger extends Logs<ActivityLogger> {

    /**
     *
     * @param fileName For Activity log
     */
    public ActivityLogger(String fileName) {
        log = Logger.getLogger(Constants.LOG_FILE.activity_log.toString());
        initLoggerConfigs(fileName);
    }




}
