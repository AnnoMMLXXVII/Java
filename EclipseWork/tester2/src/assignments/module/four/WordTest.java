package assignments.module.four;

import java.util.ArrayList;
import java.util.List;

public class WordTest {
	private static Word word = new Word();

	public static void main(String[] args) {
		List<Sentence> sentences = new ArrayList<>();
		int i = 20;
		while (--i > -1) {
			sentences.add(generateSentence());
		}
		sentences.forEach(e -> System.out.println(e.toString()));
	}

	private static Sentence generateSentence() {
		Sentence sentence = new Sentence();
		sentence.appendToSentence(word.getArticle());
		sentence.appendToSentence(word.getNoun());
		sentence.appendToSentence(word.getVerb());
		sentence.appendToSentence(word.getPreposition());
		sentence.appendToSentence(word.getArticle());
		sentence.appendToSentence(word.getNoun());
		return sentence;
	}

}
