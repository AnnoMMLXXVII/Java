package main.java;

public class SlideJSObject {

	private SlidePrefixObject prefix;
	private SlideBodyObject body;
	private SlideSuffixObject suffix;

	public SlideJSObject() {

	}

	public SlidePrefixObject getPrefix() {
		return prefix;
	}

	public SlideBodyObject getBody() {
		return body;
	}

	public SlideSuffixObject getSuffix() {
		return suffix;
	}

	public void setPrefix(SlidePrefixObject prefix) {
		this.prefix = prefix;
	}

	public void setBody(SlideBodyObject body) {
		this.body = body;
	}

	public void setSuffix(SlideSuffixObject suffix) {
		this.suffix = suffix;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
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
		SlideJSObject other = (SlideJSObject) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (suffix == null) {
			if (other.suffix != null)
				return false;
		} else if (!suffix.equals(other.suffix))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SlideJSObject [prefix=" + prefix + ", body=" + body + ", suffix=" + suffix + "]";
	}


	
	
}