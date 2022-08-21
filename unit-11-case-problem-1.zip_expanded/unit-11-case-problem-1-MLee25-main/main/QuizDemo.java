import java.util.Scanner;
import java.util.ArrayList;

/**
 */
public class QuizDemo {
	public static void main(String[] args) {
		Quiz currentQuiz = new Quiz();

		Scanner input = new Scanner(System.in);

		ArrayList<Question> questions = currentQuiz.getQuestions();
		int numOfQuestions = questions.size();
		int totalPoints = 0;
		int pointsEarned = 0;
		for (int i = 0; i < numOfQuestions; i++) {
			Question question = questions.get(i);
			int pointsForQuestion = question.getNumOfPoints();
			totalPoints += pointsForQuestion;

			System.out.println(
					"Question " + (i + 1) + " of " + numOfQuestions + " (Points Possible: " + pointsForQuestion + ")");
			String userAnswer = askQuestion(input, question);
			if (question.isCorrectAnswer(userAnswer)) {
				pointsEarned += pointsForQuestion;
			}
			System.out.println();
		}

		System.out.println("You have now completed the quiz");
		System.out.println("You earned " + pointsEarned + " out of " + totalPoints);

		input.close();
	}

	/**
	 * This will ask the user the given question. If it is a MultipleChoiceQuestion
	 * it will also ensure a valid response is provided first. It returns the answer
	 * the user enters/selects.
	 * 
	 * @param input    The Scanner that gets input from the console
	 * @param question The question to ask the user
	 * @return The answer entered/selected by the user
	 */
	public static String askQuestion(Scanner input, Question question) {
		if (question instanceof MultipleChoiceQuestion) {
			return askMultipleChoiceQuestion(input, (MultipleChoiceQuestion) question);
		} else {
			System.out.println(question.getQuestionDisplay());
			String userAnswer = input.nextLine();
			return userAnswer;
		}
	}

	/**
	 * This is to handle asking a MultipleChoiceQuestion to ensure the number the
	 * user enters is a valid option. It will then return the text of the answer
	 * choice number the user selected.
	 * 
	 * @param input    The Scanner that gets input from the console
	 * @param question The question to ask the user
	 * @return The text of the answer choice number the user selects
	 */
	public static String askMultipleChoiceQuestion(Scanner input, MultipleChoiceQuestion question) {
		String[] answerChoices = question.getAnswerChoices();
		int numOfAnswerChoices = answerChoices.length;

		System.out.println(question.getQuestionDisplay());
		int userAnswerNum = input.nextInt();
		input.nextLine();
		while (userAnswerNum < 1 || userAnswerNum > numOfAnswerChoices) {
			System.out.println("Invalid choice entered.  Enter a number from the choices.");
			userAnswerNum = input.nextInt();
			input.nextLine();
		}

		return answerChoices[userAnswerNum - 1];
	}
}
