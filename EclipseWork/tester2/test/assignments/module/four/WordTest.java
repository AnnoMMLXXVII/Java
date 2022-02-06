package assignments.module.four;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WordTest {

	private Word word;

	@BeforeEach
	void setUp() throws Exception {
//		word = new Word();
	}

	@Test
	void testWordObjectIsNotNull() {
		assertNotEquals(null, word);
	}

	@Test
	void testIfGetCallsReturnRandomValues() {
		String article = word.getArticle();
		String noun = word.getNoun();
		String verb = word.getVerb();
		String prep = word.getPreposition();
		assertNotEquals(article, word.getArticle());
	}

}
