package test.by.epam.cinemarating;


import by.epam.cinemarating.database.ConnectionPool;
import by.epam.cinemarating.hash.Hasher;
import by.epam.cinemarating.hash.MD5Hash;
import by.epam.cinemarating.validation.AuthenticationValidator;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class CinemaRatingTest {
	@Test
	public void authenticationValidationTest() {
		String login = "Hello";
		String password = "1234567";
		AuthenticationValidator validator = new AuthenticationValidator();
		Assert.assertTrue(validator.validate(login, password));
	}
	@Test (timeout = 100)
	public void singletonTest() {
		ConnectionPool firstInstance = ConnectionPool.getInstance();
		ConnectionPool secondInstance = ConnectionPool.getInstance();
		Assert.assertSame(firstInstance, secondInstance);
		ConnectionPool.getInstance().closePool();
	}
	@Test
	public void hashTest() {
		Hasher hasher = new MD5Hash();
		Assert.assertEquals("81dc9bdb52d04dc20036dbd8313ed055", hasher.hexHash("1234"));
	}
	@Test
	public void poolCheckerTest() throws InterruptedException {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.takeConnection();
		TimeUnit.SECONDS.sleep(200);
		int poolSize = connectionPool.getSize();
		connectionPool.closePool();
		Assert.assertEquals(poolSize, 20);

	}
}
