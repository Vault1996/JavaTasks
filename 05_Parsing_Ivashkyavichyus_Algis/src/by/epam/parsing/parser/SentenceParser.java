package by.epam.parsing.parser;

import by.epam.parsing.entity.composite.Composite;
import by.epam.parsing.enumeration.TypeOfTextUnit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser{
    private static final String LAST_DIGIT = "(\\.|!|\\?|(\\.\\.\\.)|(\\?!)|(!\\?))";
    private static final String SENTENCE_PATTERN = "[ —\\w,+\\-/*()'\"]*" + LAST_DIGIT + "[ \\r\\n]";

    private static final Pattern SENTENCE;

    static {
        SENTENCE = Pattern.compile(SENTENCE_PATTERN);
    }

    public SentenceParser() {
    }

    public SentenceParser(AbstractParser successor) {
        setSuccessor(successor);
    }

    @Override
    public void parse(Composite textComposite, String text) {
        Matcher matcher = SENTENCE.matcher(text);
        Composite sentence;
        while (matcher.find()) {
            sentence = new Composite(TypeOfTextUnit.SENTENCE);
            textComposite.add(sentence);
			getSuccessor().parse(sentence, matcher.group().trim());
        }
    }
}
