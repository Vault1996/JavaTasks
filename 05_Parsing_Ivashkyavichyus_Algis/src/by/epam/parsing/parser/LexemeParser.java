package by.epam.parsing.parser;

import by.epam.parsing.entity.composite.Composite;
import by.epam.parsing.type.TypeOfTextUnit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractParser{
    private static final String LEXEME_PATTERN = "[\\p{Punct}\\w—]++";

    private static final Pattern LEXEME;

    static {
        LEXEME = Pattern.compile(LEXEME_PATTERN);
    }

    public LexemeParser() {
    }

    public LexemeParser(AbstractParser successor) {
        setSuccessor(successor);
    }

    @Override
    public void parse(Composite textComposite, String text) {
        Matcher matcher = LEXEME.matcher(text);
        Composite lexeme;
        while (matcher.find()) {
            lexeme = new Composite(TypeOfTextUnit.LEXEME);
            textComposite.add(lexeme);
            getSuccessor().parse(lexeme, matcher.group());
        }
    }
}
