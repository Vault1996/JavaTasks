package by.epam.cinemarating.command;

import by.epam.cinemarating.entity.Movie;
import by.epam.cinemarating.entity.ReviewAndRatingInfo;
import by.epam.cinemarating.entity.User;
import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.logic.MovieLogic;
import by.epam.cinemarating.logic.ReviewLogic;
import by.epam.cinemarating.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowMovieCommand implements ActionCommand {
	private static final String MOVIE_ID = "movie_id";
	private static final String MOVIE = "movie";

	private static final String PAGE_MOVIE = "path.page.movie";
	private static final String REVIEWS = "reviews";

	private static final String ERROR_MESSAGE = "Problem in Show Movie Command";
	private static final String USER = "activeUser";
	private static final String ACTIVE_USER_REVIEW = "activeUserReview";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		try {
			long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
			MovieLogic movieLogic = new MovieLogic();
			Movie movie = movieLogic.findMovieById(movieId);
			ReviewLogic reviewLogic = new ReviewLogic();
			List<ReviewAndRatingInfo> reviews = new ArrayList<>();
			ReviewAndRatingInfo activeUserReview = new ReviewAndRatingInfo();
			User activeUser = (User) request.getSession().getAttribute(USER);
			reviewLogic.getReviewsOfMovie(movieId, activeUser, reviews, activeUserReview);
			request.setAttribute(MOVIE, movie);
			request.setAttribute(ACTIVE_USER_REVIEW, activeUserReview);
			request.setAttribute(REVIEWS, reviews);

			return ConfigurationManager.getProperty(PAGE_MOVIE);
		} catch (LogicException e) {
			throw new CommandException(ERROR_MESSAGE, e);
		}
	}
}
