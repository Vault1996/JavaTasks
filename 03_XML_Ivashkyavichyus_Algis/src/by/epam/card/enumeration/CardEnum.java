package by.epam.card.enumeration;

public enum CardEnum {
    OLD_CARDS("oldCards"),
    ID("Id"),
    AUTHOR("Author"),
    THEMA("Thema"),
    TYPE("Type"),
    COUNTRY("Country"),
    YEAR("Year"),
    VALUABLE("Valuable"),
    MESSAGE("Message"),
    SONG("Song"),
    FORMAT("Format"),
    SOUND_CARD("soundCard"),
    POST_CARD("postCard");

    private String value;

    private CardEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
