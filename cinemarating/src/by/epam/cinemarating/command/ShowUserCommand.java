package by.epam.cinemarating.command;

import by.epam.cinemarating.entity.User;
import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.logic.UserLogic;
import by.epam.cinemarating.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ShowUserCommand implements ActionCommand {
	private static final String USER_ID = "user_id";
	private static final String USER = "user";

	private static final String PAGE_USER = "path.page.user";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		long userId = Integer.valueOf(request.getParameter(USER_ID));
		UserLogic userLogic = new UserLogic();
		try {
			User user = userLogic.findUserById(userId);
			request.setAttribute(USER, user);
		} catch (LogicException e) {
			throw new CommandException(e);
		}
		return ConfigurationManager.getProperty(PAGE_USER);
	}
}
