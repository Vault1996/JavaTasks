package by.epam.cinemarating.logic;

import by.epam.cinemarating.dao.DAOException;
import by.epam.cinemarating.dao.RatingDAO;
import by.epam.cinemarating.dao.ReviewDAO;
import by.epam.cinemarating.dao.UserDAO;
import by.epam.cinemarating.database.ConnectionPool;
import by.epam.cinemarating.database.WrapperConnection;
import by.epam.cinemarating.entity.Rating;
import by.epam.cinemarating.entity.Review;
import by.epam.cinemarating.entity.ReviewAndRatingInfo;
import by.epam.cinemarating.entity.User;

import java.util.List;

public class ReviewLogic {
	public void getReviewsOfMovie(long movieId, List<ReviewAndRatingInfo> reviews) throws LogicException{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		try {
			// TODO: нормально ли?
			ReviewDAO reviewDAO = new ReviewDAO(connection);
			List<Review> reviewList = reviewDAO.findReviewsByMovieId(movieId);
			UserDAO userDAO = new UserDAO(connection);
			RatingDAO ratingDAO = new RatingDAO(connection);
			for (Review review : reviewList) {
				User user = userDAO.findEntityById(review.getUserId()).orElse(null);
				Rating rating = ratingDAO.findRating(movieId, user.getUserId()).orElse(null);
				ReviewAndRatingInfo reviewAndRatingInfo = new ReviewAndRatingInfo(user, review, rating);
				reviews.add(reviewAndRatingInfo);
			}
		} catch (DAOException e) {
			throw new LogicException(e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}
}
