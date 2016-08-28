package by.epam.cinemarating.memento;

import java.util.HashMap;
import java.util.Map;

public class MementoRequestAttributes {
	private Map<String, Object> attributes = new HashMap<>();

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
}
