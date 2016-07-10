package by.epam.card.main;

import by.epam.card.builder.CardsDOMBuilder;
import by.epam.card.entity.OldCard;
import by.epam.card.exception.NoSuchEnumElementException;
import by.epam.card.factory.CardsBuilderFactory;
import by.epam.card.factory.ValidatorFactory;
import by.epam.card.validation.ValidatorSAXXSD;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        try {
            String fileName = "resources/OldCards.xml";
            String schemaName = "resources/OldCards.xsd";
            ValidatorFactory validatorFactory = ValidatorFactory.getInstance();
            ValidatorSAXXSD validator = (ValidatorSAXXSD) validatorFactory.newValidator("validator");
            //ValidatorSAX validator = (ValidatorSAX) validatorFactory.newValidator("sax");
            LOGGER.info("Validating file " + fileName);
            validator.validate(fileName, schemaName);
            LOGGER.info("Validation ended");
            CardsBuilderFactory factory = CardsBuilderFactory.getInstance();
            //CardsSAXBuilder builder = (CardsSAXBuilder) factory.createStudentBuilder("sax");
            CardsDOMBuilder builder = (CardsDOMBuilder) factory.createStudentBuilder("dom");
            //CardsStAXBuilder builder = (CardsStAXBuilder) factory.createStudentBuilder("stax");
            builder.buildSetCards(fileName);
            Set<OldCard> set = builder.getCards();
            LOGGER.info("Built set:");
            for (OldCard card : set) {
                LOGGER.info(card);
            }
        } catch (NoSuchEnumElementException e) {
            LOGGER.error("Can't resolve enum element.");
        }
    }
}
