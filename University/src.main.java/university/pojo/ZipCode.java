package university.pojo;

public class ZipCode {

	private String zipcode;
	private String city;
	private String stateAbbr;

	/**
	 * 
	 */
	public ZipCode() {
		super();
	}

	/**
	 * @param zipcode
	 * @param city
	 * @param stateAbbr
	 */
	public ZipCode(String zipcode, String city, String stateAbbr) {
		super();
		this.zipcode = zipcode;
		this.city = city;
		this.stateAbbr = stateAbbr;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the stateAbbr
	 */
	public String getStateAbbr() {
		return stateAbbr;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param stateAbbr the stateAbbr to set
	 */
	public void setStateAbbr(String stateAbbr) {
		this.stateAbbr = stateAbbr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((stateAbbr == null) ? 0 : stateAbbr.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
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
		ZipCode other = (ZipCode) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (stateAbbr == null) {
			if (other.stateAbbr != null)
				return false;
		} else if (!stateAbbr.equals(other.stateAbbr))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ZipCode [" + zipcode + "," + city + "," + stateAbbr + "]";
	}

}
