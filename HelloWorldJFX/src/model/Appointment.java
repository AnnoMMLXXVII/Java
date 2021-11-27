package model;

public class Appointment {
    private Integer appointment_id;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private String create_date;
    private String created_by;
    private String last_update;
    private String last_updated_by;
    private Integer customer_id;
    private Integer user_id;
    private Integer contact_id;

    /**
     * Empty Constructor
     */
    public Appointment() {
        super();
    }

    /**
     * @param appointment_id int
     * @param title           String
     * @param description     String
     * @param location        String
     * @param type            String
     * @param start           String
     * @param end             String
     * @param create_date     String
     * @param created_by      String
     * @param last_update     String
     * @param last_updated_by String
     * @param customer_id     String
     * @param user_id         String
     * @param contact_id      String
     */
    public Appointment(Integer appointment_id, String title, String description, String location, String type,
                       String start, String end, String create_date, String created_by, String last_update, String last_updated_by,
                       Integer customer_id, Integer user_id, Integer contact_id) {
        super();
        this.appointment_id = appointment_id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.contact_id = contact_id;
    }

    /**
     * @return the appointment_id
     */
    public Integer getAppointment_id() {
        return appointment_id;
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
     * @return the created_by
     */
    public String getCreated_by() {
        return created_by;
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
     * @return the customer_id
     */
    public Integer getCustomer_id() {
        return customer_id;
    }

    /**
     * @return the user_id
     */
    public Integer getUser_id() {
        return user_id;
    }

    /**
     * @return the contact_id
     */
    public Integer getContact_id() {
        return contact_id;
    }

    /**
     * @param appointment_id the appointment_id to set
     */
    public void setAppointment_id(Integer appointment_id) {
        this.appointment_id = appointment_id;
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
     * @param created_by the created_by to set
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * @param last_update the last_update to set
     */
    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    /**
     * @param last_updated_by the last_updated_by to set
     */
    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    /**
     * @param contact_id the contact_id to set
     */
    public void setContact_id(Integer contact_id) {
        this.contact_id = contact_id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + appointment_id;
        result = prime * result + ((contact_id == null) ? 0 : contact_id.hashCode());
        result = prime * result + ((created_by == null) ? 0 : created_by.hashCode());
        result = prime * result + ((create_date == null) ? 0 : create_date.hashCode());
        result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + ((last_update == null) ? 0 : last_update.hashCode());
        result = prime * result + ((last_updated_by == null) ? 0 : last_updated_by.hashCode());
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
        Appointment other = (Appointment) obj;
        if (appointment_id != other.appointment_id)
            return false;
        if (contact_id == null) {
            if (other.contact_id != null)
                return false;
        } else if (!contact_id.equals(other.contact_id))
            return false;
        if (created_by == null) {
            if (other.created_by != null)
                return false;
        } else if (!created_by.equals(other.created_by))
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
        if (last_updated_by == null) {
            if (other.last_updated_by != null)
                return false;
        } else if (!last_updated_by.equals(other.last_updated_by))
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
        return "Appointments [appointment_id=" + appointment_id + ", title=" + title + ", description=" + description
                + ", location=" + location + ", type=" + type + ", start=" + start + ", end=" + end + ", create_date="
                + create_date + ", created_by=" + created_by + ", last_update=" + last_update + ", last_updated_by="
                + last_updated_by + ", customer_id=" + customer_id + ", user_id=" + user_id + ", contact_id=" + contact_id
                + "]";
    }

}
