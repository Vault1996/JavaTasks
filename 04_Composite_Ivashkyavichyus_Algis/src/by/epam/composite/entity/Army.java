package by.epam.composite.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Army {
    private static final Logger LOGGER = LogManager.getLogger(Army.class);

    private final static int MAX_STEPS = 10000;

    private Component armyUnit;

    public Army(Component armyUnit) {
        this.armyUnit = armyUnit;
    }

    public void startWar(Army opponent) {
        LOGGER.info("War started.");
        int counter = 0;
        while(!armyUnit.isDead() && !opponent.armyUnit.isDead() && counter < MAX_STEPS){
            LOGGER.info("Step " + counter + ":");
            armyUnit.hit(opponent.armyUnit);
            if (!opponent.armyUnit.isDead()) {
                opponent.armyUnit.hit(armyUnit);
            }
            counter++;
        }
        if (counter == MAX_STEPS) {
            LOGGER.info("War ended with a tie.");
        } else if (armyUnit.isDead()) {
            LOGGER.info("End of war. Second army won.");
        } else {
            LOGGER.info("End of war. First army won.");
        }
    }
}
