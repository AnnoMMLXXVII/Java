
public class DuplicateCharacter {
	private char letter;
	private int count;
	public DuplicateCharacter(char letter, int count) {
		super();
		this.letter = letter;
		this.count = count;
	}
	public char getLetter() {
		return letter;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return " [char=" + String.valueOf(letter).toLowerCase().trim() + ", count=" + count + "]";
	}
}
