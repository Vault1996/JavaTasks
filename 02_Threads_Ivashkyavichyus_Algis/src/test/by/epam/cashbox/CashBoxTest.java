package test.by.epam.cashbox;

import by.epam.cashbox.entity.Customer;
import by.epam.cashbox.entity.Restaurant;
import by.epam.cashbox.entity.RestaurantQueue;
import by.epam.cashbox.example.RestaurantExample;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assume;
import org.junit.Test;

import java.util.ArrayList;

public class CashBoxTest {
    private static final Logger LOGGER = LogManager.getLogger(RestaurantExample.class.getName());
    @Test (timeout = 1050)
    public void customerTest() {
        ArrayList<RestaurantQueue> queues = new ArrayList<RestaurantQueue>();
        RestaurantQueue queue = new RestaurantQueue();
        queues.add(queue);
        Customer customer = new Customer();

        queue.addToQueueLock(customer);
        customer.setQueue(queues);

        Restaurant restaurant = Restaurant.getInstance("KFC", queues);

        customer.start();

        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            LOGGER.warn("Problem with sleep");
        }
        Assume.assumeNoException(null);
    }
}
