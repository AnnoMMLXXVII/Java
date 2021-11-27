package model;

public class Customer {
    private Integer customer_id;
    private String customer_name;
    private String address;
    private String postal_code;
    private String phone;
    private String create_date;
    private String create_by;
    private String last_update;
    private String last_updated_by;
    private Integer division_id;

    /**
     * Empty Constructor
     */
    public Customer() {
        super();
    }

    /**
     * @param customer_id  String
     * @param customer_name String
     * @param address      String
     * @param postal_code  String
     * @param phone        String
     * @param create_date  String
     * @param create_by    String
     * @param last_update  String
     * @param last_updated_by String
     * @param division_id  String
     */
    public Customer(Integer customer_id, String customer_name, String address, String postal_code, String phone,
                    String create_date, String create_by, String last_update, String last_updated_by, Integer division_id) {
        super();
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        this.postal_code = postal_code;
        this.phone = phone;
        this.create_date = create_date;
        this.create_by = create_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.division_id = division_id;
    }

    /**
     * @return the customer_id
     */
    public Integer getCustomer_id() {
        return customer_id;
    }

    /**
     * @return the customer_name
     */
    public String getCustomer_name() {
        return customer_name;
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
     * @return the last_updated_by
     */
    public String getLast_updated_by() {
        return last_updated_by;
    }

    /**
     * @return the division_id
     */
    public Integer getDivision_id() {
        return division_id;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * @param customer_name the customer_name to set
     */
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
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
    public void setLast_update_by(String last_update) {
        this.last_update = last_update;
    }

    /**
     * @param last_updated_by the last_updated_by to set
     */
    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    /**
     * @param division_id the division_id to set
     */
    public void setDivision_id(Integer division_id) {
        this.division_id = division_id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((customer_name == null) ? 0 : customer_name.hashCode());
        result = prime * result + ((create_by == null) ? 0 : create_by.hashCode());
        result = prime * result + ((create_date == null) ? 0 : create_date.hashCode());
        result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
        result = prime * result + ((division_id == null) ? 0 : division_id.hashCode());
        result = prime * result + ((last_update == null) ? 0 : last_update.hashCode());
        result = prime * result + ((last_updated_by == null) ? 0 : last_updated_by.hashCode());
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
        Customer other = (Customer) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (customer_name == null) {
            if (other.customer_name != null)
                return false;
        } else if (!customer_name.equals(other.customer_name))
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
        if (division_id == null) {
            if (other.division_id != null)
                return false;
        } else if (!division_id.equals(other.division_id))
            return false;
        if (last_update == null) {
            if (other.last_update != null)
                return false;
        } else if (!last_update.equals(other.last_update))
            return false;
        if (last_updated_by == null) {
            if (other.last_updated_by != null)
                return false;
        } else if (!last_updated_by.equals(other.last_updated_by))
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
        return customer_name;
    }

}
