package by.epam.cinemarating.command;

import by.epam.cinemarating.entity.Movie;
import by.epam.cinemarating.logic.AllMoviesLogic;
import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.memento.Caretaker;
import by.epam.cinemarating.memento.MementoRequestAttributes;
import by.epam.cinemarating.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllMoviesCommand implements ActionCommand {
	private static final String MOVIES = "movies";
	private static final String SORT_BY = "sortBy";
	private static final String PAGE_NUMBER = "pageNumber";
	private static final String MEMENTO = "memento";
	private static final String PAGE_ALL_MOVIES = "path.page.allMovies";

	@Override
	public String execute(HttpServletRequest request) throws CommandException{
		try {
			String sortBy = request.getParameter(SORT_BY);
			AllMoviesLogic allMoviesLogic = new AllMoviesLogic();
			List<Movie> movies = allMoviesLogic.getAllMovies(sortBy);
			request.setAttribute(MOVIES, movies);
			request.getSession().setAttribute(PAGE_NUMBER, 0);
			request.setAttribute(SORT_BY, sortBy);

			MementoRequestAttributes memento = (MementoRequestAttributes) request.getSession().getAttribute(MEMENTO);
			if (memento == null) {
				memento = new MementoRequestAttributes();
			}
			Caretaker caretaker = new Caretaker(memento);
			caretaker.extractToMemento(request);
			request.getSession().setAttribute(MEMENTO, memento);

			return ConfigurationManager.getProperty(PAGE_ALL_MOVIES);
		} catch (LogicException e) {
			throw new CommandException(e);
		}

	}
}
