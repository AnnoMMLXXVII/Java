package cards.model;

public class Card {

	private Rank rank;
	private Suit suit;
	private String color;

	public Card(Rank rank, Suit suit, String color) {
		this.rank = rank;
		this.suit = suit;
		this.color = color;
	}

	private Rank getRank() {
		return rank;
	}

	private Suit getSuit() {
		return suit;
	}

	public String getColor() {
		return color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card [" + rank + " of " + suit + " : " + color + "]";
	}

}
