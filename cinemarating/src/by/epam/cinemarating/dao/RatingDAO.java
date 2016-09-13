package by.epam.cinemarating.dao;

import by.epam.cinemarating.database.WrapperConnection;
import by.epam.cinemarating.entity.Rating;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RatingDAO extends AbstractDAO<Rating> {
	private static final String MOVIE_ID = "movie_id";
	private static final String USER_ID = "user_id";
	private static final String RATING = "rating";
	private static final String TIME = "time";

	private static final String FIND_ALL_RATINGS = "SELECT movie_id,user_id,rating,`time` " +
			"FROM rating";
	private static final String FIND_RATING_BY_MOVIE_ID = "SELECT movie_id,user_id,rating,`time` " +
			"FROM rating WHERE movie_id=?";
	private static final String FIND_RATING_BY_USER_ID = "SELECT movie_id,user_id,rating,`time` " +
			"FROM rating WHERE user_id=?";
	private static final String FIND_RATING = "SELECT movie_id,user_id,rating,`time` " +
			"FROM rating WHERE movie_id=? AND user_id=?";

	private static final String INSERT_RATING = "INSERT INTO rating(movie_id,user_id,rating,time) " +
			"VALUES(?,?,?,NOW())";
	private static final String UPDATE_RATING = "UPDATE rating SET rating=? " +
			"WHERE movie_id=? AND user_id=?";
	private static final String DELETE_RATING_BY_MOVIE_ID = "DELETE FROM rating WHERE movie_id=?";
	private static final String DELETE_RATING_BY_USER_ID = "DELETE FROM rating WHERE user_id=?";
	private static final String DELETE_RATING = "DELETE FROM rating WHERE movie_id=? AND user_id=?";


	public RatingDAO(WrapperConnection connection) {
		super(connection);
	}

	@Override
	public List<Rating> findAll() throws DAOException {
		List<Rating> ratings = new ArrayList<>();
		try (
				Statement statement = connection.createStatement();
		) {
			ResultSet resultSet = statement.executeQuery(FIND_ALL_RATINGS);
			while (resultSet.next()) {
				long movieId = resultSet.getLong(MOVIE_ID);
				long userId = resultSet.getLong(USER_ID);
				int rating = resultSet.getInt(RATING);
				Timestamp time = resultSet.getTimestamp(TIME);
				ratings.add(new Rating(movieId, userId, rating, time));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return ratings;
	}

	@Override
	public Optional<Rating> findEntityById(long id) throws DAOException {
		return Optional.empty();
	}

	public List<Rating> findRatingsByMovieId(long movieId) throws DAOException {
		List<Rating> ratingList = new ArrayList<>();
		try (
				PreparedStatement statement = connection.prepareStatement(FIND_RATING_BY_MOVIE_ID)
		) {
			statement.setLong(1, movieId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				long movieIdField = resultSet.getLong(MOVIE_ID);
				long userId = resultSet.getLong(USER_ID);
				int ratingField = resultSet.getInt(RATING);
				Timestamp time = resultSet.getTimestamp(TIME);
				ratingList.add(new Rating(movieIdField, userId, ratingField, time));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return ratingList;
	}

	public List<Rating> findRatingsByUserId(long userId) throws DAOException {
		List<Rating> ratingList = new ArrayList<>();
		try (
				PreparedStatement statement = connection.prepareStatement(FIND_RATING_BY_USER_ID)
		) {
			statement.setLong(1, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				long userIdField = resultSet.getLong(USER_ID);
				long movieId = resultSet.getLong(MOVIE_ID);
				int ratingField = resultSet.getInt(RATING);
				Timestamp time = resultSet.getTimestamp(TIME);
				ratingList.add(new Rating(movieId, userIdField, ratingField, time));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return ratingList;
	}

	public Optional<Rating> findRating(long movieId, long userId) throws DAOException {
		Rating rating = null;
		try (
				PreparedStatement statement = connection.prepareStatement(FIND_RATING)
		) {
			statement.setLong(1, movieId);
			statement.setLong(2, userId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				long userIdField = resultSet.getLong(USER_ID);
				long movieIdField = resultSet.getLong(MOVIE_ID);
				int ratingField = resultSet.getInt(RATING);
				Timestamp time = resultSet.getTimestamp(TIME);
				rating = new Rating(movieIdField, userIdField, ratingField, time);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return Optional.ofNullable(rating);
	}

	@Override
	public boolean insert(Rating entity) throws DAOException {
		int result;
		try (
				PreparedStatement statement = connection.prepareStatement(INSERT_RATING);
		) {
			statement.setLong(1, entity.getMovieId());
			statement.setLong(2, entity.getUserId());
			statement.setInt(3, entity.getRating());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return result > 0;
	}

	@Override
	public boolean delete(long id) throws DAOException {
		return false;
	}

	public boolean deleteRatingByMovieId(long movieId) throws DAOException {
		int result;
		try(
				PreparedStatement statement = connection.prepareStatement(DELETE_RATING_BY_MOVIE_ID);
		) {
			statement.setLong(1, movieId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return result > 0;
	}

	public boolean deleteRatingByUserId(long userId) throws DAOException {
		int result;
		try(
				PreparedStatement statement = connection.prepareStatement(DELETE_RATING_BY_USER_ID);
		) {
			statement.setLong(1, userId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return result > 0;
	}

	public boolean deleteRating(long movieId, long userId) throws DAOException {
		int result;
		try(
				PreparedStatement statement = connection.prepareStatement(DELETE_RATING);
		) {
			statement.setLong(1, movieId);
			statement.setLong(2, userId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return result > 0;
	}

	@Override
	public boolean update(Rating entity) throws DAOException {
		int result;
		try(
				PreparedStatement statement = connection.prepareStatement(UPDATE_RATING);
		) {
			statement.setInt(1, entity.getRating());
			statement.setLong(2, entity.getMovieId());
			statement.setLong(3, entity.getUserId());
			result = statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return result > 0;
	}

}
