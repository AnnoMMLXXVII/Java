package main.java.inheritance.student.children;

public class Appointments {

	private int appointments_id;
	private String title;
	private String description;
	private String location;
	private String type;
	private String start;
	private String end;
	private String create_date;
	private String create_by;
	private String last_update;
	private String last_updated;
	private String customer_id;
	private String user_id;
	private String contact_id;

	/**
	 * Empty Constructor
	 */
	public Appointments() {
		super();
	}

	/**
	 * @param appointments_id
	 * @param title
	 * @param description
	 * @param location
	 * @param type
	 * @param start
	 * @param end
	 * @param create_date
	 * @param create_by
	 * @param last_update
	 * @param last_updated
	 * @param customer_id
	 * @param user_id
	 * @param contact_id
	 */
	public Appointments(int appointments_id, String title, String description, String location, String type,
			String start, String end, String create_date, String create_by, String last_update, String last_updated,
			String customer_id, String user_id, String contact_id) {
		super();
		this.appointments_id = appointments_id;
		this.title = title;
		this.description = description;
		this.location = location;
		this.type = type;
		this.start = start;
		this.end = end;
		this.create_date = create_date;
		this.create_by = create_by;
		this.last_update = last_update;
		this.last_updated = last_updated;
		this.customer_id = customer_id;
		this.user_id = user_id;
		this.contact_id = contact_id;
	}

	/**
	 * @return the appointments_id
	 */
	public int getAppointments_id() {
		return appointments_id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * @return the create_date
	 */
	public String getCreate_date() {
		return create_date;
	}

	/**
	 * @return the create_by
	 */
	public String getCreate_by() {
		return create_by;
	}

	/**
	 * @return the last_update
	 */
	public String getLast_update() {
		return last_update;
	}

	/**
	 * @return the last_updated
	 */
	public String getLast_updated() {
		return last_updated;
	}

	/**
	 * @return the customer_id
	 */
	public String getCustomer_id() {
		return customer_id;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @return the contact_id
	 */
	public String getContact_id() {
		return contact_id;
	}

	/**
	 * @param appointments_id the appointments_id to set
	 */
	public void setAppointments_id(int appointments_id) {
		this.appointments_id = appointments_id;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * @param create_date the create_date to set
	 */
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	/**
	 * @param create_by the create_by to set
	 */
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	/**
	 * @param last_update the last_update to set
	 */
	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

	/**
	 * @param last_updated the last_updated to set
	 */
	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}

	/**
	 * @param customer_id the customer_id to set
	 */
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @param contact_id the contact_id to set
	 */
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + appointments_id;
		result = prime * result + ((contact_id == null) ? 0 : contact_id.hashCode());
		result = prime * result + ((create_by == null) ? 0 : create_by.hashCode());
		result = prime * result + ((create_date == null) ? 0 : create_date.hashCode());
		result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((last_update == null) ? 0 : last_update.hashCode());
		result = prime * result + ((last_updated == null) ? 0 : last_updated.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
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
		Appointments other = (Appointments) obj;
		if (appointments_id != other.appointments_id)
			return false;
		if (contact_id == null) {
			if (other.contact_id != null)
				return false;
		} else if (!contact_id.equals(other.contact_id))
			return false;
		if (create_by == null) {
			if (other.create_by != null)
				return false;
		} else if (!create_by.equals(other.create_by))
			return false;
		if (create_date == null) {
			if (other.create_date != null)
				return false;
		} else if (!create_date.equals(other.create_date))
			return false;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (last_update == null) {
			if (other.last_update != null)
				return false;
		} else if (!last_update.equals(other.last_update))
			return false;
		if (last_updated == null) {
			if (other.last_updated != null)
				return false;
		} else if (!last_updated.equals(other.last_updated))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Appointments [appointments_id=" + appointments_id + ", title=" + title + ", description=" + description
				+ ", location=" + location + ", type=" + type + ", start=" + start + ", end=" + end + ", create_date="
				+ create_date + ", create_by=" + create_by + ", last_update=" + last_update + ", last_updated="
				+ last_updated + ", customer_id=" + customer_id + ", user_id=" + user_id + ", contact_id=" + contact_id
				+ "]";
	}

}
