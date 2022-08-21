/**
 * Name: Date: Assignment:
 * 
 * Purpose (Class Description): Model class that is the Subtype of the Questions
 * Object. Overrides the getCorrectAnswer, getQuestsionDisplay, isCorrectAnswer
 * methods
 */
public class FillInTheBlankQuestion extends Question {

	private String correctAnswer;
	private boolean isAnswerCaseSensitive;

	public FillInTheBlankQuestion(String prompt, int numOfPoints, String correctAnswer, boolean isAnswerCaseSensitive) {
		super(prompt, numOfPoints);
		setCorrectAnswer(correctAnswer);
		setAnswerCaseSensitive(isAnswerCaseSensitive);
	}

	@Override
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	@Override
	public String getQuestionDisplay() {
		return getPrompt();
	}

	@Override
	public boolean isCorrectAnswer(String choice) {
		return isAnswerCaseSensitive() ? getCorrectAnswer().toLowerCase().contains(choice.toLowerCase())
				: getCorrectAnswer().equalsIgnoreCase(choice);
	}

	/**
	 * @return the isAnswerCaseSensitive
	 */
	public boolean isAnswerCaseSensitive() {
		return isAnswerCaseSensitive;
	}

	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	/**
	 * @param isAnswerCaseSensitive the isAnswerCaseSensitive to set
	 */
	public void setAnswerCaseSensitive(boolean isAnswerCaseSensitive) {
		this.isAnswerCaseSensitive = isAnswerCaseSensitive;
	}

}
