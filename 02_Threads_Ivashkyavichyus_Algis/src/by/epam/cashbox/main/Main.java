package by.epam.cashbox.main;

import by.epam.cashbox.example.RestaurantExample;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        LOGGER.info("Starting program.");
        LOGGER.info("Crating example.");
        RestaurantExample example = new RestaurantExample();
        LOGGER.info("Example created.");
        example.getExample();
    }
}