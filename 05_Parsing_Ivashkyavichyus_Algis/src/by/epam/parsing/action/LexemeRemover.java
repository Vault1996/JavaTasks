package by.epam.parsing.action;

import by.epam.parsing.entity.composite.Composite;

public class LexemeRemover {
	public void removeLexeme(Composite text, int length, char startLetter) {
		text.removeLexeme(length, startLetter);
		text.removeEmptyLexemes();
	}
}
