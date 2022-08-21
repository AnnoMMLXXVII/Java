/**
 * Name: Date: Assignment:
 * 
 * Purpose (Class Description): Abstract class that will represent the Question
 * Object. Has Subtypes of FillInTheBlank and MultipleChoice.
 * Has abstract methods in which the subclasses will need to override
 */
public abstract class Question {
	private String prompt;
	private int numOfPoints;

	/**
	 * @param prompt
	 * @param numOfPoints
	 */
	public Question(String prompt, int numOfPoints) {
		setPrompt(prompt);
		setNumOfPoints(numOfPoints);
	}

	/**
	 * @return the prompt
	 */
	public String getPrompt() {
		return prompt;
	}

	/**
	 * @return the numOfPoints
	 */
	public int getNumOfPoints() {
		return numOfPoints;
	}

	/**
	 * @param prompt the prompt to set
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	/**
	 * @param numOfPoints the numOfPoints to set
	 */
	public void setNumOfPoints(int numOfPoints) {
		this.numOfPoints = numOfPoints < 0 ? 0 : numOfPoints;
	}

	public abstract String getCorrectAnswer();

	public abstract String getQuestionDisplay();

	public abstract boolean isCorrectAnswer(String choice);

}
