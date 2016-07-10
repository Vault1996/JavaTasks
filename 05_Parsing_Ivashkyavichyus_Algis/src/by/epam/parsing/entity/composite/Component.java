package by.epam.parsing.entity.composite;

import by.epam.parsing.enumeration.TypeOfTextUnit;

import java.io.FileWriter;

public interface Component {
    void add(Component c);
    void remove(Component c);
    TypeOfTextUnit getType();
    Object getChild(int index);
    void print(FileWriter fileWriter);
    void calculateFormula();
    void changeLetter();
    void removeLexeme(int length, char startLetter);
}
