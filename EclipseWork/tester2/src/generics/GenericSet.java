package generics;

import java.util.List;

public class GenericSet<T> {
	
	private int integer;
	private String string;
	private List<T> listOfElements;
	
	/**
	 * @param integer
	 * @param string
	 * @param listOfElements
	 */
	public GenericSet(int integer, String string, List<T> listOfElements) {
		this.integer = integer;
		this.string = string;
		this.listOfElements = listOfElements;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + integer;
		result = prime * result + ((listOfElements == null) ? 0 : listOfElements.hashCode());
		result = prime * result + ((string == null) ? 0 : string.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof GenericSet))
			return false;
		GenericSet<?> other = (GenericSet<?>) obj;
		if (integer != other.integer)
			return false;
		if (listOfElements == null) {
			if (other.listOfElements != null)
				return false;
		} else if (!listOfElements.equals(other.listOfElements))
			return false;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		return true;
	}

	

}
