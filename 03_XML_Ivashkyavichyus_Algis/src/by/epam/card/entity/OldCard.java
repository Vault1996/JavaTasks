package by.epam.card.entity;

import by.epam.card.enumeration.Country;
import by.epam.card.enumeration.Thema;
import by.epam.card.enumeration.Type;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import javax.xml.datatype.XMLGregorianCalendar;

public class OldCard {

    private Thema thema;
    private Type type;
    private Country country;
    private XMLGregorianCalendar year;
    private boolean valuable;
    private String id;
    private String author = "None";

    public Thema getThema() {
        return thema;
    }

    public void setThema(Thema value) {
        this.thema = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type value) {
        this.type = value;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country value) {
        this.country = value;
    }

    public XMLGregorianCalendar getYear() {
        return year;
    }

    public void setYear(String value) {
        int date = Integer.parseInt(value);
        year = new XMLGregorianCalendarImpl();
        if (1000 <= date && date <= 2016) {
            year.setYear(Integer.parseInt(value));
        } else {
            year.setYear(2016);
        }
    }

    public boolean isValuable() {
        return valuable;
    }

    public void setValuable(boolean value) {
        this.valuable = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String value) {
        this.author = value;
    }

    @Override
    public String toString() {
        return  "thema='" + thema + '\'' +
                ", type='" + type + '\'' +
                ", country='" + country + '\'' +
                ", year='" + year + '\'' +
                ", valuable='" + valuable + '\'' +
                ", id='" + id + '\'' +
                ", author='" + author + '\'';
    }
}
