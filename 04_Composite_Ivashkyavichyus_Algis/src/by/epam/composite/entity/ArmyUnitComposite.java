package by.epam.composite.entity;

import by.epam.composite.enumeration.TypeOfUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ArmyUnitComposite implements Component {
    private static final Logger LOGGER = LogManager.getLogger(ArmyUnitComposite.class);

    private ArrayList<Component> components = new ArrayList<Component>();
    private final TypeOfUnit TYPE_OF_UNIT;

    public ArmyUnitComposite(TypeOfUnit typeOfUnit) {
        TYPE_OF_UNIT = typeOfUnit;
    }

    public void hit(Component unit) {
        int totalDamage = totalDamage();
        LOGGER.info(TYPE_OF_UNIT.getValue() + " hits " + unit.getType().getValue() + " with " + totalDamage + " damage.");
        unit.distributeDamage(totalDamage);
    }
    @Override
    public void distributeDamage(int damage) {
        int distributedDamage = damage / components.size();
        LOGGER.info(TYPE_OF_UNIT.getValue() + " consists of " + components.size() + " units.");
        for (int i = 0; i < components.size(); i++) {
            components.get(i).distributeDamage(distributedDamage);
            if (components.get(i).isDead()){
                LOGGER.info(components.get(i).getType().getValue() + " is dead.");
                components.remove(i);
                i--;
            }
        }
    }

    public void add(Component component) {
        components.add(component);
    }
    public void remove(Component component) {
        components.remove(component);
    }

    public boolean isDead() {
        for (Component component : components) {
            if (!component.isDead()) {
                return false;
            }
        }
        return true;
    }
    public int totalDamage(){
        int totalDamage = 0;
        for (Component component : components) {
            totalDamage += component.totalDamage();
        }
        return totalDamage;
    }

    @Override
    public TypeOfUnit getType() {
        return TYPE_OF_UNIT;
    }

}