package by.epam.cinemarating.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
	RUSSIAN_MESSAGE(ResourceBundle.getBundle(MessageManagerConstantKeeper.LANGUAGE_RESOURCE,
			new Locale(MessageManagerConstantKeeper.RUSSIAN_LANGUAGE, MessageManagerConstantKeeper.RUSSIAN_COUNTRY))),
	ENGLISH_MESSAGE(ResourceBundle.getBundle(MessageManagerConstantKeeper.LANGUAGE_RESOURCE,
			new Locale(MessageManagerConstantKeeper.ENGLISH_LOCALE)));

	private ResourceBundle resourceBundle;
	// класс извлекает информацию из файла lang.properties
	MessageManager(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}
	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
