package by.epam.card.builder;

import by.epam.card.entity.OldCard;
import by.epam.card.handler.CardHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;
public class CardsSAXBuilder extends AbstractCardsBuilder{
    private static final Logger LOGGER = LogManager.getLogger(CardsSAXBuilder.class);

    private CardHandler cardHandler;
    private XMLReader reader;
    public CardsSAXBuilder() {
        super();
// создание SAX-анализатора
        cardHandler = new CardHandler();
        try {
// создание объекта-обработчика
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(cardHandler);
        } catch (SAXException e) {
            LOGGER.error("ошибка SAX парсера: " + e);
        }
    }
    public Set<OldCard> getCards() {
        return cards;
    }
    public void buildSetCards(String fileName) {
        try {
// разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            LOGGER.error("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            LOGGER.error("ошибка I/О потока: " + e);
        }
        cards = cardHandler.getCards();
    }
}
