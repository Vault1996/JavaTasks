package by.epam.cinemarating.logic;

import by.epam.cinemarating.dao.DAOException;
import by.epam.cinemarating.dao.UserDAO;
import by.epam.cinemarating.database.ConnectionPool;
import by.epam.cinemarating.database.WrapperConnection;
import by.epam.cinemarating.entity.Role;
import by.epam.cinemarating.entity.User;
import by.epam.cinemarating.hash.Hasher;
import by.epam.cinemarating.hash.MD5Hash;

import java.util.Optional;

public class RegistrationLogic {
	private static final int INITIAL_USER_STATUS = 100;
	private static final String STANDARD_PHOTO = "/images/user/no-user-image.png";
	// return value boolean: is registration passed
	public boolean logic(String login, String name, String surname, String email, String password) throws LogicException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		UserDAO userDAO = new UserDAO(connection);
		try {
			Optional<User> optionalByLogin = userDAO.findUserByLogin(login);
			Optional<User> optionalByEmail = userDAO.findUserByEmail(email);
			boolean isOk = !optionalByLogin.isPresent() && !optionalByEmail.isPresent();
			if (isOk) {
				Hasher hasher = new MD5Hash();
				User user = new User(0, Role.USER, login, hasher.hexHash(password), email, null, name, surname, INITIAL_USER_STATUS, STANDARD_PHOTO);
				userDAO.insert(user);
			}
			return isOk;
		} catch (DAOException e) {
			throw new LogicException(e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}
}
