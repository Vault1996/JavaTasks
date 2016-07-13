package by.epam.parsing.parser;

import by.epam.parsing.entity.composite.Composite;
import by.epam.parsing.entity.composite.Leaf;
import by.epam.parsing.exception.NotLeafException;
import by.epam.parsing.type.TypeOfTextUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeafTextParser extends AbstractParser{

    private static final Logger LOGGER = LogManager.getLogger(LeafTextParser.class);

    private static final String WORD_PATTERN = "[\\w\\-]+";
    private static final String PUNCTUATION_PATTERN = "[()\"'?!,\\.—]";
    private static final String FORMULA_PATTERN = "[+\\-*/()\\d]{2,}+";

    private static final Pattern WORD;
    private static final Pattern PUNCTUATION;
    private static final Pattern FORMULA;


    static {
        WORD = Pattern.compile(WORD_PATTERN);
        PUNCTUATION = Pattern.compile(PUNCTUATION_PATTERN);
        FORMULA = Pattern.compile(FORMULA_PATTERN);
    }

    @Override
    public void parse(Composite textComposite, String text) {
        boolean isEnd = false;
        if(!text.isEmpty()) {
            Matcher wordMatcher = WORD.matcher(text);
            Matcher punctuationMatcher = PUNCTUATION.matcher(text);
            Matcher formulaMatcher = FORMULA.matcher(text);
            Leaf leaf;
            try {
                if ("—".equals(text)) {
                    leaf = new Leaf(TypeOfTextUnit.PUNCTUATION);
                    leaf.setText("—");
                    textComposite.add(leaf);
                    return;
                }
                if (formulaMatcher.find()) {
                    leaf = new Leaf(TypeOfTextUnit.FORMULA);
                    leaf.setText(formulaMatcher.group());
                    textComposite.add(leaf);
                } else if (wordMatcher.find()) {
                    if (punctuationMatcher.find()) {
                        if (punctuationMatcher.end() != text.length()) {
                            leaf = new Leaf(TypeOfTextUnit.PUNCTUATION);
                            leaf.setText(punctuationMatcher.group());
                            textComposite.add(leaf);
                        } else {
                            isEnd = true;
                        }
                    }
                    leaf = new Leaf(TypeOfTextUnit.WORD);
                    leaf.setText(wordMatcher.group());
                    textComposite.add(leaf);
                    if (isEnd) {
                        leaf = new Leaf(TypeOfTextUnit.PUNCTUATION);
                        leaf.setText(punctuationMatcher.group());
                        textComposite.add(leaf);
                    } else if (punctuationMatcher.find()) {
                        leaf = new Leaf(TypeOfTextUnit.PUNCTUATION);
                        leaf.setText(punctuationMatcher.group());
                        textComposite.add(leaf);

                    }
                }
            } catch (NotLeafException e) {
                LOGGER.error("This element is not a leaf.");
            }
        }
    }
}
