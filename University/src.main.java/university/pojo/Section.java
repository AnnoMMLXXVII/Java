package university.pojo;

import java.sql.Date;

public class Section {

	private int id;
	private int number;
	private int term;
	private Date year;
	private int courseId;
	private int roomNumber;

	/**
	 * 
	 */
	public Section() {
		super();
	}

	/**
	 * @param id
	 * @param number
	 * @param term
	 * @param year
	 * @param courseId
	 * @param roomNumber
	 */
	public Section(int id, int number, int term, Date year, int courseId, int roomNumber) {
		super();
		this.id = id;
		this.number = number;
		this.term = term;
		this.year = year;
		this.courseId = courseId;
		this.roomNumber = roomNumber;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @return the term
	 */
	public int getTerm() {
		return term;
	}

	/**
	 * @return the year
	 */
	public Date getYear() {
		return year;
	}

	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
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
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @param term the term to set
	 */
	public void setTerm(int term) {
		this.term = term;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Date year) {
		this.year = year;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
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
		result = prime * result + courseId;
		result = prime * result + id;
		result = prime * result + number;
		result = prime * result + roomNumber;
		result = prime * result + term;
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		Section other = (Section) obj;
		if (courseId != other.courseId)
			return false;
		if (id != other.id)
			return false;
		if (number != other.number)
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		if (term != other.term)
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Section [" + id + ", " + number + ", " + term + ", " + year + ", courseId=" + courseId + ", roomNumber="
				+ roomNumber + "]";
	}

}
