package by.epam.composite.entity;

import by.epam.composite.enumeration.TypeOfUnit;

public interface Component {
    void hit(Component unit);
    void distributeDamage(int damage);
    void add(Component c);
    void remove(Component c);
    boolean isDead();
    int totalDamage();
    TypeOfUnit getType();
}
