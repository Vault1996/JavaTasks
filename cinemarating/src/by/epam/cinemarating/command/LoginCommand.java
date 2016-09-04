package by.epam.cinemarating.command;

import by.epam.cinemarating.entity.Ban;
import by.epam.cinemarating.entity.User;
import by.epam.cinemarating.function.TimeConverter;
import by.epam.cinemarating.logic.BanLogic;
import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.logic.LoginLogic;
import by.epam.cinemarating.resource.ConfigurationManager;
import by.epam.cinemarating.validation.AuthenticationValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Optional;

public class LoginCommand implements ActionCommand {
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String ACTIVE_USER = "activeUser";
	private static final String LANGUAGE = "language";
	private static final String BAN = "ban";

	private static final String PAGE_LOGIN = "path.page.login";
	private static final String ERROR_LOGIN_VALIDATION = "errorLoginValidation";
	private static final String SHOW_MAIN_PAGE = "/controller?command=show_main_page";
	private static final String USER_ID = "userId";
	private static final String ERROR_MESSAGE = "Problem in Login Command";
	private static final String SHOW_BAN = "/controller?command=show_ban";
	private static final String TIME_LEFT = "timeLeft";
	private static final String ERROR_LOGIN_PASSWORD = "errorLoginPassword";


	@Override
	public String execute(HttpServletRequest request) throws CommandException{
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		AuthenticationValidator validator = new AuthenticationValidator();

		if (!validator.validate(login, password)) {
			request.setAttribute(ERROR_LOGIN_VALIDATION, true);
			request.setAttribute(LOGIN, login);
			request.setAttribute(PASSWORD, password);
			return ConfigurationManager.getProperty(PAGE_LOGIN);
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
					request.setAttribute(BAN, ban);
					request.setAttribute(TIME_LEFT, TimeConverter.findDifference(ban.getTill(),
							new Timestamp(System.currentTimeMillis())));
					request.setAttribute(USER_ID, user.getUserId());
					return SHOW_BAN;
				} else {
					request.getSession().setAttribute(ACTIVE_USER, user);
					return SHOW_MAIN_PAGE;
				}
			} else {
				request.setAttribute(LOGIN, login);
				request.setAttribute(PASSWORD, password);
				request.setAttribute(ERROR_LOGIN_PASSWORD, true);
				return ConfigurationManager.getProperty(PAGE_LOGIN);
			}
		} catch (LogicException e) {
			throw new CommandException(ERROR_MESSAGE, e);
		}
	}
}
