public class MultipleChoiceQuestion {

	private String questionText;
	private String[] choices;
	private int correctAnswer;
	private static final int INVALID_ANSWER = -1;
	private boolean showAnswer;
	private static final String[] BULLET_POINTS = { "A", "B", "C", "D" };

	public MultipleChoiceQuestion(String questionText, String[] choices, int correctAnswer) {
		super();
		setQuestionText(questionText);
		setChoices(choices);
		setCorrectAnswer(correctAnswer);
		setShowAnswer(true);
	}

	public String getQuestionText() {
		return questionText;
	}

	public String[] getChoices() {
		return choices;
	}

	public int getCorrectAnswerIndex() {
		return correctAnswer;
	}

	public String getCorrectAnswerString() {
		return getCorrectAnswerIndex() == INVALID_ANSWER ? "Invalid Answer Choice Set"
				: choices[getCorrectAnswerIndex()];
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public void setChoices(String[] choices) {
		this.choices = choices;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer > getChoices().length || correctAnswer < 0 ? INVALID_ANSWER : correctAnswer;
	}

	public boolean isShowAnswer() {
		return showAnswer;
	}

	public void setShowAnswer(boolean showAnswer) {
		this.showAnswer = showAnswer;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (String s : BULLET_POINTS) {
			sb.append(String.format("\t[%1s] - %s\n", s, choices[i]));
			i++;
		}
		return String
				.format("Question: %s\n%s%s", getQuestionText(), sb.toString(),
						(isShowAnswer()) ? String.format("***Answer: %s\n", getCorrectAnswerString()) : "".trim())
				.trim();
	}

}
