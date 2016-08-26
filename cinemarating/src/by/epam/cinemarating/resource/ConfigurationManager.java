package by.epam.cinemarating.resource;

import java.util.ResourceBundle;

public class ConfigurationManager {
	private static final String CONFIGURATION_RESOURCE = "config";
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(CONFIGURATION_RESOURCE);
	// класс извлекает информацию из файла config.properties
	private ConfigurationManager() { }
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
