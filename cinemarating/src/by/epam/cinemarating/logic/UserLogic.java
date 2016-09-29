package by.epam.cinemarating.logic;

import by.epam.cinemarating.exception.DAOException;
import by.epam.cinemarating.dao.UserDAO;
import by.epam.cinemarating.database.ConnectionPool;
import by.epam.cinemarating.database.WrapperConnection;
import by.epam.cinemarating.entity.User;
import by.epam.cinemarating.exception.LogicException;

import java.util.List;

public class UserLogic {
	private static final String ERROR_MESSAGE = "Problem in User Logic";

	/**
	 * Find user by id
	 * @param userId id of user
	 * @return user if exists and null otherwise
	 * @throws LogicException
	 */
	public User findUserById(long userId) throws LogicException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		UserDAO userDAO = new UserDAO(connection);
		try {
			User user = userDAO.findEntityById(userId).orElse(null);
			return user;
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}

	/**
	 * Gets all users
	 * @return list of all users sorted by status
	 * @throws LogicException
	 */
	public List<User> getAllUsers() throws LogicException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		UserDAO userDAO = new UserDAO(connection);
		List<User> users;
		try {
			users = userDAO.findAll();
			users.sort((o1, o2)->(Integer.valueOf(o2.getStatus())).compareTo(o1.getStatus()));
			return users;
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}

	/**
	 * Delete user
	 * @param userId id of user
	 * @throws LogicException
	 */
	public void deleteUser(long userId) throws LogicException{
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		UserDAO userDAO = new UserDAO(connection);
		try {
			userDAO.delete(userId);
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}
}
