package by.epam.cinemarating.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand implements ActionCommand{

	private static final String LANGUAGE = "language";
	private static final String PAGE = "page";
	private static final String MEMENTO = "memento";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getSession().getAttribute(PAGE).toString();
		String language = request.getParameter(LANGUAGE);
		request.getSession().setAttribute(LANGUAGE, language);
		return page;
	}
}
