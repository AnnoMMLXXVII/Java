package main.java.com.anno.slide;

import java.util.List;

public class WeeklySection {
	List<WeeklySlideIndex> indexes;

	/**
	 * @param indexes
	 */
	public WeeklySection(List<WeeklySlideIndex> indexes) {
		super();
		this.indexes = indexes;
	}

	/**
	 * @return the indexes
	 */
	public List<WeeklySlideIndex> getIndexes() {
		return indexes;
	}

	public String toString() {
		return indexes.toString().replace(",", "").replace("[", "").replace("]", "");
	}
}
