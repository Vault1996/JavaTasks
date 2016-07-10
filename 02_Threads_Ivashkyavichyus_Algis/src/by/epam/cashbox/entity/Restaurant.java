package by.epam.cashbox.entity;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {
    private String name;
    private ArrayList<RestaurantQueue> restaurant = new ArrayList<RestaurantQueue>();

    private static Restaurant instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    //private Restaurant() { }
    private Restaurant(String name, ArrayList<RestaurantQueue> restaurant) {
        this.name = name;
        if (restaurant != null) {
            this.restaurant = restaurant;
        }
    }
    public static Restaurant getInstance(String name, ArrayList<RestaurantQueue> restaurant) {
        if(!instanceCreated.get()){
            lock.lock(); // блокировка
            try {
                if (instance == null) {
                    instance = new Restaurant(name, restaurant);
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock(); // снятие блокировки
            }
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public ArrayList<RestaurantQueue> getRestaurantQueue() {
        ArrayList<RestaurantQueue> clone = (ArrayList<RestaurantQueue>)restaurant.clone();
        RestaurantQueue queue = new RestaurantQueue();
        for (int i = 0; i < clone.size(); i++) {
            queue = clone.get(i);
            clone.remove(i);
            clone.set(i, queue);
        }
        return clone;
    }
}
