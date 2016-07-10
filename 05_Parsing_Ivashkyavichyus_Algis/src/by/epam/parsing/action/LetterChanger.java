package by.epam.parsing.action;

import by.epam.parsing.entity.composite.Composite;

public class LetterChanger {
	public void changeLetter(Composite text) {
		text.changeLetter();
		text.removeEmptyLexemes();
	}
}
