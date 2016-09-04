package by.epam.cinemarating.command;

import by.epam.cinemarating.entity.Movie;
import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.logic.MovieLogic;
import by.epam.cinemarating.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowMainPageCommand implements ActionCommand {
	private static final int NUMBER_OF_TOP_MOVIES = 3;
	private static final String PAGE_MAIN = "path.page.main";
	private static final String ERROR_MESSAGE = "Problem in Show Main Page Command";
	private static final String TOP_MOVIES = "topMovies";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		MovieLogic movieLogic = new MovieLogic();
		try {
			List<Movie> topMovies = movieLogic.findTopMovies(NUMBER_OF_TOP_MOVIES);
			request.setAttribute(TOP_MOVIES, topMovies);
			return ConfigurationManager.getProperty(PAGE_MAIN);
		} catch (LogicException e) {
			throw new CommandException(ERROR_MESSAGE, e);
		}
	}
}
