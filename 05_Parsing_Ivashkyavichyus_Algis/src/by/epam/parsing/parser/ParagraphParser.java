package by.epam.parsing.parser;

import by.epam.parsing.entity.composite.Composite;
import by.epam.parsing.enumeration.TypeOfTextUnit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractParser {
    private static final String PARAGRAPH_PATTERN = "\\t[ —\\w\\p{Punct}]*\\r\\n";

    private static final Pattern PARAGRAPH;

    static {
        PARAGRAPH = Pattern.compile(PARAGRAPH_PATTERN);
    }

    public ParagraphParser() {
    }

    public ParagraphParser(AbstractParser successor) {
        setSuccessor(successor);
    }

    @Override
    public void parse(Composite textComposite, String text) {
        Matcher matcher = PARAGRAPH.matcher(text);
        Composite paragraph;
        while (matcher.find()) {
            paragraph = new Composite(TypeOfTextUnit.PARAGRAPH);
            textComposite.add(paragraph);
            getSuccessor().parse(paragraph, matcher.group());
        }
    }
}
