package model;

public class Division {
    private Integer division_id;
    private String division;
    private String create_date;
    private String create_by;
    private String last_update;
    private String last_updated_by;
    private Integer country_id;

    /**
     *  Empty Constructor
     */
    public Division() {
        super();
    }
    /**
     * @param division_id Integer
     * @param division String
     * @param create_date String
     * @param create_by String
     * @param last_update String
     * @param last_updated_by String
     * @param country_id Integer
     */
    public Division(Integer division_id, String division, String create_date, String create_by,
                    String last_update, String last_updated_by, Integer country_id) {
        super();
        this.division_id = division_id;
        this.division = division;
        this.create_date = create_date;
        this.create_by = create_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.country_id = country_id;
    }
    /**
     * @return the division_id
     */
    public Integer getDivision_id() {
        return division_id;
    }
    /**
     * @return the division
     */
    public String getDivision() {
        return division;
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
     * @return the country_id
     */
    public Integer getCountry_id() {
        return country_id;
    }
    /**
     * @param division_id the division_id to set
     */
    public void setDivision_id(Integer division_id) {
        this.division_id = division_id;
    }
    /**
     * @param division the division to set
     */
    public void setDivision(String division) {
        this.division = division;
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
     * @param last_updated_by the last_updated_by to set
     */
    public void setLast_updated_by(String last_updated_by) {
        this.last_updated_by = last_updated_by;
    }
    /**
     * @param country_id the country_id to set
     */
    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((country_id == null) ? 0 : country_id.hashCode());
        result = prime * result + ((create_by == null) ? 0 : create_by.hashCode());
        result = prime * result + ((create_date == null) ? 0 : create_date.hashCode());
        result = prime * result + ((division == null) ? 0 : division.hashCode());
        result = prime * result + ((division_id == null) ? 0 : division_id.hashCode());
        result = prime * result + ((last_update == null) ? 0 : last_update.hashCode());
        result = prime * result + ((last_updated_by == null) ? 0 : last_updated_by.hashCode());
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
        Division other = (Division) obj;
        if (country_id == null) {
            if (other.country_id != null)
                return false;
        } else if (!country_id.equals(other.country_id))
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
        if (division == null) {
            if (other.division != null)
                return false;
        } else if (!division.equals(other.division))
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
        return true;
    }

    @Override
    public String toString() {
        return "FirstLevelDivisions [division_id=" + division_id + ", division=" + division + ", create_date="
                + create_date + ", create_by=" + create_by + ", last_update=" + last_update + ", last_updated_by="
                + last_updated_by + ", country_id=" + country_id + "]";
    }

}
