package by.epam.cinemarating.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

class PoolSizeController extends Thread {
	private static final int CHECK_TIME_IN_SECONDS = 180;

	private static final Logger LOGGER = LogManager.getLogger(PoolSizeController.class);

	private ConnectionPool connectionPool;
	private String url;
	private Properties properties;
	private int poolSize;

	PoolSizeController(ConnectionPool connectionPool,
							  String url, Properties properties, int poolSize) {
		this.connectionPool = connectionPool;
		this.url = url;
		this.properties = properties;
		this.poolSize = poolSize;
	}

	@Override
	public void run() {
		while(true) {
			while (connectionPool.getSize() < poolSize) {
				try {
					connectionPool.returnConnection(new WrapperConnection(DriverManager.getConnection(url, properties)));
				} catch (SQLException e) {
					LOGGER.warn( "Can't set new connection to ConnectionPool", e );
				}
			}
			try {
				TimeUnit.SECONDS.sleep(CHECK_TIME_IN_SECONDS);
			} catch (InterruptedException e) {
				LOGGER.warn(e);
			}
		}
	}
}
