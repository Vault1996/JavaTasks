package by.epam.composite.main;

import by.epam.composite.entity.Army;
import by.epam.composite.entity.ArmyUnitComposite;
import by.epam.composite.entity.Soldier;
import by.epam.composite.enumeration.TypeOfUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        //
        Soldier soldier1 = new Soldier(100, 12);
        Soldier soldier2 = new Soldier(92, 12);
        Soldier soldier3 = new Soldier(111, 12);
        Soldier soldier4 = new Soldier(105, 12);
        Soldier soldier5 = new Soldier(106, 12);
        Soldier soldier6 = new Soldier(104, 12);
        Soldier soldier7 = new Soldier(108, 12);
        Soldier soldier8 = new Soldier(107, 12);
        Soldier soldier9 = new Soldier(96, 12);
        Soldier soldier10 = new Soldier(107, 12);
        //
        ArmyUnitComposite army1 = new ArmyUnitComposite(TypeOfUnit.ARMY);
        ArmyUnitComposite army2 = new ArmyUnitComposite(TypeOfUnit.ARMY);
        //
        ArmyUnitComposite regiment1 = new ArmyUnitComposite(TypeOfUnit.REGIMENT);
        ArmyUnitComposite regiment2 = new ArmyUnitComposite(TypeOfUnit.REGIMENT);
        //
        ArmyUnitComposite troop1 = new ArmyUnitComposite(TypeOfUnit.TROOP);
        ArmyUnitComposite troop2 = new ArmyUnitComposite(TypeOfUnit.TROOP);
        //
        troop1.add(soldier1);
        troop1.add(soldier2);
        troop1.add(soldier3);
        troop1.add(soldier4);
        troop1.add(soldier5);
        //
        troop2.add(soldier6);
        troop2.add(soldier7);
        troop2.add(soldier8);
        troop2.add(soldier9);
        troop2.add(soldier10);
        //
        regiment1.add(troop1);
        //
        regiment2.add(troop2);
        //
        army1.add(regiment1);
        army2.add(regiment2);
        //
        Army firstArmy = new Army(army1);
        Army secondArmy = new Army(army2);
        //
        firstArmy.startWar(secondArmy);
    }
}
