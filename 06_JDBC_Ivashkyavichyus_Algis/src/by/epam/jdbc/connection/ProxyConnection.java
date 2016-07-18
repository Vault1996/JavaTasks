package by.epam.jdbc.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProxyConnection{
	private Connection connection;

	ProxyConnection(Connection connection) { // только в пакете
		if (connection != null) {
			this.connection = connection;
		}
	}

	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}

	public void close() throws SQLException {
		connection.close();
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
