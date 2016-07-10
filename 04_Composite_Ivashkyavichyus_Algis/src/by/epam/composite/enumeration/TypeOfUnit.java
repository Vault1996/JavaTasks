package by.epam.composite.enumeration;

public enum TypeOfUnit {
    ARMY("Army"),
    // ПОЛК
    REGIMENT("Regiment"),
    // ВЗВОД
    TROOP("Troop"),
    SOLDIER("Soldier");

    private String value;

    private TypeOfUnit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
