package main.java.inheritance.student.children;

public class Customers {

	private String customer_id;
	private String contact_name;
	private String address;
	private String postal_code;
	private String phone;
	private String create_date;
	private String create_by;
	private String last_update;
	private String last_updated;
	private String division;

	/**
	 * Empty Constructor
	 */
	public Customers() {
		super();
	}

	/**
	 * @param customer_id
	 * @param contact_name
	 * @param address
	 * @param postal_code
	 * @param phone
	 * @param create_date
	 * @param create_by
	 * @param last_update
	 * @param last_updated
	 * @param division
	 */
	public Customers(String customer_id, String contact_name, String address, String postal_code, String phone,
			String create_date, String create_by, String last_update, String last_updated, String division) {
		super();
		this.customer_id = customer_id;
		this.contact_name = contact_name;
		this.address = address;
		this.postal_code = postal_code;
		this.phone = phone;
		this.create_date = create_date;
		this.create_by = create_by;
		this.last_update = last_update;
		this.last_updated = last_updated;
		this.division = division;
	}

	/**
	 * @return the customer_id
	 */
	public String getCustomer_id() {
		return customer_id;
	}

	/**
	 * @return the contact_name
	 */
	public String getContact_name() {
		return contact_name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the postal_code
	 */
	public String getPostal_code() {
		return postal_code;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
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
	 * @return the division
	 */
	public String getDivision() {
		return division;
	}

	/**
	 * @param customer_id the customer_id to set
	 */
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	/**
	 * @param contact_name the contact_name to set
	 */
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param postal_code the postal_code to set
	 */
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @param division the division to set
	 */
	public void setDivision(String division) {
		this.division = division;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((contact_name == null) ? 0 : contact_name.hashCode());
		result = prime * result + ((create_by == null) ? 0 : create_by.hashCode());
		result = prime * result + ((create_date == null) ? 0 : create_date.hashCode());
		result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
		result = prime * result + ((division == null) ? 0 : division.hashCode());
		result = prime * result + ((last_update == null) ? 0 : last_update.hashCode());
		result = prime * result + ((last_updated == null) ? 0 : last_updated.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((postal_code == null) ? 0 : postal_code.hashCode());
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
		Customers other = (Customers) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (contact_name == null) {
			if (other.contact_name != null)
				return false;
		} else if (!contact_name.equals(other.contact_name))
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
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
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
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (postal_code == null) {
			if (other.postal_code != null)
				return false;
		} else if (!postal_code.equals(other.postal_code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customers [customer_id=" + customer_id + ", contact_name=" + contact_name + ", address=" + address
				+ ", postal_code=" + postal_code + ", phone=" + phone + ", create_date=" + create_date + ", create_by="
				+ create_by + ", last_update=" + last_update + ", last_updated=" + last_updated + ", division="
				+ division + "]";
	}

}
