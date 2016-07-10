package by.epam.composite.entity;

import by.epam.composite.enumeration.TypeOfUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Soldier implements Component {
    private static final Logger LOGGER = LogManager.getLogger(Soldier.class);

    private static final int DEFAULT_HEALTH = 1;
    private static final int DEFAULT_STRENGTH = 1;

    private final static Random random = new Random(System.currentTimeMillis());

    private final TypeOfUnit TYPE_OF_UNIT = TypeOfUnit.SOLDIER;

    private int healthPoints = DEFAULT_HEALTH;
    private int strength = DEFAULT_STRENGTH;

    public Soldier(int healthPoints, int strength) {
        if (healthPoints > 0) {
            this.healthPoints = healthPoints;
        }
        if (strength > 0) {
            this.strength = strength;
        }
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints > 0) {
            this.healthPoints = healthPoints;
        } else {
            this.healthPoints = DEFAULT_HEALTH;
        }
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if (strength > 0) {
            this.strength = strength;
        } else {
            this.strength = DEFAULT_STRENGTH;
        }
    }

    public void hit(Component unit) {
        unit.distributeDamage(strength + random.nextInt(10) - 5);
    }


    @Override
    public void distributeDamage(int damage) {
        LOGGER.info("Soldier with " + healthPoints + " healthPoints take " + damage + " damage.");
        this.healthPoints -= damage;
    }

    public void add(Component c) {

    }
    public void remove(Component c) {

    }

    @Override
    public boolean isDead() {
        return !(healthPoints > 0);
    }

    @Override
    public int totalDamage() {
        return getStrength() + random.nextInt(10) - 5;
    }

    @Override
    public TypeOfUnit getType() {
        return TYPE_OF_UNIT;
    }

    @Override
    public String toString() {
        return "Soldier{ healthPoints=" + healthPoints +
                ", strength=" + strength +
                '}';
    }
}
