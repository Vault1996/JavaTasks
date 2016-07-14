package by.epam.parsing.entity.composite;

import by.epam.parsing.type.TypeOfTextUnit;
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

    @Override
    public int size() {
        return components.size();
    }

    public Component getChild(int index) {
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

    public TypeOfTextUnit getType() {
        return TYPE_OF_UNIT;
    }

}