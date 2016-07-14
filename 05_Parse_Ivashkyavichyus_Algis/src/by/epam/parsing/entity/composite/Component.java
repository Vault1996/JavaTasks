package by.epam.parsing.entity.composite;

import by.epam.parsing.exception.NoSuchOperationException;
import by.epam.parsing.type.TypeOfTextUnit;

import java.io.FileWriter;

public interface Component {
    void add(Component c);
    void remove(Component c);
    int size();
    TypeOfTextUnit getType();
    Component getChild(int index) throws NoSuchOperationException;
    void print(FileWriter fileWriter);
}
