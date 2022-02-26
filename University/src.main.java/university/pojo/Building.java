package university.pojo;

public class Building {

	private int id;
	private String address;
	private String name;
	private int campusId;
	private String zipCode;
	private int roomNumber;

	/**
	 * @param id
	 * @param address
	 * @param name
	 * @param campusId
	 * @param zipCode
	 * @param roomNumber
	 */
	public Building(int id, String address, String name, int campusId, String zipCode, int roomNumber) {
		super();
		this.id = id;
		this.address = address;
		this.name = name;
		this.campusId = campusId;
		this.zipCode = zipCode;
		this.roomNumber = roomNumber;
	}

	/**
	 * 
	 */
	public Building() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the campusId
	 */
	public int getCampusId() {
		return campusId;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @return the roomNumber
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param campusId the campusId to set
	 */
	public void setCampusId(int campusId) {
		this.campusId = campusId;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @param roomNumber the roomNumber to set
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + campusId;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + roomNumber;
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		Building other = (Building) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (campusId != other.campusId)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Building [id=" + id + ", address=" + address + ", name=" + name + ", campusId=" + campusId
				+ ", zipCode=" + zipCode + ", roomNumber=" + roomNumber + "]";
	}

}
