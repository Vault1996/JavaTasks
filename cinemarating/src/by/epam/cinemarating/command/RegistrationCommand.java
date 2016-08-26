package by.epam.cinemarating.command;

import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.logic.RegistrationLogic;
import by.epam.cinemarating.resource.ConfigurationManager;
import by.epam.cinemarating.resource.MessageManager;
import by.epam.cinemarating.validation.RegistrationValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ActionCommand {
	private static final String LOGIN = "login";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";
	private static final String LANGUAGE = "language";
	private static final String RUSSIAN_LANGUAGE = "ru_ru";

	private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String login = request.getParameter(LOGIN);
		String email = request.getParameter(EMAIL);
		String name = request.getParameter(NAME);
		String surname = request.getParameter(SURNAME);
		String password = request.getParameter(PASSWORD);
		RegistrationValidator validator = new RegistrationValidator();
		//
		MessageManager messageManager;
		if (RUSSIAN_LANGUAGE.equalsIgnoreCase(request.getSession().getAttribute(LANGUAGE).toString())) {
			messageManager = MessageManager.RUSSIAN_MESSAGE;
		} else {
			messageManager = MessageManager.ENGLISH_MESSAGE;
		}
		//
		if (!validator.validate(login, email, password)) {
			request.setAttribute("errorRegistrationMessage", messageManager.getProperty("message.registrationError"));
			return ConfigurationManager.getProperty("path.page.registration");
		}
		RegistrationLogic registrationLogic = new RegistrationLogic();
		try {
			boolean flag = registrationLogic.logic(login, name, surname, email, password);
			if (flag) {
				request.setAttribute("registrationStatus", messageManager.getProperty("message.registrationSuccessful"));
				return ConfigurationManager.getProperty("path.page.login");
			} else {
				request.setAttribute("errorRegistrationMessage", messageManager.getProperty("message.userOrEmailExists"));
				return ConfigurationManager.getProperty("path.page.registration");
			}
		} catch (LogicException e) {
			LOGGER.error(e);
			// throw new CommandException();
		}
		return "";
	}
}
