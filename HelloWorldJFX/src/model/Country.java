package model;

public class Country {
    private String country_id;
    private String country;
    private String create_date;
    private String create_by;
    private String last_update;
    private String last_updated_by;

    /**
     * Empty Constructor
     */
    public Country() {
        super();
    }

    /**
     * @param country_id String
     * @param country String
     * @param create_date String
     * @param create_by String
     * @param last_update String
     * @param last_updated_by String
     */
    public Country(String country_id, String country, String create_date, String create_by, String last_update,
                   String last_updated_by) {
        super();
        this.country_id = country_id;
        this.country = country;
        this.create_date = create_date;
        this.create_by = create_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
    }

    /**
     * @return the country_id
     */
    public String getCountry_id() {
        return country_id;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
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
     * @param country_id the country_id to set
     */
    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((country_id == null) ? 0 : country_id.hashCode());
        result = prime * result + ((create_by == null) ? 0 : create_by.hashCode());
        result = prime * result + ((create_date == null) ? 0 : create_date.hashCode());
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
        Country other = (Country) obj;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
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
        return "Countries [country_id=" + country_id + ", country=" + country + ", create_date=" + create_date
                + ", create_by=" + create_by + ", last_update=" + last_update + ", last_updated_by=" + last_updated_by
                + "]";
    }

}
