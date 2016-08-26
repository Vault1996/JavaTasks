package by.epam.cinemarating.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements ActionCommand{
	private static final String LANGUAGE = "language";
	private static final String PAGE = "page";

	private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		String language = request.getParameter(LANGUAGE);
		request.getSession().setAttribute(LANGUAGE, language);
		return request.getSession().getAttribute(PAGE).toString();
	}
}
