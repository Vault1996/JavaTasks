package by.epam.cinemarating.logic;

import by.epam.cinemarating.dao.DAOException;
import by.epam.cinemarating.dao.UserDAO;
import by.epam.cinemarating.database.ConnectionPool;
import by.epam.cinemarating.database.WrapperConnection;
import by.epam.cinemarating.entity.User;
import by.epam.cinemarating.hash.Hasher;
import by.epam.cinemarating.hash.MD5Hash;

import java.util.Optional;

public class LoginLogic {
	public boolean logic(String login, String password, User user) throws LogicException {
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
			}
			return optional.isPresent();
		} catch (DAOException e) {
			throw new LogicException(e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}
}
