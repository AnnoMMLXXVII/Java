/**
 * Name: Stephen Dellinger Date: 3/20/2022 Assignment:unit 9 Case Problem 2
 * 
 * Purpose (Class Description): This class is for the cards for the matching
 * game.
 */
public class Card {
	private Value value;
	private boolean isShown;

	public enum Value {
		Cat, Dog, Mouse, Cow, Chicken, Horse, Pig, Snake, Raccoon, Spider
	}

	public Card(Value value) {
		setValue(value);
		setShown(false);
	}

	/**
	 * @return the value
	 */
	public Value getValue() {
		return value;
	}

	/**
	 * @return the isShown
	 */
	public boolean isShown() {
		return isShown;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Value value) {
		this.value = value;
	}

	/**
	 * @param isShown the isShown to set
	 */
	private void setShown(boolean isShown) {
		this.isShown = isShown;
	}

	public void show() {
		setShown(true);
	}

	public void hide() {
		setShown(false);
	}

	public String toString() {
		return String.format("%s", (isShown() ? getValue() : "HIDDEN"));
	}

}
