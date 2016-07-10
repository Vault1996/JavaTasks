package by.epam.card.enumeration;

public enum Country {

    RUSSIA("Russia"),
    BELARUS("Belarus"),
    GREAT_BRITAIN("Great Britain"),
    UKRAINE("Ukraine");
    private final String value;

    Country(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Country fromValue(String v) {
        for (Country c: Country.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return RUSSIA;
    }
    @Override
    public String toString() {
        return value;
    }
}
