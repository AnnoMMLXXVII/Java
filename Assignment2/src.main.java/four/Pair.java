package four;

public class Pair<F, S> {

	private F key;
	private S value;

	/**
	 * 
	 */
	public Pair() {
		super();
	}

	/**
	 * @param key
	 * @param value
	 */
	public Pair(F key, S value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public F getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public S getValue() {
		return value;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(F key) {
		this.key = key;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(S value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Pair<?, ?> other = (Pair<?, ?>) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Pair[{%s}-key=%s, {%s}-value=%s]", getKey().getClass().getSimpleName(), getKey().toString(),
				getValue().getClass().getSimpleName(), getValue().toString());
	}

}
