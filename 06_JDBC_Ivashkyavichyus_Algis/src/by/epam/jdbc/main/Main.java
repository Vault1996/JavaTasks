package by.epam.jdbc.main;

import by.epam.jdbc.connection.ConnectionPool;
import by.epam.jdbc.connection.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class Main {
	private static final Logger LOGGER = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			ConnectionPool connectionPool = ConnectionPool.getInstance(20);
			Optional<ProxyConnection> optional = connectionPool.takeConnection();
			ProxyConnection proxyConnection = optional.orElse(null);

			Statement statement = proxyConnection.createStatement();
			statement.execute("INSERT INTO user (login, email, password)" +
					" VALUES ('ййййй', 'sfsdf', 'qwqewqew')");
			ResultSet resultSet = statement.executeQuery("SELECT user_id FROM user");
			while (resultSet.next()) {
				System.out.println(resultSet.getString("user_id"));
			}
			statement.close();

			connectionPool.returnConnection(proxyConnection);
			connectionPool.closePool();
		} catch (SQLException e) {
			LOGGER.error("SQL problem.");
		} catch (InterruptedException e) {
			LOGGER.error("Interrupted.");
		}

	}
}
