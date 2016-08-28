package by.epam.cinemarating.logic;

import by.epam.cinemarating.dao.DAOException;
import by.epam.cinemarating.dao.MovieDAO;
import by.epam.cinemarating.database.ConnectionPool;
import by.epam.cinemarating.database.WrapperConnection;
import by.epam.cinemarating.entity.Movie;

import java.util.List;

public class AllMoviesLogic {
	public List<Movie> getAllMovies(String sortBy) throws LogicException{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		MovieDAO movieDAO = new MovieDAO(connection);
		List<Movie> movies;
		try {
			movies = movieDAO.findAll();
			switch (sortBy) {
				case "rating":
					movies.sort((o1, o2)->(Double.valueOf(o2.getRating())).compareTo(o1.getRating()));
					break;
				case "name": default:
					movies.sort((o1, o2)->o1.getName().compareTo(o2.getName()));
			}
			return movies;
		} catch (DAOException e) {
			throw new LogicException(e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}


	}

}
