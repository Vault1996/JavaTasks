package by.epam.parsing.parser;

import by.epam.parsing.entity.composite.Composite;

public abstract class AbstractParser {

    private AbstractParser successor = DefaultParseRequest.getHandleRequest();

    public AbstractParser(AbstractParser successor) {
        this.successor = successor;
    }
    public AbstractParser() {

    }

    public AbstractParser getSuccessor() {
        return successor;
    }

    public void setSuccessor(AbstractParser successor) {
        this.successor = successor;
    }

    abstract void parse(Composite textComposite, String text);

    private static class DefaultParseRequest extends AbstractParser {
        private static DefaultParseRequest handler = new DefaultParseRequest();
        private DefaultParseRequest() {
        }
        public static DefaultParseRequest getHandleRequest() {
            return handler;
        }

        @Override
        void parse(Composite textComposite, String text) {

        }
    }
}
