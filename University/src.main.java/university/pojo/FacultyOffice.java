package university.pojo;

public class FacultyOffice {

	private int officeNumber;
	private int buildingId;

	/**
	 * 
	 */
	public FacultyOffice() {
		super();
	}

	/**
	 * @param officeNumber
	 * @param buildingId
	 */
	public FacultyOffice(int officeNumber, int buildingId) {
		super();
		this.officeNumber = officeNumber;
		this.buildingId = buildingId;
	}

	/**
	 * @return the officeNumber
	 */
	public int getOfficeNumber() {
		return officeNumber;
	}

	/**
	 * @return the buildingId
	 */
	public int getBuildingId() {
		return buildingId;
	}

	/**
	 * @param officeNumber the officeNumber to set
	 */
	public void setOfficeNumber(int officeNumber) {
		this.officeNumber = officeNumber;
	}

	/**
	 * @param buildingId the buildingId to set
	 */
	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + buildingId;
		result = prime * result + officeNumber;
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
		FacultyOffice other = (FacultyOffice) obj;
		if (buildingId != other.buildingId)
			return false;
		if (officeNumber != other.officeNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FacultyOffice [" + officeNumber + ", " + buildingId + "]";
	}

}
