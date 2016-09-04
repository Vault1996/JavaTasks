package by.epam.cinemarating.dao;

import by.epam.cinemarating.database.WrapperConnection;
import by.epam.cinemarating.entity.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewDAO extends AbstractDAO<Review> {
	private static final String MOVIE_ID = "movie_id";
	private static final String USER_ID = "user_id";
	private static final String REVIEW = "review";
	private static final String TIME = "time";

	private static final String FIND_ALL_REVIEWS = "SELECT movie_id,user_id,review,`time` " +
			"FROM review";
	private static final String FIND_REVIEW_BY_MOVIE_ID = "SELECT movie_id,user_id,review,`time` " +
			"FROM review WHERE movie_id=?";
	private static final String FIND_REVIEW_BY_USER_ID = "SELECT movie_id,user_id,review,`time` " +
			"FROM review WHERE user_id=?";
	private static final String FIND_REVIEW = "SELECT movie_id,user_id,review,`time` " +
			"FROM review WHERE movie_id=? AND user_id=?";

	private static final String INSERT_REVIEW = "INSERT INTO review(movie_id,user_id,review,`time`) " +
			"VALUES(?,?,?,NOW())";
	private static final String UPDATE_REVIEW = "UPDATE review SET review=? " +
			"WHERE movie_id=? AND user_id=?";
	private static final String DELETE_REVIEW_BY_MOVIE_ID = "DELETE FROM review WHERE movie_id=?";
	private static final String DELETE_REVIEW_BY_USER_ID = "DELETE FROM review WHERE user_id=?";
	private static final String DELETE_REVIEW = "DELETE FROM review WHERE movie_id=? AND user_id=?";


	public ReviewDAO(WrapperConnection connection) {
		super(connection);
	}

	@Override
	public List<Review> findAll() throws DAOException {
		List<Review> reviews = new ArrayList<>();
		try (
				Statement statement = connection.createStatement();
		) {
			ResultSet resultSet = statement.executeQuery(FIND_ALL_REVIEWS);
			while (resultSet.next()) {
				long movieId = resultSet.getLong(MOVIE_ID);
				long userId = resultSet.getLong(USER_ID);
				String review = resultSet.getString(REVIEW);
				Timestamp time = resultSet.getTimestamp(TIME);
				reviews.add(new Review(movieId, userId, review, time));
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
		return reviews;
	}

	@Override
	public Optional<Review> findEntityById(long id) throws DAOException {
		return Optional.empty();
	}

	public List<Review> findReviewsByMovieId(long movieId) throws DAOException {
		List<Review> reviews = new ArrayList<>();
		try (
				PreparedStatement statement = connection.prepareStatement(FIND_REVIEW_BY_MOVIE_ID)
		) {
			statement.setLong(1, movieId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long movieIdField = resultSet.getLong(MOVIE_ID);
				long userId = resultSet.getLong(USER_ID);
				String reviewField = resultSet.getString(REVIEW);
				Timestamp time = resultSet.getTimestamp(TIME);
				reviews.add(new Review(movieIdField, userId, reviewField, time));
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
		return reviews;
	}

	public List<Review> findReviewsByUserId(long userId) throws DAOException {
		List<Review> reviews = new ArrayList<>();
		try (
				PreparedStatement statement = connection.prepareStatement(FIND_REVIEW_BY_USER_ID)
		) {
			statement.setLong(1, userId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long userIdField = resultSet.getLong(USER_ID);
				long movieId = resultSet.getLong(MOVIE_ID);
				String reviewField = resultSet.getString(REVIEW);
				Timestamp time = resultSet.getTimestamp(TIME);
				reviews.add(new Review(movieId, userIdField, reviewField, time));
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
		return reviews;
	}

	public Optional<Review> findReview(long movieId, long userId) throws DAOException {
		Review review = null;
		try (
				PreparedStatement statement = connection.prepareStatement(FIND_REVIEW)
		) {
			statement.setLong(1, movieId);
			statement.setLong(2, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				long userIdField = resultSet.getLong(USER_ID);
				long movieIdField = resultSet.getLong(MOVIE_ID);
				String reviewField = resultSet.getString(REVIEW);
				Timestamp time = resultSet.getTimestamp(TIME);
				review = new Review(movieIdField, userIdField, reviewField, time);
			}
		} catch (SQLException e) {
			throw new DAOException();
		}
		return Optional.ofNullable(review);
	}

	@Override
	public boolean insert(Review entity) throws DAOException {
		int result;
		try (
				PreparedStatement statement = connection.prepareStatement(INSERT_REVIEW);
		) {
			statement.setLong(1, entity.getMovieId());
			statement.setLong(2, entity.getUserId());
			statement.setString(3, entity.getReview());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		}
		return result > 0;
	}

	@Override
	public boolean delete(long id) throws DAOException {
		return false;
	}

	public boolean deleteReviewsByMovieId(long movieId) throws DAOException {
		int result;
		try(
				PreparedStatement statement = connection.prepareStatement(DELETE_REVIEW_BY_MOVIE_ID);
		) {
			statement.setLong(1, movieId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		}
		return result > 0;
	}

	public boolean deleteReviewsByUserId(long userId) throws DAOException {
		int result;
		try(
				PreparedStatement statement = connection.prepareStatement(DELETE_REVIEW_BY_USER_ID);
		) {
			statement.setLong(1, userId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		}
		return result > 0;
	}

	public boolean deleteReview(long movieId, long userId) throws DAOException {
		int result;
		try(
				PreparedStatement statement = connection.prepareStatement(DELETE_REVIEW);
		) {
			statement.setLong(1, movieId);
			statement.setLong(2, userId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		}
		return result > 0;
	}

	@Override
	public boolean update(Review entity) throws DAOException {
		int result;
		try(
				PreparedStatement statement = connection.prepareStatement(UPDATE_REVIEW);
		) {
			statement.setString(1, entity.getReview());
			statement.setLong(2, entity.getMovieId());
			statement.setLong(3, entity.getUserId());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException();
		}
		return result > 0;
	}
}