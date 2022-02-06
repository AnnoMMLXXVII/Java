package assignments.module.four;

public class Sentence {
	private StringBuilder sentence;

	public Sentence() {
		this.sentence = new StringBuilder("");
	}

	public void appendToSentence(String word) {
		this.sentence.append(word + " ");
	}

	public StringBuilder getSentence() {
		return sentence;
	}

	@Override
	public String toString() {
		return sentence.substring(0, 1).equalsIgnoreCase("a")
				? sentence.substring(0, 1).toUpperCase() + " " + sentence.substring(1).trim().toString() + "."
				: sentence.substring(0, 1).toUpperCase() + sentence.substring(1).trim().toString() + ".";
	}
}
