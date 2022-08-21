import java.util.Scanner;

/**
 * Name:Stephen Dellinger Date:3/13/2022 Assignment:unit 8 case problem 2
 * 
 * Purpose (Class Description):
 */

public class QuizGenerator {

	public static void main(String[] args) {
		MultipleChoiceQuestion[] mc = new MultipleChoiceQuestion[5];
		for (int i = 0; i < mc.length; i++) {
			mc[i] = createMultipleChoiceQuestions(new Scanner(System.in));
			if (i % 2 == 0) {
				mc[i].setShowAnswer(false);
			}
		}
		displayQuizDetails(mc);
	}

	public static MultipleChoiceQuestion createMultipleChoiceQuestions(Scanner z) {
		if (z == null) {
			throw new NullPointerException("Scanner object is null!");
		}
		String text = getTextFromUser(z);
		String[] choices = getChoicesFromUser(z);
		Integer answerIdx = getAnswerFromUser(choices, z);
		return new MultipleChoiceQuestion(text, choices, answerIdx);
	}

	private static String getTextFromUser(Scanner z) {
		System.out.println("Enter the Question for the Multiple Choice:");
		return z.nextLine();
	}

	private static String[] getChoicesFromUser(Scanner z) {
		System.out.println("Enter Four Choices the Question: ");
		String[] choices = new String[4];
		int i = 0;
		while (i < 4) {
			String temp = z.nextLine();
			if (!temp.equalsIgnoreCase("")) {
				choices[i] = temp.trim();
				i++;
			} else {
				System.out.println("Please enter a valid choice (non-empty value)");
			}
		}
		return choices;
	}

	private static Integer getAnswerFromUser(String[] choices, Scanner z) {
		System.out.println("Please enter number that matches the correct answer for the question: ");
		Integer answerIdx = -1;
		for (int i = 0; i < choices.length; i++) {
			System.out.printf("(%1d) - %s\n", i + 1, choices[i]);
		}
		answerIdx = z.nextInt();
		if (answerIdx < 0 || answerIdx >= choices.length) {
			z.nextLine();
			getAnswerFromUser(choices, z);
		}
		return answerIdx - 1;
	}

	private static void displayQuizDetails(MultipleChoiceQuestion[] mc) {
		System.out.println("--------------Multiple Choice Quiz--------------");
		for (int i = 0; i < mc.length; i++) {
			System.out.printf("(%1d). %s\n", (i + 1), mc[i].toString());
		}
	}

}
