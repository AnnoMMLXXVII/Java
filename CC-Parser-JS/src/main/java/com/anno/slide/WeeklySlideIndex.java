package main.java.com.anno.slide;

public class WeeklySlideIndex {

	private int week;
	private String topic;
	private String file;

	/**
	 * @param week
	 * @param topic
	 * @param file
	 */
	public WeeklySlideIndex(int week, String topic, String file) {
		super();
		this.week = week;
		this.topic = topic;
		this.file = file;
	}
	
	public WeeklySlideIndex(String string) {
		
	}

	/**
	 * @return the week
	 */
	public int getWeek() {
		return week;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @return the file
	 */
	public String getFile() {
		return file;
	}

}
