package by.epam.cinemarating.command;

import by.epam.cinemarating.memento.Caretaker;
import by.epam.cinemarating.memento.MementoRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ChangeLanguageCommand implements ActionCommand{
	private static final String LOGIN = "login";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";

	private static final String LANGUAGE = "language";
	private static final String PAGE = "page";

	private static final String MEMENTO = "memento";

	@Override
	public String execute(HttpServletRequest request) {
		String page = request.getSession().getAttribute(PAGE).toString();
		String language = request.getParameter(LANGUAGE);

		MementoRequestAttributes memento = (MementoRequestAttributes) request.getSession().getAttribute("memento");
		if (memento == null) {
			memento = new MementoRequestAttributes();
		}
		Caretaker caretaker = new Caretaker(memento);
		caretaker.fillRequest(request);
		request.getSession().setAttribute("memento", memento);

		request.getSession().setAttribute(LANGUAGE, language);
		return page;
	}
}
