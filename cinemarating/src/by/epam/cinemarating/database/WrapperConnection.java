package by.epam.cinemarating.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class WrapperConnection{
	private static final Logger LOGGER = LogManager.getLogger(WrapperConnection.class);

	private Connection connection;

	WrapperConnection(Connection connection) { // только в пакете
		//TODO: может изменить реализацию
		if (connection != null) {
			this.connection = connection;
		}
	}

	void close() throws SQLException {
		connection.close();
	}

	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}

	public void commit() throws SQLException {
		connection.commit();
	}

	public boolean isClosed() throws SQLException {
		return connection.isClosed();
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	public void rollback() throws SQLException {
		connection.rollback();
	}

	public void setAutoCommit(boolean flag) throws SQLException {
		connection.setAutoCommit(flag);
	}

}
