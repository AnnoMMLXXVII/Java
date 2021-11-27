package assignments;

/**
 * @author
 *
 */
public class AssignmentMarks {

	private String name;
	private Integer assignment1;
	private Integer assignment2;
	private Integer assignment3;

	/**
	 * @param name
	 * @param mark1
	 * @param mark2
	 * @param mark3
	 */
	public AssignmentMarks(String name, Integer mark1, Integer mark2, Integer mark3) {
		this.name = name;
		this.assignment1 = mark1;
		this.assignment2 = mark2;
		this.assignment3 = mark3;
	}

	/**
	 * @param assignmentNumber
	 * @param mark
	 */
	public void setMark(Integer assignmentNumber, Integer mark) {
		if (assignmentNumber == 1) {
			this.assignment1 = mark;
		} else if (assignmentNumber == 2) {
			this.assignment2 = mark;
		} else if (assignmentNumber == 3) {
			this.assignment3 = mark;
		}
	}

	/**
	 * @param assignmentNumber
	 * @return Integer
	 */
	public Integer getMark(Integer assignmentNumber) {
		if (assignmentNumber == 1) {
			return this.assignment1;
		} else if (assignmentNumber == 2) {
			return this.assignment2;
		} else {
			return this.assignment3;
		}
	}

	/**
	 * @return Integer
	 */
	public Integer getAverageMark() {
		return (this.assignment1 + this.assignment2 + this.assignment3) / 3;
	}

	/**
	 * @param assignmentNumber
	 * @return String
	 */
	public String getGrade(Integer assignmentNumber) {
		Integer mark = getMark(assignmentNumber);
		return markToGrade(mark);
	}

	/**
	 * @return String
	 */
	public String getAverageGrade() {
		return markToGrade(getAverageMark());
	}

	/**
	 * @param mark
	 * @return String
	 */
	public String markToGrade(Integer mark) {
		if (mark >= 95 && mark <= 100) {
			return ("A+");
		} else if (mark >= 90 && mark <= 94) {
			return ("A");
		} else if (mark >= 85 && mark <= 89) {
			return ("A-");
		} else if (mark >= 80 && mark <= 84) {
			return ("B+");
		} else if (mark >= 75 && mark <= 79) {
			return ("B");
		} else if (mark >= 70 && mark <= 74) {
			return ("B-");
		} else if (mark >= 60 && mark <= 69) {
			return ("C+");
		} else if (mark >= 50 && mark <= 59) {
			return ("C");
		} else if (mark >= 41 && mark <= 49) {
			return ("C-");
		} else if (mark >= 0 && mark <= 40) {
			return ("D");
		} else {
			return ("E");
		}
	}

	/**
	 * @param name
	 */
	public void courseNames(String name) {
		this.name = name;
	}

	/**
	 * @return String
	 */
	public String getCourseName() {
		return name;
	}

}
