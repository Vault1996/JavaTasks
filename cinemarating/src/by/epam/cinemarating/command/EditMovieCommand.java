package by.epam.cinemarating.command;

import by.epam.cinemarating.entity.Movie;
import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.logic.MovieLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;

public class EditMovieCommand implements ActionCommand {
	private static final String MOVIE_ID = "movie_id";
	private static final String SHOW_MOVIE_COMMAND = "/controller?command=show_movie&movie_id=";
	private static final String ERROR_MESSAGE = "Problem in Edit Movie Command";
	private static final String NAME = "name";
	private static final String YEAR = "year";
	private static final String DESCRIPTION = "description";
	private static final String COUNTRY = "country";
	private static final String POSTER = "poster";
	private static final String MOVIE_IMAGES_LOCATION = "images/movies/";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		long movieId = Long.valueOf(request.getParameter(MOVIE_ID));
		String name = request.getParameter(NAME);
		String year = request.getParameter(YEAR);
		String description = request.getParameter(DESCRIPTION);
		String country = request.getParameter(COUNTRY);
		try{
			MovieLogic movieLogic = new MovieLogic();
			Movie currentMovie = movieLogic.findMovieById(movieId);
			Part posterPart = request.getPart(POSTER);
			String fileName = Paths.get(posterPart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			if (name != null && !name.isEmpty()) {
				currentMovie.setName(name);
			}
			if (year != null && !year.isEmpty()) {
				currentMovie.setYear(Integer.valueOf(year));
			}
			if (description != null && !description.isEmpty()) {
				currentMovie.setDescription(description);
			}
			if (country != null && !country.isEmpty()) {
				currentMovie.setCountry(country);
			}
			if (posterPart.getSize() != 0) {
				currentMovie.setPoster(MOVIE_IMAGES_LOCATION + fileName);
				posterPart.write(request.getServletContext().getRealPath("") + MOVIE_IMAGES_LOCATION + fileName);
			}
			movieLogic.editMovie(currentMovie);
		} catch (ServletException | IOException | LogicException e) {
			throw new CommandException(ERROR_MESSAGE, e);
		}
		return SHOW_MOVIE_COMMAND + movieId;
	}
}
