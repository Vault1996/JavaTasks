package by.epam.card.builder;

import by.epam.card.entity.OldCard;
import by.epam.card.entity.PostCard;
import by.epam.card.entity.SoundCard;
import by.epam.card.enumeration.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class CardsStAXBuilder extends AbstractCardsBuilder{
    private static final Logger LOGGER = LogManager.getLogger(CardsStAXBuilder.class);

    private XMLInputFactory inputFactory;
    public CardsStAXBuilder() {
        super();
        inputFactory = XMLInputFactory.newInstance();
    }
    public void buildSetCards(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
// StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    try {
                        if (CardEnum.valueOf(toConstantForm(name)) == CardEnum.POST_CARD) {
                            OldCard st = buildPostCard(reader);
                            cards.add(st);
                        }
                        if (CardEnum.valueOf(toConstantForm(name)) == CardEnum.SOUND_CARD) {
                            OldCard st = buildSoundCard(reader);
                            cards.add(st);
                        }
                    } catch (IllegalArgumentException e) {
                        LOGGER.error("Can't resolve element " + name);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            LOGGER.error("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            LOGGER.error("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error("Impossible close file "+fileName+" : "+e);
            }
        }
    }
    private OldCard buildPostCard (XMLStreamReader reader) {
        PostCard st = new PostCard();
        st.setId(reader.getAttributeValue(null, CardEnum.ID.getValue()));
        if (reader.getAttributeValue(null, CardEnum.AUTHOR.getValue()) != null) {
            st.setAuthor(reader.getAttributeValue(null,CardEnum.AUTHOR.getValue()));
        }
        String name = "";
        try {
            while (reader.hasNext()) {
                int type = reader.next();
                try {
                    switch (type) {
                        case XMLStreamConstants.START_ELEMENT:
                            name = reader.getLocalName();
                            switch (CardEnum.valueOf(toConstantForm(name))) {
                                case THEMA:
                                    st.setThema(Thema.fromValue(getXMLText(reader)));
                                    break;
                                case TYPE:
                                    st.setType(Type.fromValue(getXMLText(reader)));
                                    break;
                                case COUNTRY:
                                    st.setCountry(Country.fromValue(getXMLText(reader)));
                                    break;
                                case YEAR:
                                    st.setYear(getXMLText(reader));
                                    break;
                                case VALUABLE:
                                    st.setValuable(Boolean.valueOf(getXMLText(reader)));
                                    break;
                                case MESSAGE:
                                    st.setMessage(getXMLText(reader));
                                    break;
                                default:
                                    LOGGER.error("Can't resolve the element.");
                            }
                            break;
                        case XMLStreamConstants.END_ELEMENT:
                            name = reader.getLocalName();
                            if (CardEnum.valueOf(toConstantForm(name)) == CardEnum.POST_CARD) {
                                return st;
                            }
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    LOGGER.error("Can't resolve element " + name);
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error("Can't resolve element " + name);
        } catch (XMLStreamException e) {
            LOGGER.error("Unknown element in tag PostCard");
        }
        return new PostCard();
    }
    private OldCard buildSoundCard (XMLStreamReader reader){
        SoundCard st = new SoundCard();
        st.setId(reader.getAttributeValue(null, CardEnum.ID.getValue()));
        if (reader.getAttributeValue(null, CardEnum.AUTHOR.getValue()) != null) {
            st.setAuthor(reader.getAttributeValue(null,CardEnum.AUTHOR.getValue()));
        }
        String name = "";
        try {
            while (reader.hasNext()) {
                int type = reader.next();
                try {
                    switch (type) {
                        case XMLStreamConstants.START_ELEMENT:
                            name = reader.getLocalName();
                            switch (CardEnum.valueOf(toConstantForm(name))) {
                                case THEMA:
                                    st.setThema(Thema.fromValue(getXMLText(reader)));
                                    break;
                                case TYPE:
                                    st.setType(Type.fromValue(getXMLText(reader)));
                                    break;
                                case COUNTRY:
                                    st.setCountry(Country.fromValue(getXMLText(reader)));
                                    break;
                                case YEAR:
                                    st.setYear(getXMLText(reader));
                                    break;
                                case VALUABLE:
                                    st.setValuable(Boolean.valueOf(getXMLText(reader)));
                                    break;
                                case SONG:
                                    st.setSong(getXMLText(reader));
                                    break;
                                case FORMAT:
                                    st.setFormat(Format.fromValue(getXMLText(reader)));
                                    break;
                                default:
                                    LOGGER.error("Can't resolve the element.");
                            }
                            break;
                        case XMLStreamConstants.END_ELEMENT:
                            name = reader.getLocalName();
                            if (CardEnum.valueOf(toConstantForm(name)) == CardEnum.SOUND_CARD) {
                                return st;
                            }
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    LOGGER.error("Can't resolve element " + name);
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.error("Unknown element in tag SoundCard");
        }
        return new SoundCard();
    }
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
    private String toConstantForm(String string) {
        for (int i = 1; i < string.length(); i++) {
            if ('A' <= string.charAt(i) && string.charAt(i) <= 'Z') {
                string = string.substring(0, i) + '_' + string.substring(i);
                i++;
            }
        }
        return string.toUpperCase();
    }
}
