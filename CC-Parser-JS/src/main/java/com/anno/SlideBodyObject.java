package main.java.com.anno;

import java.util.List;

public class SlideBodyObject {

	private List<SlideLine> body;

	public SlideBodyObject(List<SlideLine> body) {
		this.body = body;
	}

	public List<SlideLine> getBody() {
		return body;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
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
		SlideBodyObject other = (SlideBodyObject) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		return true;
	}

	public String toString() {
		String str = "";
		for (SlideLine s : body) {
			str.concat(String.format("%s\n", s.toString()));
		}
		return str;
	}

}
