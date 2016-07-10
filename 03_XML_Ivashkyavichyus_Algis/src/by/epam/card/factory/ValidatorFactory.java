package by.epam.card.factory;

import by.epam.card.exception.NoSuchEnumElementException;
import by.epam.card.validation.AbstractValidator;
import by.epam.card.validation.ValidatorSAX;
import by.epam.card.validation.ValidatorSAXXSD;

public class ValidatorFactory {
    private enum TypeValidator {
        SAX, VALIDATOR;
    }
    private ValidatorFactory() {}
    private static class SingletonHolder { // nested class
        private final static ValidatorFactory INSTANCE = new ValidatorFactory();
    }
    public static ValidatorFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
    public AbstractValidator newValidator(String typeValidator) throws NoSuchEnumElementException{
        TypeValidator type = TypeValidator.valueOf(typeValidator.toUpperCase());
        switch (type) {
            case SAX:
                return new ValidatorSAX();
            case VALIDATOR:
                return new ValidatorSAXXSD();
            default:
                throw new NoSuchEnumElementException();
        }
    }
}