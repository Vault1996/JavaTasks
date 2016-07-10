package by.epam.card.handler;

import by.epam.card.entity.OldCard;
import by.epam.card.entity.PostCard;
import by.epam.card.entity.SoundCard;
import by.epam.card.enumeration.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
public class CardHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(CardHandler.class);

    private Set<OldCard> cards;

    private PostCard postCard = null;
    private SoundCard soundCard = null;
    private String currentType = null;
    private CardEnum currentEnum = null;
    private EnumSet<CardEnum> withText;
    public CardHandler() {
        cards = new HashSet<>();
        withText = EnumSet.range(CardEnum.THEMA, CardEnum.FORMAT);
    }
    public Set<OldCard> getCards() {
        return cards;
    }
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (CardEnum.POST_CARD.getValue().equals(localName)) {
            currentType = CardEnum.POST_CARD.getValue();
            postCard = new PostCard();
            if (attrs.getLength() == 2) {
                if (CardEnum.ID.getValue().equals(attrs.getType(0))) {
                    postCard.setId(attrs.getValue(0));
                    postCard.setAuthor(attrs.getValue(1));
                } else {
                    postCard.setId(attrs.getValue(1));
                    postCard.setAuthor(attrs.getValue(0));
                }
            } else{
                postCard.setId(attrs.getValue(0));
            }
        } else if(CardEnum.SOUND_CARD.getValue().equals(localName)) {
            currentType = CardEnum.SOUND_CARD.getValue();
            soundCard = new SoundCard();
            if (attrs.getLength() == 2) {
                if (CardEnum.ID.getValue().equals(attrs.getType(0))) {
                    soundCard.setId(attrs.getValue(0));
                    soundCard.setAuthor(attrs.getValue(1));
                } else {
                    soundCard.setAuthor(attrs.getValue(0));
                    soundCard.setId(attrs.getValue(1));
                }
            } else{
                soundCard.setId(attrs.getValue(0));
            }
        } else {
            try {
                CardEnum temp = CardEnum.valueOf(toConstantForm(localName));
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
            } catch (IllegalArgumentException e) {
                LOGGER.error("Can't resolve element " + localName);
            }
        }
    }
    public void endElement(String uri, String localName, String qName) {
        if (CardEnum.POST_CARD.getValue().equals(localName)) {
            cards.add(postCard);
            currentType = null;
        } else if (CardEnum.SOUND_CARD.getValue().equals(localName)) {
            cards.add(soundCard);
            currentType = null;
        }
    }
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        try {
            if (currentEnum != null) {
                if (CardEnum.POST_CARD.getValue().equals(currentType)) {
                    switch (currentEnum) {
                        case THEMA:
                            postCard.setThema(Thema.fromValue(s));
                            break;
                        case TYPE:
                            postCard.setType(Type.fromValue(s));
                            break;
                        case COUNTRY:
                            postCard.setCountry(Country.fromValue(s));
                            break;
                        case YEAR:
                            postCard.setYear(s);
                            break;
                        case VALUABLE:
                            postCard.setValuable(Boolean.valueOf(s));
                            break;
                        case MESSAGE:
                            postCard.setMessage(s);
                            break;
                        default:
                            LOGGER.error(currentEnum.getDeclaringClass() + " " + currentEnum.name());
                    }
                } else if (CardEnum.SOUND_CARD.getValue().equals(currentType)) {
                    switch (currentEnum) {
                        case COUNTRY:
                            soundCard.setCountry(Country.fromValue(s));
                            break;
                        case THEMA:
                            soundCard.setThema(Thema.fromValue(s));
                            break;
                        case TYPE:
                            soundCard.setType(Type.fromValue(s));
                            break;
                        case YEAR:
                            soundCard.setYear(s);
                            break;
                        case VALUABLE:
                            soundCard.setValuable(Boolean.valueOf(s));
                            break;
                        case SONG:
                            soundCard.setSong(s);
                            break;
                        case FORMAT:
                            soundCard.setFormat(Format.fromValue(s));
                            break;
                        default:
                            LOGGER.error(currentEnum.getDeclaringClass() + " " + currentEnum.name());
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error("Can't find element " + currentType);
        }
        currentEnum = null;
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