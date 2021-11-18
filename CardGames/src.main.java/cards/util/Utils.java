package cards.util;

import java.util.ArrayList;
import java.util.List;

import cards.model.Card;

public class Utils {
	private static List<Card> cards = new ArrayList<>();

	public static List<Card> getCards() {
		return cards;
	}

	public static void setCards(List<Card> cards) {
		Utils.cards = cards;
	}

}
