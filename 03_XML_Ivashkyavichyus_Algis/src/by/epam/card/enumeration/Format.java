package by.epam.card.enumeration;

public enum Format {
    MP_3("mp3"),
    WAV("wav");
    private final String value;

    Format(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Format fromValue(String v) {
        for (Format c: Format.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        return MP_3;
    }
    @Override
    public String toString() {
        return value;
    }
}
