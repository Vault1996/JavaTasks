package by.epam.card.enumeration;

public enum Type {

    CELEBRATION("Celebration"),
    ADV("Adv"),
    USUAL("Usual");
    private final String value;

    Type(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Type fromValue(String v) {
        for (Type c: Type.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return CELEBRATION;
    }
    @Override
    public String toString() {
        return value;
    }
}
