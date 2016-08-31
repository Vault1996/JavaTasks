package by.epam.cinemarating.command;

import by.epam.cinemarating.entity.Ban;
import by.epam.cinemarating.entity.User;
import by.epam.cinemarating.logic.BanLogic;
import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.logic.LoginLogic;
import by.epam.cinemarating.resource.ConfigurationManager;
import by.epam.cinemarating.resource.MessageManager;
import by.epam.cinemarating.validation.AuthenticationValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class LoginCommand implements ActionCommand {
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String ACTIVE_USER = "activeUser";
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
			User user = new User();
			boolean flag = loginLogic.logic(login, password, user);
			if (flag) {
				BanLogic logic = new BanLogic();
				Optional<Ban> banOptional = logic.findBan(user.getUserId());
				if (banOptional.isPresent()) {
					Ban ban = banOptional.get();
					request.setAttribute("ban", ban);
					return ConfigurationManager.getProperty("path.page.login");
				} else {
					request.getSession().setAttribute(ACTIVE_USER, user);
					return ConfigurationManager.getProperty("path.page.main");
				}
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
