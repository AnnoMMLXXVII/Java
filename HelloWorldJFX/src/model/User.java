package model;

/**
 *
 */
public class User {

    public int user_id;
    public String user_name;
    public String password;
    public String create_date;
    public String created_by;
    public String last_update;
    public String last_update_by;

    /**
     * @param user_id int
     * @param user_name String
     * @param password String
     * @param create_date String
     * @param created_by String
     * @param last_update String
     * @param last_update_by String
     */
    public User(int user_id, String user_name, String password, String create_date, String created_by,
                String last_update, String last_update_by) {
        super();
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_update_by = last_update_by;
    }
    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }
    /**
     * @return the user_name
     */
    public String getUser_name() {
        return user_name;
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
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
     * @return the last_update_by
     */
    public String getLast_update_by() {
        return last_update_by;
    }
    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    /**
     * @param user_name the user_name to set
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @param last_update_by the last_update_by to set
     */
    public void setLast_update_by(String last_update_by) {
        this.last_update_by = last_update_by;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((create_date == null) ? 0 : create_date.hashCode());
        result = prime * result + ((created_by == null) ? 0 : created_by.hashCode());
        result = prime * result + ((last_update == null) ? 0 : last_update.hashCode());
        result = prime * result + ((last_update_by == null) ? 0 : last_update_by.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + user_id;
        result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
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
        User other = (User) obj;
        if (create_date == null) {
            if (other.create_date != null)
                return false;
        } else if (!create_date.equals(other.create_date))
            return false;
        if (created_by == null) {
            if (other.created_by != null)
                return false;
        } else if (!created_by.equals(other.created_by))
            return false;
        if (last_update == null) {
            if (other.last_update != null)
                return false;
        } else if (!last_update.equals(other.last_update))
            return false;
        if (last_update_by == null) {
            if (other.last_update_by != null)
                return false;
        } else if (!last_update_by.equals(other.last_update_by))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (user_id != other.user_id)
            return false;
        if (user_name == null) {
            if (other.user_name != null)
                return false;
        } else if (!user_name.equals(other.user_name))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Users [user_id=" + user_id + ", user_name=" + user_name + ", password=" + password + ", create_date="
                + create_date + ", created_by=" + created_by + ", last_update=" + last_update + ", last_update_by="
                + last_update_by + "]";
    }
}
