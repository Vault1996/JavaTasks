package by.epam.parsing.entity.composite;

import by.epam.parsing.enumeration.TypeOfTextUnit;
import by.epam.parsing.parser.LeafTextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Composite implements Component {
    private static final Logger LOGGER = LogManager.getLogger(Composite.class);
    private ArrayList<Component> components = new ArrayList<>();
    private final TypeOfTextUnit TYPE_OF_UNIT;

    public Composite(TypeOfTextUnit typeOfTextUnit) {
        TYPE_OF_UNIT = typeOfTextUnit;
    }

    public void add(Component component) {
        components.add(component);
    }

    public void remove(Component component) {
        components.remove(component);
    }

    public Object getChild(int index) {
        return components.get(index);
    }

    @Override
    public void print(FileWriter fileWriter) {
        try {
            for (int i = 0; i < components.size(); i++) {
                Component text = components.get(i);
                switch (TYPE_OF_UNIT) {
                    case TEXT:
                        if (i == 0) {
                            fileWriter.write("\t");
                        } else {
                            fileWriter.write("\r\n\t");
                        }
                        break;
                    case PARAGRAPH: case SENTENCE:
                        if (i != 0) {
                            fileWriter.write(" ");
                        }
                        break;
                    case LEXEME:
                        break;
                    default:
                        LOGGER.error("No such type of unit.");
                }
                text.print(fileWriter);
            }
        } catch (IOException e) {
            LOGGER.error("Can't write to file.");
        }
    }

    @Override
    public void calculateFormula() {
        for (Component component : components) {
            component.calculateFormula();
        }
    }

    @Override
    public void changeLetter() {
        if (TYPE_OF_UNIT == TypeOfTextUnit.LEXEME) {
            String text = "";
            for (Component component : components) {
                text += ((Leaf)component).getText();
            }
            if(!text.isEmpty()) {
                text = text.replaceAll(Character.valueOf(text.charAt(0)).toString(), "");
                components = new ArrayList<>();
                LeafTextParser leafTextParser = new LeafTextParser();
                leafTextParser.parse(this, text);
            }
        } else {
            for (Component component : components) {
                component.changeLetter();
            }
        }
    }

    @Override
    public void removeLexeme(int length, char startLetter) {
        if (TYPE_OF_UNIT == TypeOfTextUnit.LEXEME) {
            String text = "";
            for (Component component : components) {
                text += ((Leaf)component).getText();
            }
            if(!text.isEmpty()) {
                if (text.charAt(0) == startLetter && text.length() == length) {
                    components = new ArrayList<>();
                }
            }
        } else {
            for (Component component : components) {
                component.removeLexeme(length, startLetter);
            }
        }
    }

    public TypeOfTextUnit getType() {
        return TYPE_OF_UNIT;
    }

    public void removeEmptyLexemes(){
        for (int i = 0; i < components.size(); i++) {
            Composite composite = (Composite) components.get(i);
            if (TYPE_OF_UNIT == TypeOfTextUnit.SENTENCE) {
                if (composite.isEmpty()) {
                    remove(components.get(i));
                    i--;
                }
            } else {
                composite.removeEmptyLexemes();
            }
        }
    }
    private boolean isEmpty(){
        return components.isEmpty();
    }

}