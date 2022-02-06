package assignments.module.four;

import java.util.Random;

public class Word {
	private String[] articles;
	private String[] nouns;
	private String[] verbs;
	private String[] prepositions;
	private Random r;

	public Word() {
		r = new Random();
		articles = new String[] { "the", "a", "one", "some", "any" };
		nouns = new String[] { "boy", "girl", "dog", "town", "car" };
		verbs = new String[] { "drove", "jumped", "ran", "walked", "skipped" };
		prepositions = new String[] { "to", "from", "over", "under", "on" };
	}

	/**
	 * @return the articles
	 */
	public String getArticle() {
		r.nextInt();
		return articles[(r.nextInt(articles.length - 1) + 0)];
	}

	/**
	 * @return the nouns
	 */
	public String getNoun() {
		r = new Random();
		r.nextInt();
		return nouns[r.nextInt(articles.length - 1) + 0];
	}

	/**
	 * @return the verbs
	 */
	public String getVerb() {
		r.nextInt();
		return verbs[r.nextInt(articles.length - 1) + 0];
	}

	/**
	 * @return the prepositions
	 */
	public String getPreposition() {
		r.nextInt();
		return prepositions[r.nextInt(articles.length - 1) + 0];
	}

}
