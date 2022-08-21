package tester2;

public class AirplaneSeats {
	private int seatNumber;
	private boolean reserved;

	public AirplaneSeats(int seatNumber, boolean reserved) {
		super();
		this.seatNumber = seatNumber;
		this.reserved = reserved;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public void printInfo() {
		System.out.println(String.format("Seat #: %s\nReserved: %s\n", getSeatNumber(), isReserved() ? "YES" : "NO"));
	}

}