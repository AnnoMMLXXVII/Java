package university.shared;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import university.logger.ApplicationLogger;
import university.logger.Logs;

public class Common {

	private static Logs<ApplicationLogger> applicationLogger;
	private static Random r = new Random(System.currentTimeMillis());
	private static Integer[] nums = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private static String[] grades = new String[] { "A", "B", "C", "D", "F" };
	private static StringBuilder sb = new StringBuilder();

	/**
	 * Getter method that returns LocalDate by the current time zone
	 *
	 * @return LocalDate
	 */
	public static LocalDate getCurrentDate() {
		return LocalDate.now(getCurrentZone());
	}

	/**
	 * Getter Method that will return the current Time Zone using ZoneId
	 *
	 * @return ZoneId
	 */
	public static ZoneId getCurrentZone() {
		return ZoneId.systemDefault();
	}

	/**
	 * Getter Method that will return LocalTime by the current Time zone
	 *
	 * @return LocalDate
	 */
	public static LocalTime getCurrentTime() {
		return LocalTime.now(getCurrentZone());
	}

	/**
	 * Method that will return a formatted LocalDate and LocalTime LocalDate format
	 * - yyyy-MM-dd LocalTime format - HH:mm:ss
	 *
	 * @param date LocalDate
	 * @param time LocalTime
	 * @return String.Format
	 */
	public static String formatDateTimeForDB(LocalDate date, LocalTime time) {
		String format = "";
		try {
			format = String.format("%s %s", formatUsingDTF(date, "yyyy-MM-dd"), formatUsingDTF(time, "hh:mm:ss"));
		} catch (ParseException e) {
			getApplicationLogger().logERROR(
					"Parse Exception : Unable to parse Date and Time ::  " + date.toString() + time.toString());
		}
		return format;
	}

	/**
	 * Getter Method that returns a specifically formatted Date
	 *
	 * @param date    LocalDate
	 * @param pattern String
	 * @return String
	 * @throws ParseException parseException
	 */
	public static String formatUsingDTF(LocalDate date, String pattern) throws ParseException {
		DateTimeFormatter dateDTF = DateTimeFormatter.ofPattern(pattern);
		return String.format("%s", dateDTF.format(date));
	}

	/**
	 * Overload method that returns a specifically formatted Time
	 *
	 * @param time    LocalTime
	 * @param pattern String
	 * @return String
	 * @throws ParseException parseException
	 */
	public static String formatUsingDTF(LocalTime time, String pattern) throws ParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		return String.format("%s", dtf.format(time));
	}

	/**
	 * @param logger Logs
	 */
	public static void setApplicationLogger(Logs<?> logger) {
		applicationLogger = (ApplicationLogger) logger;
	}

	/**
	 * @return ApplicationLogger
	 */
	public static Logs<ApplicationLogger> getApplicationLogger() {
		return applicationLogger;
	}

	public static int generateRandomID() {
		return 1 + r.nextInt(90001);
	}

	public static synchronized int generateRandomID(int n) {
		sb.append(0 + r.nextInt(nums.length - 1));
		for (int i = 1; i < n; i++) {
			sb.append(0 + r.nextInt(nums.length - 1));
			System.out.printf("%s - %s\n", i, sb);
		}
		int temp = Integer.parseInt(sb.toString());
		sb = new StringBuilder();
		return temp;
	}

	public static synchronized Date generateRandomDate() {
		return new Date(0 + r.nextLong());
	}

	public static synchronized String generateRandomString(int n) {
		if (n < 0 || n == 0) {
			n = 10 + r.nextInt(21);
		}
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = n;
		String s = r.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return s;
	}

	public static synchronized Date generateRandomDateConditionally() {
		if (0 + r.nextInt(10) % 2 == 0) {
			return generateRandomDate();
		}
		return null;
	}

	public static synchronized String generateRandomGrade() {
		String grade = grades[0 + r.nextInt(grades.length - 1)];
		if (grade.equalsIgnoreCase("F")) {
			return grade;
		}
		if (r.nextBoolean()) {
			return grade;
		} else {
			return 0 + r.nextInt(100) % 5 == 0 ? grade + "+" : grade + "-";
		}
	}
	
//	public static synchronized Object generateConditionalEnroll() {
//		if(0+r.nextInt(1000)%25 ==0) {
//			
//		}
//	}

}
