package by.epam.card.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class CardErrorHandler extends DefaultHandler {

    private static final Logger LOGGER = LogManager.getLogger(CardErrorHandler.class);

    public CardErrorHandler(){}

    public void warning(SAXParseException e){
        LOGGER.warn(getLineAddress(e) + "-" + e.getMessage());
    }
    public void error(SAXParseException e){
        LOGGER.error(getLineAddress(e) + " - " + e.getMessage());
    }
    public void fatalError(SAXParseException e){
        LOGGER.fatal(getLineAddress(e) + " - " + e.getMessage());
    }
    private String getLineAddress(SAXParseException e) {
// определение строки и столбца ошибки
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
