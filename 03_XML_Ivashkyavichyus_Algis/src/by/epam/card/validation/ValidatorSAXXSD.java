package by.epam.card.validation;

import by.epam.card.handler.CardErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
public class ValidatorSAXXSD extends AbstractValidator{
    private static final Logger LOGGER = LogManager.getLogger(ValidatorSAX.class);
    private static final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;

    public void validate(String fileName, String schemaName) {
        SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);
        File schemaLocation = new File(schemaName);
        try {
            // создание схемы
            Schema schema = factory.newSchema(schemaLocation);
            // создание валидатора на основе схемы
            Validator validator = schema.newValidator();
            // проверка документа
            Source source = new StreamSource(fileName);
            validator.setErrorHandler(new CardErrorHandler());
            validator.validate(source);
        } catch (SAXException e) {
            LOGGER.error("validation "+ fileName + " is not valid because "
                    + e.getMessage());
        } catch (IOException e) {
            LOGGER.error(fileName + " is not valid because "
                    + e.getMessage());
        }
    }
}
