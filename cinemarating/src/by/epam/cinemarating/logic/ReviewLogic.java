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
import java.util.Optional;

public class ReviewLogic {
	private static final String ERROR_MESSAGE = "Problem in Review Logic";

	public void getReviewsOfMovie(long movieId, User activeUser,
								  List<ReviewAndRatingInfo> reviews, ReviewAndRatingInfo activeUserReview) throws LogicException{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		try {
			ReviewDAO reviewDAO = new ReviewDAO(connection);
			UserDAO userDAO = new UserDAO(connection);
			RatingDAO ratingDAO = new RatingDAO(connection);
			List<Rating> ratingList = ratingDAO.findRatingsByMovieId(movieId);
			ratingList.sort((o1, o2)-> o2.getTime().compareTo(o1.getTime()));
			for (Rating rating : ratingList) {
				User user = userDAO.findEntityById(rating.getUserId()).orElse(null);
				Review review = reviewDAO.findReview(movieId, user.getUserId()).orElse(null);
				ReviewAndRatingInfo reviewAndRatingInfo = new ReviewAndRatingInfo(user, review, rating);
				reviews.add(reviewAndRatingInfo);
			}
			Optional<Rating> userRatingOptional = ratingDAO.findRating(movieId, activeUser.getUserId());
			if (userRatingOptional.isPresent()) {
				Review review = reviewDAO.findReview(movieId, activeUser.getUserId()).orElse(null);
				activeUserReview.setUser(activeUser);
				activeUserReview.setRating(userRatingOptional.get());
				activeUserReview.setReview(review);
			}
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}
	public boolean leaveReview(long movieId, long userId, int rating, String review) throws LogicException{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		try {
			RatingDAO ratingDAO = new RatingDAO(connection);
			ReviewDAO reviewDAO = new ReviewDAO(connection);
			Optional<Rating> ratingOptional = ratingDAO.findRating(movieId, userId);
			if (ratingOptional.isPresent()) {
				return false;
			} else {
				ratingDAO.insert(new Rating(movieId, userId, rating, null));
				reviewDAO.insert(new Review(movieId, userId, review, null));
				return true;
			}
		} catch (DAOException e) {
			throw new LogicException(e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}

	public void deleteReview(long movieId, long userId) throws LogicException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		RatingDAO ratingDAO = new RatingDAO(connection);
		ReviewDAO reviewDAO = new ReviewDAO(connection);
		try {
			ratingDAO.deleteRating(movieId, userId);
			reviewDAO.deleteReview(movieId, userId);
		} catch (DAOException e) {
			throw new LogicException(e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}

	public ReviewAndRatingInfo getReviewAndRating(long movieId, long userId) throws LogicException{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		try {
			ReviewDAO reviewDAO = new ReviewDAO(connection);
			RatingDAO ratingDAO = new RatingDAO(connection);
			UserDAO userDAO = new UserDAO(connection);
			Optional<Rating> ratingOptional = ratingDAO.findRating(movieId, userId);
			ReviewAndRatingInfo reviewAndRatingInfo = new ReviewAndRatingInfo();
			if (ratingOptional.isPresent()) {
				Review review = reviewDAO.findReview(movieId, userId).orElse(null);
				User user = userDAO.findEntityById(userId).orElse(new User());
				reviewAndRatingInfo.setUser(user);
				reviewAndRatingInfo.setRating(ratingOptional.get());
				reviewAndRatingInfo.setReview(review);
			}
			return reviewAndRatingInfo;
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}

	public void editReview(long movieId, long userId, int rating, String review) throws LogicException{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		try {
			ReviewDAO reviewDAO = new ReviewDAO(connection);
			RatingDAO ratingDAO = new RatingDAO(connection);
			reviewDAO.update(new Review(movieId, userId, review, null));
			ratingDAO.update(new Rating(movieId, userId, rating, null));
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}

}
