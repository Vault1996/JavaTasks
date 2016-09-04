package by.epam.cinemarating.command;

import by.epam.cinemarating.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ShowBanCommand implements ActionCommand {
	private static final String PAGE_BAN = "path.page.ban";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		request.getSession().invalidate();
		return ConfigurationManager.getProperty(PAGE_BAN);
	}
}
