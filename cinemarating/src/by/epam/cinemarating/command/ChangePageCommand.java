package by.epam.cinemarating.command;

import by.epam.cinemarating.memento.Caretaker;
import by.epam.cinemarating.memento.MementoRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ChangePageCommand implements ActionCommand {
	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		String nextPage = request.getParameter("next");

		MementoRequestAttributes memento = (MementoRequestAttributes) request.getSession().getAttribute("memento");
		Caretaker caretaker = new Caretaker(memento);
		caretaker.fillRequest(request);

		request.getSession().setAttribute("pageNumber", nextPage);

		return request.getSession().getAttribute("page").toString();
	}
}
