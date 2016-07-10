package by.epam.card.factory;

import by.epam.card.builder.AbstractCardsBuilder;
import by.epam.card.builder.CardsDOMBuilder;
import by.epam.card.builder.CardsSAXBuilder;
import by.epam.card.builder.CardsStAXBuilder;
import by.epam.card.exception.NoSuchEnumElementException;

public class CardsBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }
    private CardsBuilderFactory() {}
    private static class SingletonHolder { // nested class
        private final static CardsBuilderFactory INSTANCE = new CardsBuilderFactory();
    }
    public static CardsBuilderFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
    public AbstractCardsBuilder createStudentBuilder(String typeParser) throws NoSuchEnumElementException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new CardsDOMBuilder();
            case STAX:
                return new CardsStAXBuilder();
            case SAX:
                return new CardsSAXBuilder();
            default:
                throw new NoSuchEnumElementException();
        }
    }
}