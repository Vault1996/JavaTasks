package by.epam.jdbc.connection;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/cinema";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private static final String USER = "user";
	private static final String PASSWORD = "password";
	private static final String AUTO_RECONNECT = "autoReconnect";
	private static final String CHAR_ENCODING = "characterEncoding";
	private static final String USE_UNICODE = "useUnicode";
	private static final String USE_SSL = "useSSL";

	private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

	private ArrayBlockingQueue <ProxyConnection> connectionQueue;

	private static ConnectionPool instance = null;
	private static ReentrantLock lock = new ReentrantLock();
	private static AtomicBoolean instanceCreated = new AtomicBoolean();

	private ConnectionPool(final int POOL_SIZE) {
		try {
			connectionQueue = new ArrayBlockingQueue<>(POOL_SIZE);
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//**********
			Properties properties = new Properties();
			properties.put(USER, DB_USER);
			properties.put(PASSWORD, DB_PASSWORD);
			properties.put(AUTO_RECONNECT, true);
			properties.put(CHAR_ENCODING, "UTF-8");
			properties.put(USE_UNICODE, true);
			properties.put(USE_SSL, false);
			//***********
			for (int i = 0; i < POOL_SIZE; i++) {
				Connection connection = DriverManager.getConnection(DB_URL, properties);
				ProxyConnection proxyConnection = new ProxyConnection(connection);
				connectionQueue.offer(proxyConnection);
			}
		} catch (SQLException e) {
			LOGGER.fatal("Can't connect to database.");
			throw new RuntimeException(e);
		}
	}

	public static ConnectionPool getInstance(final int POOL_SIZE) {
		if(!instanceCreated.get()){
			lock.lock(); // блокировка
			try {
				if (instance == null) {
					instance = new ConnectionPool(POOL_SIZE);
					instanceCreated.set(true);
				}
			} finally {
				lock.unlock(); // снятие блокировки
			}
		}
		return instance;
	}

	public Optional<ProxyConnection> takeConnection() {
		ProxyConnection connection = null;
		try {
			connection = connectionQueue.poll(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			LOGGER.error("Error while waiting.");
		}
		return Optional.ofNullable(connection);
	}
	public void returnConnection(ProxyConnection connection) {
		if (connection != null) {
			connectionQueue.offer(connection);
		}
	}

	public void closePool() throws InterruptedException, SQLException{
		while (!connectionQueue.isEmpty()) {
			connectionQueue.take().close();
		}
	}
}
