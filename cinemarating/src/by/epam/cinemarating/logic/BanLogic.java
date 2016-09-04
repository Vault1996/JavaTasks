package by.epam.cinemarating.logic;

import by.epam.cinemarating.dao.BanDAO;
import by.epam.cinemarating.dao.DAOException;
import by.epam.cinemarating.database.ConnectionPool;
import by.epam.cinemarating.database.WrapperConnection;
import by.epam.cinemarating.entity.Ban;

import java.sql.Timestamp;
import java.util.Optional;

public class BanLogic {
	private static final String ERROR_MESSAGE = "Problem in Ban Logic";

	public Optional<Ban> findBan(long userId) throws LogicException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		WrapperConnection connection = connectionPool.takeConnection().orElseThrow(LogicException::new);
		BanDAO banDAO = new BanDAO(connection);
		Optional<Ban> banOptional;
		try {
			banOptional = banDAO.findBanByUserId(userId);
			if (banOptional.isPresent()) {
				Ban ban = banOptional.get();
				// if ban passed
				if (ban.getTill().before(new Timestamp(System.currentTimeMillis()))) {
					banDAO.delete(ban.getBanId());
					return Optional.empty();
				}
			}
			return banOptional;
		} catch (DAOException e) {
			throw new LogicException(ERROR_MESSAGE, e);
		} finally {
			if (connection != null) {
				connectionPool.returnConnection(connection);
			}
		}
	}
}
