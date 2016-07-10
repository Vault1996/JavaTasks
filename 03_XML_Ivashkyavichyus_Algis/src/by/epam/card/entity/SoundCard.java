package by.epam.card.entity;

import by.epam.card.enumeration.Format;

public class SoundCard extends OldCard
{

    private String song;

    private Format format;

    public String getSong() {
        return song;
    }

    public void setSong(String value) {
        this.song = value;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format value) {
        this.format = value;
    }

    @Override
    public String toString() {
        return "SoundCard: {" + super.toString() +
                ", song='" + song + '\'' +
                ", format='" + format + "\'}";
    }
}
