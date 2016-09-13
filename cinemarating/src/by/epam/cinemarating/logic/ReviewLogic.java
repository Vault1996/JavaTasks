package by.epam.cinemarating.logic;

import by.epam.cinemarating.dao.*;
import by.epam.cinemarating.database.ConnectionPool;
import by.epam.cinemarating.database.WrapperConnection;
import by.epam.cinemarating.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ReviewLogic {
	private static final String ERROR_MESSAGE = "Problem in Review Logic";
	private static final long SKIP_NUMBER_OF_USERS = 3;
	private static final double EPS = 2.5;
	private static final int ADD_STATUS = 3;

	private static final Logger LOGGER = LogManager.getLogger(ReviewLogic.class);

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
				updateRating(movieId);
				updateStatus(movieId);
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
			updateRating(movieId);
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
			updateRating(movieId);
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}

	private void updateRating(long movieId) throws LogicException{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		try {
			RatingDAO ratingDAO = new RatingDAO(connection);
			MovieDAO movieDAO = new MovieDAO(connection);
			Movie movie = movieDAO.findEntityById(movieId).orElse(new Movie());
			List<Rating> ratingList = ratingDAO.findRatingsByMovieId(movieId);
			if (!ratingList.isEmpty()) {
				int size = ratingList.size();
				int average = 0;
				for (Rating rating1 : ratingList) {
					average += rating1.getRating();
				}
				movie.setRating((double) average / size);
			} else {
				movie.setRating(0.0);
			}
			movieDAO.update(movie);
		} catch (DAOException e) {
			throw new LogicException(e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}

	private void updateStatus(long movieId) throws LogicException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		try {
			RatingDAO ratingDAO = new RatingDAO(connection);
			MovieDAO movieDAO = new MovieDAO(connection);
			UserDAO userDAO = new UserDAO(connection);
			Movie movie = movieDAO.findEntityById(movieId).orElse(new Movie());
			List<Rating> ratingList = ratingDAO.findRatingsByMovieId(movieId);
			//
			if (ratingList.size() > SKIP_NUMBER_OF_USERS) {
				ratingList.stream().sorted((o1, o2) -> o2.getTime().compareTo(o1.getTime())).skip(SKIP_NUMBER_OF_USERS).forEach(
						(o1) -> {
							if (Math.abs(movie.getRating() - o1.getRating()) <= EPS) {
								try {
									User user = userDAO.findEntityById(o1.getUserId()).orElse(new User());
									user.setStatus(user.getStatus() + ADD_STATUS);
									userDAO.update(user);
								} catch (DAOException e) {
									LOGGER.warn("Problem in updating status", e);
								}
							}
						}
				);
				movieDAO.update(movie);
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
