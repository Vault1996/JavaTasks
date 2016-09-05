package by.epam.cinemarating.command;

import by.epam.cinemarating.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ShowBanCommand implements ActionCommand {
	private static final String USER_ID = "userId";
	private static final String PAGE_BAN = "path.page.ban";
	private static final String ACTIVE_USER = "activeUser";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		//User activeUser = (User) request.getSession().getAttribute(ACTIVE_USER);
		//long userId = activeUser.getUserId();
		request.getSession().invalidate();
		//request.setAttribute(USER_ID, userId);
		return ConfigurationManager.getProperty(PAGE_BAN);
	}
}
