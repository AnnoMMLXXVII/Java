package university.pojo;

import java.sql.Date;

public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private Date bithdate;
	private int zipCode;
	private int facultyId;
	private int sectionId;

	/**
	 * 
	 */
	public Student() {
		super();
	}

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param phone
	 * @param bithdate
	 * @param zipCode
	 * @param facultyId
	 * @param sectionId
	 */
	public Student(int id, String firstName, String lastName, String address, String phone, Date bithdate, int zipCode,
			int facultyId, int sectionId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.bithdate = bithdate;
		this.zipCode = zipCode;
		this.facultyId = facultyId;
		this.sectionId = sectionId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the bithdate
	 */
	public Date getBithdate() {
		return bithdate;
	}

	/**
	 * @return the zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * @return the facultyId
	 */
	public int getFacultyId() {
		return facultyId;
	}

	/**
	 * @return the sectionId
	 */
	public int getSectionId() {
		return sectionId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param bithdate the bithdate to set
	 */
	public void setBithdate(Date bithdate) {
		this.bithdate = bithdate;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @param facultyId the facultyId to set
	 */
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	/**
	 * @param sectionId the sectionId to set
	 */
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((bithdate == null) ? 0 : bithdate.hashCode());
		result = prime * result + facultyId;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + sectionId;
		result = prime * result + zipCode;
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
		Student other = (Student) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (bithdate == null) {
			if (other.bithdate != null)
				return false;
		} else if (!bithdate.equals(other.bithdate))
			return false;
		if (facultyId != other.facultyId)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (sectionId != other.sectionId)
			return false;
		if (zipCode != other.zipCode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [" + id + ", " + firstName + ", " + lastName + ", " + address + ", " + phone + ", " + bithdate
				+ ", zipCode=" + zipCode + ", facultyId=" + facultyId + ", sectionId=" + sectionId + "]";
	}

}
