package by.epam.card.builder;

import by.epam.card.entity.OldCard;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCardsBuilder {
    protected Set<OldCard> cards;
    public AbstractCardsBuilder() {
        cards = new HashSet<OldCard>();
    }
    public AbstractCardsBuilder(Set<OldCard> cards) {
        this.cards = cards;
    }
    public Set<OldCard> getCards() {
        return cards;
    }
    abstract public void buildSetCards(String fileName);
}
