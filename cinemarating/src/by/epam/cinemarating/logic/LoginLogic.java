package by.epam.cinemarating.logic;

import by.epam.cinemarating.exception.DAOException;
import by.epam.cinemarating.dao.UserDAO;
import by.epam.cinemarating.database.ConnectionPool;
import by.epam.cinemarating.database.WrapperConnection;
import by.epam.cinemarating.entity.User;
import by.epam.cinemarating.exception.LogicException;
import by.epam.cinemarating.hash.Hasher;
import by.epam.cinemarating.hash.MD5Hash;

import java.util.Optional;

public class LoginLogic {
	private static final String ERROR_MESSAGE = "Problem in Login Logic";

	/**
	 * Checks if user exists
	 * @param login login of user
	 * @param password password of user
	 * @param user user to set info into
	 * @return true if authentication is valid and false otherwise
	 * @throws LogicException
	 */
	public boolean checkUser(String login, String password, User user) throws LogicException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		UserDAO userDAO = new UserDAO(connection);
		Hasher hasher = new MD5Hash();
		try {
			Optional<User> optional = userDAO.findUserByLoginAndPassword(login, hasher.hexHash(password));
			if (optional.isPresent()) {
				User currentUser = optional.get();
				user.setCreateDate(currentUser.getCreateDate());
				user.setLogin(currentUser.getLogin());
				user.setName(currentUser.getName());
				user.setRole(currentUser.getRole());
				user.setUserId(currentUser.getUserId());
				user.setSurname(currentUser.getSurname());
				user.setPhoto(currentUser.getPhoto());
				user.setStatus(currentUser.getStatus());
			}
			return optional.isPresent();
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}
}
