import java.util.ArrayList;

/**
 * Name: Date: Assignment:
 * 
 * Purpose (Class Description): Class that will house all types of questions in
 * a List.
 */
public class Quiz {

	private ArrayList<Question> questions;

	public Quiz() {
		createSampleQuiz();
	}

	/**
	 * Method that generates a list of MutlipleChoice or FillInTheBlank questions
	 */
	public void createSampleQuiz() {
		questions = new ArrayList<>(); // instantiates the questions object
		MultipleChoiceQuestion mc = new MultipleChoiceQuestion("What letter is your favorite number?", 10, 3, "MC^2",
				"2B", "33333", "ABCDEFG");
		questions.add(mc);
		FillInTheBlankQuestion fitb = new FillInTheBlankQuestion("What goes up but never ever comes down?", 10, "Age",
				false);
		questions.add(fitb);
		mc = new MultipleChoiceQuestion("Which Bear is best?", 10, 1, "Snuggle Bear", "Black Bear", "Brown Bear",
				"Sushine Bear");
		questions.add(mc);
		fitb = new FillInTheBlankQuestion("Who is inevitable? (Hint. Think MCU)", 10, "Iron Man", false);
		questions.add(fitb);
		mc = new MultipleChoiceQuestion("What can one catch that is not thrown?", 10, 2, "You", "Me", "A Cold",
				"You and Me");
		questions.add(mc);
		mc = new MultipleChoiceQuestion("Which animal holds hands while sleeping?", 10, 1, "Only cute ones", "Otters",
				"Sea Bears", "Owls");
		questions.add(mc);
		fitb = new FillInTheBlankQuestion("Life is like a box of what?", 10, "Chocolate", false);
		questions.add(fitb);
		mc = new MultipleChoiceQuestion("Which part of the brain is responsible for balance?", 10, 3, "Ear Lobe",
				"Occipital lobe", "Amygdala", "Cerebelum");
		questions.add(mc);
		fitb = new FillInTheBlankQuestion("What is the root of all evil?", 10, "Money", false);
		questions.add(fitb);
		fitb = new FillInTheBlankQuestion("Yes or No? (Hint - Do not choose No)", 10, "Yes", false);
		questions.add(fitb);
	}

	/**
	 * Returns the questions arrayList
	 * 
	 * @return ArrayList : Question
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}
}
