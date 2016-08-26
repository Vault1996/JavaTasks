package by.epam.cinemarating.logic;

import by.epam.cinemarating.dao.DAOException;
import by.epam.cinemarating.dao.UserDAO;
import by.epam.cinemarating.database.ConnectionPool;
import by.epam.cinemarating.database.WrapperConnection;
import by.epam.cinemarating.entity.Role;
import by.epam.cinemarating.entity.User;
import by.epam.cinemarating.hash.Hasher;
import by.epam.cinemarating.hash.MD5Hash;
import javafx.util.Pair;

import java.util.Optional;

public class LoginLogic {
	public Pair<Boolean, Role> logic(String login, String password) throws LogicException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		UserDAO userDAO = new UserDAO(connection);
		Hasher hasher = new MD5Hash();
		Pair<Boolean, Role> result;
		try {
			Optional<User> optional = userDAO.findUserByLoginAndPassword(login, hasher.hexHash(password));
			if (optional.isPresent()) {
				result = new Pair<>(true, optional.get().getRole());
			} else {
				result = new Pair<>(false, null);
			}
			return result;
		} catch (DAOException e) {
			throw new LogicException(e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}
}
