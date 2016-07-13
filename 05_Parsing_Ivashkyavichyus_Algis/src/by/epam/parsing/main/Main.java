package by.epam.parsing.main;

import by.epam.parsing.action.*;
import by.epam.parsing.entity.composite.Composite;
import by.epam.parsing.type.TypeOfTextUnit;
import by.epam.parsing.parser.LeafTextParser;
import by.epam.parsing.parser.LexemeParser;
import by.epam.parsing.parser.ParagraphParser;
import by.epam.parsing.parser.SentenceParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    private static final String INPUT_FILE_PATH = "text" + File.separator + "input.txt";
    private static final String OUTPUT_FILE_PATH = "text" + File.separator + "output.txt";

    public static void main(String[] args) {
        //**********************
        TextReader textReader = new TextReader();
        String text = textReader.readFromFile(INPUT_FILE_PATH);
        //***************
        Composite textComposite = new Composite(TypeOfTextUnit.TEXT);
        LeafTextParser leafTextParser = new LeafTextParser();
        LexemeParser lexemeParser = new LexemeParser(leafTextParser);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        paragraphParser.parse(textComposite, text);
        //*******************
        ExpressionCalculator expressionCalculator = new ExpressionCalculator();
        expressionCalculator.calculate(textComposite);
        //*********************
        LetterChanger letterChanger = new LetterChanger();
        letterChanger.changeLetters(textComposite);
        //*********************
        LexemeRemover lexemeRemover = new LexemeRemover();
        lexemeRemover.removeLexeme(textComposite, 5, 'e');
        //*********************
        TextWriter textWriter = new TextWriter();
        textWriter.printToFile(OUTPUT_FILE_PATH, textComposite);
    }
}
