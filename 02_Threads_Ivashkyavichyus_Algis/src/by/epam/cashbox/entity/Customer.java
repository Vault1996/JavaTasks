package by.epam.cashbox.entity;

import by.epam.cashbox.generator.IDGenerator;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Customer extends Thread{
    private static final Logger LOGGER = LogManager.getLogger(Customer.class.getName());

    private static final String LOCALE_PATH = "resources.lang";
    private static final String DEFAULT_LANGUAGE = "EN";
    private static final String DEFAULT_COUNTRY = "US";

    //changing number of queue and index of client
    private static Exchanger<Pair<Integer, Integer>> queueExchanger = new Exchanger<>();

    private Locale locale;
    private ResourceBundle resourceBundle;

    private long customerID;

    private int indexOfQueue;
    private ArrayList<RestaurantQueue> queue = new ArrayList<>();

    public Customer() {
        this.customerID = IDGenerator.createID();
        locale = new Locale(DEFAULT_LANGUAGE, DEFAULT_COUNTRY);
        resourceBundle = ResourceBundle.getBundle(LOCALE_PATH, locale);
    }

    public Customer(ArrayList<RestaurantQueue> queue, int indexOfQueue) {
        this.customerID = IDGenerator.createID();
        locale = new Locale(DEFAULT_LANGUAGE, DEFAULT_COUNTRY);
        resourceBundle = ResourceBundle.getBundle(LOCALE_PATH, locale);

        this.queue = queue;
        this.indexOfQueue = indexOfQueue;
    }

    public Customer(String language, String country) {
        this.customerID = IDGenerator.createID();
        locale = new Locale(language, country);
        resourceBundle = ResourceBundle.getBundle(LOCALE_PATH, locale);
    }

    public Customer(String language, String country, ArrayList<RestaurantQueue> queue) {
        this.customerID = IDGenerator.createID();
        locale = new Locale(language, country);
        resourceBundle = ResourceBundle.getBundle("resources.language", locale);

        this.queue = queue;
    }

    public void setQueue(ArrayList<RestaurantQueue> queue) {
        this.queue = queue;
    }

    public int getIndexOfQueue() {
        return indexOfQueue;
    }

    public void setIndexOfQueue(int indexOfQueue) {
        this.indexOfQueue = indexOfQueue;
    }

    private void changeQueue(int indexToQueue){
        queue.get(indexOfQueue).lock();
        queue.get(indexToQueue).lock();
        queue.get(indexOfQueue).removeFromQueueNoLock(this);
        queue.get(indexToQueue).addToQueueNoLock(this);
        queue.get(indexToQueue).unlock();
        queue.get(indexOfQueue).unlock();
        indexOfQueue = indexToQueue;
    }

    private void changeWithClient(){
        queue.get(indexOfQueue).lock();
        RestaurantQueue q = queue.get(indexOfQueue);
        int indexOfCustomer = queue.get(indexOfQueue).indexOfLock(this);
        LOGGER.info(resourceBundle.getString("servicing.client") + customerID +
                " " + resourceBundle.getString("servicing.anotherClient"));
        try {
            Pair<Integer, Integer> changedValue =
                    queueExchanger.exchange(new Pair<>(indexOfQueue, indexOfCustomer),
                            50, TimeUnit.MILLISECONDS);
            LOGGER.info(resourceBundle.getString("servicing.client") +
                    customerID + " " + resourceBundle.getString("servicing.changedClient"));

            queue.get(indexOfQueue).removeFromQueueNoLock(this);
            indexOfQueue = changedValue.getKey();
            queue.get(indexOfQueue).addToQueueNoLock(changedValue.getValue(), this);
        } catch (InterruptedException e) {
            LOGGER.error("Sleep Exception");
        } catch (TimeoutException e) {
            LOGGER.warn("Timeout in changing queues with client №" + customerID);
        } finally {
            q.unlock();
        }
    }

    @Override
    public void run() {
        LOGGER.info("Stating thread.");
        Random random = new Random(System.currentTimeMillis());

        while(queue.get(indexOfQueue).containsLock(this)){
            if (queue.get(indexOfQueue).indexOfLock(this) == 0) {
                try {
                    LOGGER.info(resourceBundle.getString("servicing.client") +
                            " " + customerID + " " + resourceBundle.getString("servicing.servicing"));
                    TimeUnit.MILLISECONDS.sleep(500);
                    LOGGER.info(resourceBundle.getString("servicing.client") +
                            " " + customerID + " " + resourceBundle.getString("servicing.serviced"));
                    queue.get(indexOfQueue).removeFromQueueLock(0);
                } catch (InterruptedException e) {
                    LOGGER.error("Sleep Exception");
                }
            } else {
                try {
                    int randomNumber = random.nextInt(100);
                    if (randomNumber < 10) {
                        /*LOGGER.info(resourceBundle.getString("servicing.client") +
                                " " + customerID + " " + resourceBundle.getString("servicing.waiting"));*/
                        TimeUnit.MILLISECONDS.sleep(50);
                    } else if (randomNumber < 90) {
                        int randomIndexOfQueue = random.nextInt(queue.size());
                        if (randomIndexOfQueue != indexOfQueue) {
                            if (queue.get(randomIndexOfQueue).sizeLock() < queue.get(indexOfQueue).indexOfLock(this)) {
                                LOGGER.info(resourceBundle.getString("servicing.changing") + " " + customerID + " ( " + indexOfQueue + " -> "
                                        + randomIndexOfQueue + " )");
                                changeQueue(randomIndexOfQueue);
                                LOGGER.info(resourceBundle.getString("servicing.client") + " " + customerID +
                                        " " + resourceBundle.getString("servicing.finishChanging"));
                            }
                        }
                    } else {
                        changeWithClient();
                    }
                } catch (InterruptedException e) {
                    LOGGER.warn("Sleep Exception");
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != getClass()) {
            return false;
        }

        Customer customer = (Customer) o;

        if (customerID != customer.customerID) return false;
        if (indexOfQueue != customer.indexOfQueue) return false;
        if (locale != null ? !locale.equals(customer.locale) : customer.locale != null) return false;
        if (resourceBundle != null ? !resourceBundle.equals(customer.resourceBundle) : customer.resourceBundle != null)
            return false;
        return queue != null ? queue.equals(customer.queue) : customer.queue == null;

    }

    @Override
    public int hashCode() {
        int result = locale != null ? locale.hashCode() : 0;
        result = 31 * result + (resourceBundle != null ? resourceBundle.hashCode() : 0);
        result = 31 * result + (int) (customerID ^ (customerID >>> 32));
        result = 31 * result + indexOfQueue;
        result = 31 * result + (queue != null ? queue.hashCode() : 0);
        return result;
    }
}
