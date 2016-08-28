package by.epam.cinemarating.command;

import by.epam.cinemarating.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class RedirectCommand implements ActionCommand {
	private static final String NEXT_PAGE = "next";
	@Override
	public String execute(HttpServletRequest request) {
		String nextPage = ConfigurationManager.getProperty(request.getParameter(NEXT_PAGE));
		return nextPage;
	}
}