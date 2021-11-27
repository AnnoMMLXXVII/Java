package assignments;

public class Student {
	private int id;
	public String firstName;
	public String lastName;
	private AssignmentMarks mathsMarks;
	private AssignmentMarks englishMarks;

	/**
	 * @param studentId
	 * @param studentFirstName
	 * @param studentLastName
	 * @param studentMathsMarks
	 * @param studentEnglishMarks
	 */
	public Student(int studentId, String studentFirstName, String studentLastName, AssignmentMarks studentMathsMarks,
			AssignmentMarks studentEnglishMarks) {
		id = studentId;
		firstName = studentFirstName;
		lastName = studentLastName;
		mathsMarks = studentMathsMarks;
		englishMarks = studentEnglishMarks;
	}

	/**
	 * @return String of First and Last name
	 */
	public String getFullName() {
		return String.format("%s %s", firstName, lastName);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the mathsMarks
	 */
	public AssignmentMarks getMathsMarks() {
		return mathsMarks;
	}

	/**
	 * @return the englishMarks
	 */
	public AssignmentMarks getEnglishMarks() {
		return englishMarks;
	}

	/**
	 * @param mathsMarks the mathsMarks to set
	 */
	public void setMathsMarks(AssignmentMarks mathsMarks) {
		this.mathsMarks = mathsMarks;
	}

	/**
	 * @param englishMarks the englishMarks to set
	 */
	public void setEnglishMarks(AssignmentMarks englishMarks) {
		this.englishMarks = englishMarks;
	}

}
