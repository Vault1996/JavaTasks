package by.epam.cinemarating.command;

import by.epam.cinemarating.entity.ReviewAndRatingInfo;
import by.epam.cinemarating.logic.LogicException;
import by.epam.cinemarating.logic.ReviewLogic;
import by.epam.cinemarating.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ShowEditReviewCommand implements ActionCommand{
	private static final String PAGE_EDIT_REVIEW = "path.page.editReview";
	private static final String MOVIE_ID = "movie_id";
	private static final String USER_ID = "user_id";
	private static final String REVIEW = "review";
	private static final String ERROR_MESSAGE = "Problem in Show Edit Review Command";

	@Override
	public String execute(HttpServletRequest request) throws CommandException {
		long movieId = Long.valueOf(request.getParameter(MOVIE_ID));
		long userId = Long.valueOf(request.getParameter(USER_ID));
		ReviewLogic reviewLogic = new ReviewLogic();
		ReviewAndRatingInfo reviewAndRatingInfo;
		try {
			reviewAndRatingInfo = reviewLogic.getReviewAndRating(movieId, userId);
		} catch (LogicException e) {
			throw new CommandException(ERROR_MESSAGE, e);
		}
		request.setAttribute(REVIEW, reviewAndRatingInfo);
		return ConfigurationManager.getProperty(PAGE_EDIT_REVIEW);
	}
}
