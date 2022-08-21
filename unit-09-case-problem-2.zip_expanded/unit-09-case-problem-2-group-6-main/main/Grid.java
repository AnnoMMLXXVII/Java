import java.util.Random;

/**
 * Name: Stephen Dellinger Date:3/20/2022 Assignment:Unit 9 Case Problem 2
 * 
 * Purpose (Class Description): This class is for the grid for the matching
 * game.
 */
public class Grid {
	// Declare the length/width of the cards
	public static final int NUM_OF_PAIRS = 4;
	// Declare the 2-D array for the board.
	private Card[][] board;
	private int[][] uniqueCounter2D = new int[NUM_OF_PAIRS][NUM_OF_PAIRS];

	public Grid() {
		generateCards();
		shuffle(4);
	}

	public void generateCards() {
		Random r = new Random();
		board = new Card[NUM_OF_PAIRS][NUM_OF_PAIRS];
		initializeUniqueTracker();
		int rand = r.nextInt(r.nextInt(50) + 1) + 1;
		// Generate the Random Pairs
		if (rand % 2 != 0) {
			rand += 1; // Offset to guarantee an Even-Digit Random Number
		}
		Card.Value[] pairs = new Card.Value[rand];
		for (int i = 0, j = 1; i < pairs.length && j < pairs.length; i += 2, j += 2) {
			rand = r.nextInt(Card.Value.values().length - 1) + 0;
			pairs[i] = Card.Value.values()[rand];
			pairs[j] = Card.Value.values()[rand];
		}
		int pairCounter = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (pairCounter >= pairs.length) {
					break;
				}
				board[i][j] = new Card(pairs[pairCounter]);
//				if (pairCounter % 4 == 0) {
				board[i][j].hide();
//				} else {
//					board[i][j].show();
//				}
				pairCounter++;
			}
		}

	}

	public void shuffle(int count) {
		Random r = new Random();
		int rand = r.nextInt(9999) + 1;
		if (count <= 0) {
			shuffleGrid(new Random(rand));
		} else {
			for (int i = 0; i < count; i++) {
				shuffleGrid(new Random(rand));
				rand = r.nextInt(999) + 1;
			}
		}
	}

	private void shuffleGrid(Random r) {
		for (int i = board.length - 1; i > 0; i--) {
			for (int j = board[i].length - 1; j > 0; j--) {
				if (board[i][j] == null) {
					continue;
				}
				int m = r.nextInt(i + 1);
				int n = r.nextInt(j + 1);
				Card temp = board[i][j];
				board[i][j] = board[m][n];
				board[m][n] = temp;
			}
		}
	}

	public String getGridDisplay() {
		StringBuilder sb = new StringBuilder();
		boolean eof = false;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == null) {
					eof = true;
					continue;
				}
				sb.append(String.format("[%10s-(%2d)]",
						(board[i][j].isShown()) ? "*" + board[i][j].toString() + "*" : board[i][j].toString(),
						(uniqueCounter2D[i][j] + 1)));
			}
			sb.append("\n");
			if (eof) {
				continue;
			}
		}
		return sb.toString();
	}

	/**
	 * takes the number that represents the position of the card and returns the
	 * card
	 */
	public Card getCard(int num) {
		if (isInValidPosition(num)) {
			return null;
		}
		Card card = null;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (uniqueCounter2D[i][j] == num && board[i][j] != null) {
					card = board[i][j];
				}
			}
		}
		return card;
	}

	/**
	 * takes the number that represents the card and returns the cards value
	 */
	public Card.Value getCardValue(int num) {
		return getCard(num).getValue();
	}

	/**
	 * sets the card to be shown
	 * 
	 * @return
	 */
	public void showCard(int num) {
		if (isInValidPosition(num)) {
			return;
		}
		getCard(num).show();
	}

	/**
	 * sets the card to be hidden
	 * 
	 * @return
	 */
	public void hideCard(int num) {
		if (isInValidPosition(num)) {
			return;
		}
		getCard(num).hide();
	}

	/**
	 * returns the isShown value of the card
	 * 
	 */
	public boolean isCardShown(int num) {
		return getCard(num).isShown();
	}

	/**
	 * uses a loop to determine if cards are still available to play
	 * 
	 * @return
	 */
	public boolean hasCards() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != null) {
					return true;
				}
			}
		}
		return false;
	}

	public void removeCard(int num) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (uniqueCounter2D[i][j] == num && board[i][j] != null) {
					board[i][j] = null;
				}
			}
		}
	}

	private boolean isInValidPosition(int num) {
		return (num < 0 || num > (NUM_OF_PAIRS * NUM_OF_PAIRS));
	}

	private void initializeUniqueTracker() {
		int unqiueCounter = 0;
		for (int i = 0; i < uniqueCounter2D.length; i++) {
			for (int j = 0; j < uniqueCounter2D[i].length; j++) {
				uniqueCounter2D[i][j] = unqiueCounter;
				unqiueCounter++;

			}
		}
	}

}
