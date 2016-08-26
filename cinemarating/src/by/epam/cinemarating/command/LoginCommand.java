package by.epam.cinemarating.command;

import by.epam.cinemarating.entity.Role;
import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.logic.LoginLogic;
import by.epam.cinemarating.resource.ConfigurationManager;
import by.epam.cinemarating.resource.MessageManager;
import by.epam.cinemarating.validation.AuthenticationValidator;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String ROLE = "role";
	private static final String LANGUAGE = "language";
	private static final String RUSSIAN_LANGUAGE = "ru_ru";

	private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);


	@Override
	public String execute(HttpServletRequest request) {
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		AuthenticationValidator validator = new AuthenticationValidator();
		//
		MessageManager messageManager;
		if (RUSSIAN_LANGUAGE.equalsIgnoreCase(request.getSession().getAttribute(LANGUAGE).toString())) {
			messageManager = MessageManager.RUSSIAN_MESSAGE;
		} else {
			messageManager = MessageManager.ENGLISH_MESSAGE;
		}
		//
		if (!validator.validate(login, password)) {
			request.setAttribute("errorLoginPassMessage", messageManager.getProperty("message.errorPasswordOrLogin"));
			return ConfigurationManager.getProperty("path.page.login");
		}
		LoginLogic loginLogic = new LoginLogic();
		try {
			Pair<Boolean, Role> result = loginLogic.logic(login, password);
			boolean flag = result.getKey();
			if (flag) {
				request.getSession().setAttribute(LOGIN, login);
				request.getSession().setAttribute(ROLE, result.getValue().toString());
				return ConfigurationManager.getProperty("path.page.main");
			} else {
				request.setAttribute("errorLoginPassMessage", messageManager.getProperty("message.errorPassword"));
				return ConfigurationManager.getProperty("path.page.login");
			}
		} catch (LogicException e) {
			LOGGER.error(e);
			//			throw new CommandException();
		}
		return "";
	}
}
