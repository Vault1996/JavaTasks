package by.epam.cashbox.generator;

import java.util.concurrent.atomic.AtomicLong;

public class IDGenerator {
    private static AtomicLong idCounter = new AtomicLong(1);

    public static long createID()
    {
        return idCounter.getAndIncrement();
    }
}