package university.pojo;

public class ClassRoom {

	private int roomNumber;
	private int occupancy;

	/**
	 * 
	 */
	public ClassRoom() {
		super();
	}

	/**
	 * @param roomNumber
	 * @param occupancy
	 */
	public ClassRoom(int roomNumber, int occupancy) {
		super();
		this.roomNumber = roomNumber;
		this.occupancy = occupancy;
	}

	/**
	 * @return the roomNumber
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @return the occupancy
	 */
	public int getOccupancy() {
		return occupancy;
	}

	/**
	 * @param roomNumber the roomNumber to set
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * @param occupancy the occupancy to set
	 */
	public void setOccupancy(int occupancy) {
		this.occupancy = occupancy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + occupancy;
		result = prime * result + roomNumber;
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
		ClassRoom other = (ClassRoom) obj;
		if (occupancy != other.occupancy)
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClassRoom [" + roomNumber + ", " + occupancy + "]";
	}

}
