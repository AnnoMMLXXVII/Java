package main.java.com.anno;

import java.util.List;

public class SlidePrefixObject {

	private List<SlideLine> prefix;

	public SlidePrefixObject(List<SlideLine> prefix) {
		this.prefix = prefix;
	}

	public List<SlideLine> getprefix() {
		return prefix;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
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
		SlidePrefixObject other = (SlidePrefixObject) obj;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		return true;
	}

	public String toString() {
		String str = "";
		for (SlideLine s : prefix) {
			str.concat(String.format("%s\n", s.toString()));
		}
		return str;
	}
}
