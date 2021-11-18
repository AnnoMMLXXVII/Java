package cards.util;

import java.util.ArrayList;
import java.util.List;

import cards.model.Card;
import cards.model.Rank;
import cards.model.Suit;
import cards.shared.CardConstants;

public class Initialization {

	public static void initializeCards() {
		List<Card> temp = new ArrayList<>();
		for (Rank r : Rank.values()) {
			for (Suit s : Suit.values()) {
				temp.add(new Card(r, s,
						(s.equals(Suit.HEARTS) || s.equals(Suit.DIAMONDS)) ? CardConstants.RED : CardConstants.BLACK));
			}
		}
		Utils.setCards(temp);
	}

}
