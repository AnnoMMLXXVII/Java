package model;

public class Contact {
    private Integer contact_id;
    private String contact_name;
    private String email;

    /**
     * Empty Constructor
     */
    public Contact() {
        super();
    }

    /**
     * @param contact_id   String
     * @param contact_name String
     * @param email        String
     */
    public Contact(Integer contact_id, String contact_name, String email) {
        super();
        this.contact_id = contact_id;
        this.contact_name = contact_name;
        this.email = email;
    }

    /**
     * @return the contact_id
     */
    public Integer getContact_id() {
        return contact_id;
    }

    /**
     * @return the contact_name
     */
    public String getContact_name() {
        return contact_name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param contact_id the contact_id to set
     */
    public void setContact_id(Integer contact_id) {
        this.contact_id = contact_id;
    }

    /**
     * @param contact_name the contact_name to set
     */
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contact_id == null) ? 0 : contact_id.hashCode());
        result = prime * result + ((contact_name == null) ? 0 : contact_name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
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
        Contact other = (Contact) obj;
        if (contact_id == null) {
            if (other.contact_id != null)
                return false;
        } else if (!contact_id.equals(other.contact_id))
            return false;
        if (contact_name == null) {
            if (other.contact_name != null)
                return false;
        } else if (!contact_name.equals(other.contact_name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return contact_name;
    }

}
