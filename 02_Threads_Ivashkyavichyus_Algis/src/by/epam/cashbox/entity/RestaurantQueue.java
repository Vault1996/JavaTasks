package by.epam.cashbox.entity;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RestaurantQueue{
    private Lock lock = new ReentrantLock();
    private ArrayList<Customer> queue = new ArrayList<Customer>();

    public RestaurantQueue() {
    }

    public RestaurantQueue(ArrayList<Customer> queue) {
        if (queue != null) {
            this.queue = queue;
        }
    }

    public ArrayList<Customer> getQueue() {
        lock.lock();
        try{
            ArrayList<Customer> clone = (ArrayList<Customer>)queue.clone();
            Customer customer = new Customer();
            for (int i = 0; i < clone.size(); i++) {
                customer = clone.get(i);
                clone.remove(i);
                clone.set(i, customer);
            }
            return clone;
        } finally {
            lock.unlock();
        }
    }

    public void setQueue(ArrayList<Customer> queue) {
        lock.lock();
        try{
            this.queue = queue;
        } finally {
            lock.unlock();
        }

    }

    public void addToQueueLock(Customer customer) {
        lock.lock();
        try{
            queue.add(customer);
        } finally {
            lock.unlock();
        }
    }

    public void addToQueueNoLock(Customer customer) {
        queue.add(customer);
    }

    public void addToQueueNoLock(int index, Customer customer) {
        queue.add(index, customer);
    }

    public int sizeLock() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public boolean containsLock(Customer customer) {
        lock.lock();
        try{
            return queue.contains(customer);
        } finally {
            lock.unlock();
        }

    }

    public int indexOfLock(Customer customer) {
        lock.lock();
        try{
            return queue.indexOf(customer);
        } finally {
            lock.unlock();
        }
    }

    public void removeFromQueueLock(int index) {
        lock.lock();
        try {
            queue.remove(index);
        } finally {
            lock.unlock();
        }
    }

    public void removeFromQueueLock(Customer customer) {
        lock.lock();
        try {
            queue.remove(customer);
        } finally {
            lock.unlock();
        }
    }

    public void removeFromQueueNoLock(Customer customer) {
        queue.remove(customer);
    }

    public void lock(){
        lock.lock();
    }

    public void unlock(){
        lock.unlock();
    }

}