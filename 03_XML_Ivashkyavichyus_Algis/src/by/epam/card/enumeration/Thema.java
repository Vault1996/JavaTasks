package by.epam.card.enumeration;

public enum Thema {
    LANDSCAPE("Landscape"),
    NATURE("Nature"),
    PEOPLE("People"),
    RELIGION("Religion"),
    SPORT("Sport"),
    ARCHITECTURE("Architecture");
    private final String value;

    Thema(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Thema  fromValue(String v) {
        for (Thema c: Thema.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return LANDSCAPE;
    }

    @Override
    public String toString() {
        return value;
    }
}
