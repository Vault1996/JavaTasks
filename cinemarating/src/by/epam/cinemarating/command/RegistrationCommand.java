package by.epam.cinemarating.command;

import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.logic.RegistrationLogic;
import by.epam.cinemarating.resource.ConfigurationManager;
import by.epam.cinemarating.validation.RegistrationValidator;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ActionCommand {
	private static final String LOGIN = "login";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String LANGUAGE = "language";
	private static final String RUSSIAN_LANGUAGE = "ru_ru";

	private static final String ERROR_MESSAGE = "Problem in Registration Command";
	private static final String ERROR_REGISTRATION_MESSAGE = "errorRegistrationMessage";
	private static final String PAGE_REGISTRATION = "path.page.registration";
	private static final String PAGE_LOGIN = "path.page.login";
	private static final String MESSAGE_REGISTRATION_ERROR = "message.registrationError";
	private static final String REGISTRATION_STATUS = "registrationStatus";
	private static final String MESSAGE_REGISTRATION_SUCCESSFUL = "message.registrationSuccessful";
	private static final String MESSAGE_USER_OR_EMAIL_EXISTS = "message.userOrEmailExists";
	private static final String ERROR_REGISTRATION_VALIDATION = "errorRegistrationValidation";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String login = request.getParameter(LOGIN);
		String email = request.getParameter(EMAIL);
		String name = request.getParameter(NAME);
		String surname = request.getParameter(SURNAME);
		String password = request.getParameter(PASSWORD);
		RegistrationValidator validator = new RegistrationValidator();
		if (!validator.validate(login, email, password)) {
			request.setAttribute(LOGIN, login);
			request.setAttribute(PASSWORD, password);
			request.setAttribute(NAME, name);
			request.setAttribute(SURNAME, surname);
			request.setAttribute(EMAIL, email);
			request.setAttribute(ERROR_REGISTRATION_VALIDATION, true);
			return ConfigurationManager.getProperty(PAGE_REGISTRATION);
		}
		RegistrationLogic registrationLogic = new RegistrationLogic();
		try {
			boolean flag = registrationLogic.logic(login, name, surname, email, password);
			if (flag) {
				request.setAttribute(LOGIN, login);
				request.setAttribute(PASSWORD, password);
				request.setAttribute(REGISTRATION_STATUS, true);
				return ConfigurationManager.getProperty(PAGE_LOGIN);
			} else {
				request.setAttribute(ERROR_REGISTRATION_MESSAGE, true);
				request.setAttribute(NAME, name);
				request.setAttribute(SURNAME, surname);
				request.setAttribute(EMAIL, email);
				request.setAttribute(LOGIN, login);
				request.setAttribute(PASSWORD, password);
				return ConfigurationManager.getProperty(PAGE_REGISTRATION);
			}
		} catch (LogicException e) {
			throw new CommandException(ERROR_MESSAGE, e);
		}
	}
}
