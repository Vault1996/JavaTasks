package by.epam.cinemarating.command;

import by.epam.cinemarating.entity.User;
import by.epam.cinemarating.logic.AllUsersLogic;
import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.memento.Caretaker;
import by.epam.cinemarating.memento.MementoRequestAttributes;
import by.epam.cinemarating.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllUsersCommand implements ActionCommand {
	private static final String USERS = "users";
	private static final String PAGE_NUMBER = "pageNumber";
	private static final String MEMENTO = "memento";
	private static final String PAGE_ALL_USERS = "path.page.allUsers";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		try {
			AllUsersLogic allUsersLogic = new AllUsersLogic();
			List<User> users = allUsersLogic.getAllUsers();
			request.setAttribute(USERS, users);
			request.getSession().setAttribute(PAGE_NUMBER, 0);

			MementoRequestAttributes memento = (MementoRequestAttributes) request.getSession().getAttribute(MEMENTO);
			if (memento == null) {
				memento = new MementoRequestAttributes();
			}
			Caretaker caretaker = new Caretaker(memento);
			caretaker.extractToMemento(request);
			request.getSession().setAttribute(MEMENTO, memento);

			return ConfigurationManager.getProperty(PAGE_ALL_USERS);
		} catch (LogicException e) {
			throw new CommandException(e);
		}
	}
}
