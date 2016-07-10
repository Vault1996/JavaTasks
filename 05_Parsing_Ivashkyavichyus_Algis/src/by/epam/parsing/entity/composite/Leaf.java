package by.epam.parsing.entity.composite;

import by.epam.parsing.action.interpreter.Interpreter;
import by.epam.parsing.action.interpreter.exception.CantInterpretException;
import by.epam.parsing.entity.composite.exception.NotLeafException;
import by.epam.parsing.enumeration.TypeOfTextUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.EnumSet;

public class Leaf implements Component {
    private static final Logger LOGGER = LogManager.getLogger(Leaf.class);

    private String text;

    private final TypeOfTextUnit TYPE_OF_UNIT;

    public Leaf(TypeOfTextUnit typeOfTextUnit) throws NotLeafException{
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

    public Object getChild(int index) {
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

    @Override
    public void calculateFormula() {
        if (TYPE_OF_UNIT == TypeOfTextUnit.FORMULA) {
            try {
                Interpreter interpreter = new Interpreter(text);
                text = interpreter.calculate().toString();
            } catch (CantInterpretException e) {
                LOGGER.warn("Can't interpret expression " + text);
            }
        }
    }

    @Override
    public void changeLetter() {

    }

    @Override
    public void removeLexeme(int length, char startLetter) {

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
