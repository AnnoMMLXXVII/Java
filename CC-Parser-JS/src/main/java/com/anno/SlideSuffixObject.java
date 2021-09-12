package main.java.com.anno;

import java.util.List;

public class SlideSuffixObject {
	private List<SlideLine> suffix;

	public SlideSuffixObject(List<SlideLine> suffix) {
		this.suffix = suffix;
	}

	public List<SlideLine> getsuffix() {
		return suffix;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
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
		SlideSuffixObject other = (SlideSuffixObject) obj;
		if (suffix == null) {
			if (other.suffix != null)
				return false;
		} else if (!suffix.equals(other.suffix))
			return false;
		return true;
	}

	public String toString() {
		String str = "";
		for (SlideLine s : suffix) {
			str.concat(String.format("%s\n", s.toString()));
		}
		return str;
	}
}
