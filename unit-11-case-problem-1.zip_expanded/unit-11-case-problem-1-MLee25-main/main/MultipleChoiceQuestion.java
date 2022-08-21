/**
 * Name: Date: Assignment:
 * 
 * Purpose (Class Description): Model class that is the Subtype of the Questions
 * Object. Overrides the getCorrectAnswer, getQuestsionDisplay, isCorrectAnswer
 * methods
 */
public class MultipleChoiceQuestion extends Question {
	private String[] answerChoices;
	private int correctAnswerIndex;

	public MultipleChoiceQuestion(String prompt, int numOfPoints, int correctAnswerIndex, String... answerChoices) {
		super(prompt, numOfPoints);
		setAnswerChoices(answerChoices);
		setCorrectAnswerIndex(correctAnswerIndex);
	}

	@Override
	public String getCorrectAnswer() {
		return getCorrectAnswerIndex() == -1 ? "ERROR: Correct answer is unknown."
				: getAnswerChoices()[getCorrectAnswerIndex()];
	}

	@Override
	public String getQuestionDisplay() {
		StringBuilder sb = new StringBuilder();
		sb.append(getPrompt() + "\n");
		int i = 0;
		for (String s : getAnswerChoices()) {
			sb.append(String.format("\t(%d).  %s\n", (i + 1), s));
			i++;
		}
		return sb.toString();
	}

	@Override
	public boolean isCorrectAnswer(String choice) {
		return getCorrectAnswer().equalsIgnoreCase(choice);
	}

	/**
	 * @return the answerChoices
	 */
	public String[] getAnswerChoices() {
		return answerChoices;
	}

	/**
	 * @return the correctAnswerIndex
	 */
	public int getCorrectAnswerIndex() {
		return correctAnswerIndex;
	}

	/**
	 * @param answerChoices the answerChoices to set
	 */
	public void setAnswerChoices(String[] answerChoices) {
		this.answerChoices = answerChoices;
	}

	/**
	 * @param correctAnswerIndex the correctAnswerIndex to set
	 */
	public void setCorrectAnswerIndex(int correctAnswerIndex) {
		this.correctAnswerIndex = correctAnswerIndex > getAnswerChoices().length || correctAnswerIndex < 0 ? -1
				: correctAnswerIndex;
	}

}
