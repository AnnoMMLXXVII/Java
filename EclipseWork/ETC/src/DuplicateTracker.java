
public class DuplicateTracker {
	private DuplicateCharacter[] dupChars = null;
	private int size = 0;
	private boolean isExist = false;

	public DuplicateTracker() {
		dupChars = new DuplicateCharacter[size];
	}

	public void checkIfCharExists(char c) {
		if (dupChars.length == 0) {
			dupChars = increaseArraySize();
			addNewCharToArray(c);
			return;
		}
		int i = 0;
		while (dupChars[i] != null && i < dupChars.length - 1) {
			if (dupChars[i].getLetter() == c) {
				isExist = true;
				break;
			}
			i++;
		}

		if (isExist) {
			increaseCount(c);
			return;
		}
		dupChars = increaseArraySize();
		addNewCharToArray(c);
	}

	private void addNewCharToArray(char c) {
		dupChars[size - 1] = new DuplicateCharacter(c, 1);
	}

	private void increaseCount(char c) {
		for (int i = 0; i < dupChars.length - 1; i++) {
			if (dupChars[i].getLetter() == c) {
				int counter = dupChars[i].getCount() + 1;
				dupChars[i].setCount(counter);
				isExist = false;
				break;
			}
		}
	}

	private DuplicateCharacter[] increaseArraySize() {
		size = size + 1;
		DuplicateCharacter[] temp = new DuplicateCharacter[size];
		for (int i = 0; i < dupChars.length; i++) {
			temp[i] = dupChars[i];
		}
		return temp;
	}

	public DuplicateCharacter[] getDuplicateCharacters() {
		return dupChars;
	}

	public void printChars() {
		for (int i = 0; i < dupChars.length; i++) {
			System.out.println(dupChars[i].toString());
		}
	}
}
