package by.epam.cinemarating.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {
	RUSSIAN_MESSAGE(ResourceBundle.getBundle("lang", new Locale("ru", "RU"))),
	ENGLISH_MESSAGE(ResourceBundle.getBundle("lang", new Locale("")));

	/*
	TODO: HOW TO USE CONSTANT FOR INITIALIZING
	private final static String MESSAGE_RESOURCE = "lang";
	*/
	private ResourceBundle resourceBundle;
	// класс извлекает информацию из файла messages.properties
	private MessageManager(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}
	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
