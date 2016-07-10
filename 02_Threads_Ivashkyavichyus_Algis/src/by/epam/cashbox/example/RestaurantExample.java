package by.epam.cashbox.example;

import by.epam.cashbox.entity.Customer;
import by.epam.cashbox.entity.Restaurant;
import by.epam.cashbox.entity.RestaurantQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;

public class RestaurantExample {
    private static final int NUMBER_OF_QUEUES = 5;
    private static final int NUMBER_OF_CUSTOMERS = 500;

    private static final Logger LOGGER = LogManager.getLogger(RestaurantExample.class.getName());

    public void getExample() {

        LOGGER.info("Creating " + NUMBER_OF_QUEUES + " queues.");
        ArrayList<RestaurantQueue> queues = new ArrayList<>();
        initializeQueues(queues, NUMBER_OF_QUEUES);

        LOGGER.info("Creating " + NUMBER_OF_CUSTOMERS + " customers.");
        Customer[] customers = new Customer[NUMBER_OF_CUSTOMERS];
        initializeCustomers(customers);

        LOGGER.info("Setting queues for customers.");
        setQueueForCustomers(queues, customers, NUMBER_OF_QUEUES);



        LOGGER.info("Creating restaurant.");
        Restaurant restaurant = Restaurant.getInstance("KFC", queues);

        LOGGER.info("Starting threads.");
        start(customers);
    }
    private static void initializeCustomers(Customer[] customers){
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < customers.length; i++) {
            if (random.nextInt(2) == 0) {
                customers[i] = new Customer("EN", "US");
            } else {
                customers[i] = new Customer("RU", "RU");
            }

        }
    }
    private static void initializeQueues(ArrayList<RestaurantQueue> queues, int numberOfQueues){
        for (int i = 0; i < numberOfQueues; i++) {
            queues.add(new RestaurantQueue());
        }
    }
    private static void setQueueForCustomers(ArrayList<RestaurantQueue> queues, Customer[] customers, int numberOfQueues){
        Random random = new Random(System.currentTimeMillis());
        RestaurantQueue randomQueue;
        for (int i = 0; i < customers.length; i++) {
            randomQueue = queues.get(random.nextInt(numberOfQueues));
            randomQueue.addToQueueLock(customers[i]);
            customers[i].setIndexOfQueue(queues.indexOf(randomQueue));
            customers[i].setQueue(queues);
        }
    }
    private static void start(Customer[] customers){
        for (int i = 0; i < customers.length; i++) {
            customers[i].start();
        }
    }
}
