package by.epam.card.builder;

import by.epam.card.entity.OldCard;
import by.epam.card.entity.PostCard;
import by.epam.card.entity.SoundCard;
import by.epam.card.enumeration.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
public class CardsDOMBuilder extends AbstractCardsBuilder{
    private static final Logger LOGGER = LogManager.getLogger(CardsDOMBuilder.class);

    private DocumentBuilder docBuilder;
    public CardsDOMBuilder() {
        super();
// создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Ошибка конфигурации парсера: " + e);
        }
    }
    public void buildSetCards(String fileName) {
        Document doc = null;
        try {
// parsing XML-документа и создание древовидной структуры
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList postCardsList = root.getElementsByTagName(CardEnum.POST_CARD.getValue());
            for (int i = 0; i < postCardsList.getLength(); i++) {
                Element postCardElement = (Element) postCardsList.item(i);
                OldCard oldCard = buildPostCard(postCardElement);
                cards.add(oldCard);
            }
            NodeList soundCardsList = root.getElementsByTagName(CardEnum.SOUND_CARD.getValue());
            for (int i = 0; i < soundCardsList.getLength(); i++) {
                Element soundCardElement = (Element) soundCardsList.item(i);
                OldCard oldCard = buildSoundCard(soundCardElement);
                cards.add(oldCard);
            }
        } catch (IOException e) {
            LOGGER.error("File error or I/O error: " + e);
        } catch (SAXException e) {
            LOGGER.error("Parsing failure: " + e);
        }
    }
    private OldCard buildPostCard(Element cardElement) {
        PostCard postCard = new PostCard();
        if (!cardElement.getAttribute(CardEnum.AUTHOR.getValue()).isEmpty()) {
            postCard.setAuthor(cardElement.getAttribute(CardEnum.AUTHOR.getValue()));
        }
        postCard.setId(cardElement.getAttribute(CardEnum.ID.getValue()));
        postCard.setType(Type.fromValue(getElementTextContent(cardElement, CardEnum.TYPE.getValue())));
        postCard.setThema(Thema.fromValue(getElementTextContent(cardElement, CardEnum.THEMA.getValue())));
        postCard.setCountry(Country.fromValue(getElementTextContent(cardElement, CardEnum.COUNTRY.getValue())));
        postCard.setYear(getElementTextContent(cardElement, CardEnum.YEAR.getValue()));
        postCard.setValuable(Boolean.valueOf(getElementTextContent(cardElement, CardEnum.VALUABLE.getValue())));
        postCard.setMessage(getElementTextContent(cardElement, CardEnum.MESSAGE.getValue()));
        return postCard;
    }
    private OldCard buildSoundCard(Element cardElement) {
        SoundCard soundCard = new SoundCard();
        if (!cardElement.getAttribute(CardEnum.AUTHOR.getValue()).isEmpty()) {
            soundCard.setAuthor(cardElement.getAttribute(CardEnum.AUTHOR.getValue()));
        }
        soundCard.setId(cardElement.getAttribute(CardEnum.ID.getValue()));
        soundCard.setType(Type.fromValue(getElementTextContent(cardElement, CardEnum.TYPE.getValue())));
        soundCard.setThema(Thema.fromValue(getElementTextContent(cardElement, CardEnum.THEMA.getValue())));
        soundCard.setCountry(Country.fromValue(getElementTextContent(cardElement, CardEnum.COUNTRY.getValue())));
        soundCard.setYear(getElementTextContent(cardElement, CardEnum.YEAR.getValue()));
        soundCard.setValuable(Boolean.valueOf(getElementTextContent(cardElement, CardEnum.VALUABLE.getValue())));
        soundCard.setSong(getElementTextContent(cardElement, CardEnum.SONG.getValue()));
        soundCard.setFormat(Format.fromValue(getElementTextContent(cardElement,CardEnum.FORMAT.getValue())));
        return soundCard;
    }
    // получение текстового содержимого тега
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        if (node != null) {
            return node.getTextContent();
        } else {
            return "";
        }
    }
}