package by.epam.cinemarating.logic;

import by.epam.cinemarating.dao.DAOException;
import by.epam.cinemarating.dao.MovieDAO;
import by.epam.cinemarating.database.ConnectionPool;
import by.epam.cinemarating.database.WrapperConnection;
import by.epam.cinemarating.entity.Movie;

import java.util.List;

public class MovieLogic {
	private static final String RATING = "rating";
	private static final String NAME = "name";
	private static final String ERROR_MESSAGE = "Problem in Movie Logic";

	public Movie findMovieById(long movieId) throws LogicException{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		MovieDAO movieDAO = new MovieDAO(connection);
		try {
			Movie movie = movieDAO.findEntityById(movieId).orElse(null);
			return movie;
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}

	public List<Movie> findTopMovies(int numberOfTopMovies) throws LogicException{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		MovieDAO movieDAO = new MovieDAO(connection);
		try {
			List<Movie> movies = movieDAO.findAll();
			movies.sort((o1, o2)->(Double.valueOf(o2.getRating())).compareTo(o1.getRating()));
			if (movies.size() >= numberOfTopMovies) {
				movies = movies.subList(0, numberOfTopMovies);
			}
			return movies;
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}

	public List<Movie> getAllMovies(String sortBy) throws LogicException{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		MovieDAO movieDAO = new MovieDAO(connection);
		List<Movie> movies;
		try {
			movies = movieDAO.findAll();
			switch (sortBy) {
				case RATING:
					movies.sort((o1, o2)->(Double.valueOf(o2.getRating())).compareTo(o1.getRating()));
					break;
				case NAME: default:
					movies.sort((o1, o2)->o1.getName().compareTo(o2.getName()));
			}
			return movies;
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}

	public void addMovie(Movie movie) throws LogicException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		MovieDAO movieDAO = new MovieDAO(connection);
		try {
			movieDAO.insert(movie);
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}

	public void deleteMovie(long movieId) throws LogicException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		MovieDAO movieDAO = new MovieDAO(connection);
		try {
			movieDAO.delete(movieId);
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}

	public void editMovie(Movie movie) throws LogicException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		MovieDAO movieDAO = new MovieDAO(connection);
		try {
			movieDAO.update(movie);
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}
}
