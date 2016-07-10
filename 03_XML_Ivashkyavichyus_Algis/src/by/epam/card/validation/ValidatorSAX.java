package by.epam.card.validation;

import by.epam.card.handler.CardErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
public class ValidatorSAX extends AbstractValidator{
    private static final Logger LOGGER = LogManager.getLogger(ValidatorSAX.class);
    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    public void validate(String fileName, String schemaName) {
        Schema schema = null;
        SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);
        try {
            // установка проверки с использованием XSD
            schema = factory.newSchema(new File(schemaName));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            spf.setSchema(schema);
            // создание объекта-парсера
            SAXParser parser = spf.newSAXParser();
            // установка обработчика ошибок и запуск
            parser.parse(fileName, new CardErrorHandler());
        } catch (ParserConfigurationException e) {
            LOGGER.error(fileName + " config error: " + e.getMessage());
        } catch (SAXException e) {
            LOGGER.error(fileName + " SAX error: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("I/O error: " + e.getMessage());
        }

    }
}