package by.epam.cinemarating.command;

import by.epam.cinemarating.entity.Role;
import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.logic.LoginLogic;
import by.epam.cinemarating.resource.ConfigurationManager;
import by.epam.cinemarating.resource.MessageManager;
import by.epam.cinemarating.validation.AuthenticationValidator;
import javafx.util.Pair;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String ROLE = "role";
	private static final String LANGUAGE = "language";
	private static final String RUSSIAN_LANGUAGE = "ru_ru";


	@Override
	public String execute(HttpServletRequest request) throws CommandException{
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
			request.setAttribute(LOGIN, login);
			request.setAttribute(PASSWORD, password);
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
				request.setAttribute(LOGIN, login);
				request.setAttribute(PASSWORD, password);
				request.setAttribute("errorLoginPassMessage", messageManager.getProperty("message.errorPassword"));
				return ConfigurationManager.getProperty("path.page.login");
			}
		} catch (LogicException e) {
			throw new CommandException(e);
		}
	}
}
