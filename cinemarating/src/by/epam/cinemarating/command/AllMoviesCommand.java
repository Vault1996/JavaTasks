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
	@Override
	public String execute(HttpServletRequest request) throws CommandException{
		try {
			String sortBy = request.getParameter("sortBy");
			AllMoviesLogic allMoviesLogic = new AllMoviesLogic();
			List<Movie> movies = allMoviesLogic.getAllMovies(sortBy);
			request.setAttribute("movies", movies);
			request.getSession().setAttribute("pageNumber", 0);
			request.setAttribute("sortBy", sortBy);

			MementoRequestAttributes memento = (MementoRequestAttributes) request.getSession().getAttribute("memento");
			if (memento == null) {
				memento = new MementoRequestAttributes();
			}
			Caretaker caretaker = new Caretaker(memento);
			caretaker.extractToMemento(request);
			request.getSession().setAttribute("memento", memento);

			return ConfigurationManager.getProperty("path.page.allMovies");
		} catch (LogicException e) {
			throw new CommandException(e);
		}

	}
}
