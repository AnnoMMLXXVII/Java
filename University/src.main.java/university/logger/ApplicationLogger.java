package university.logger;

import java.util.logging.Logger;

public class ApplicationLogger extends Logs<ApplicationLogger> {

	/**
	 * @param file application_log file
	 */
	public ApplicationLogger(String file) {
		log = Logger.getLogger(file);
		initLoggerConfigs(file);
	}

}
