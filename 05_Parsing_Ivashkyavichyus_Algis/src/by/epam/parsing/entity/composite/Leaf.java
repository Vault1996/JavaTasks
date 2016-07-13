package by.epam.parsing.entity.composite;

import by.epam.parsing.exception.NotLeafException;
import by.epam.parsing.type.TypeOfTextUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.EnumSet;

public class Leaf implements Component {
    private static final Logger LOGGER = LogManager.getLogger(Leaf.class);

    private String text;

    private final TypeOfTextUnit TYPE_OF_UNIT;

    public Leaf(TypeOfTextUnit typeOfTextUnit) throws NotLeafException {
        EnumSet<TypeOfTextUnit> leafs = EnumSet.range(TypeOfTextUnit.WORD, TypeOfTextUnit.FORMULA);
        if (leafs.contains(typeOfTextUnit)) {
            TYPE_OF_UNIT = typeOfTextUnit;
        } else {
            throw new NotLeafException();
        }
    }

    public void add(Component c) {

    }

    public void remove(Component c) {

    }

    @Override
    public int size() {
        return 0;
    }

    public Component getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void print(FileWriter fileWriter){
        try {
            fileWriter.write(text);
        } catch (IOException e) {
            LOGGER.error("Can't write in file.");
        }
    }


    public TypeOfTextUnit getType() {
        return TYPE_OF_UNIT;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
