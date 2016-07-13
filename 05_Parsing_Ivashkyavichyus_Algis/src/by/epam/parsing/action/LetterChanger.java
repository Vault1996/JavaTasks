package by.epam.parsing.action;

import by.epam.parsing.entity.composite.Composite;
import by.epam.parsing.entity.composite.Leaf;
import by.epam.parsing.parser.LeafTextParser;
import by.epam.parsing.type.TypeOfTextUnit;

public class LetterChanger {
	public void changeLetters(Composite textUnit) {
		for (int i = 0; i < textUnit.size(); i++) {
			if(textUnit.getType() == TypeOfTextUnit.SENTENCE){
				Composite composite = (Composite) textUnit.getChild(i);
				String text = "";
				for (int j = 0; j < composite.size(); j++) {
					text += ((Leaf) composite.getChild(j)).getText();
				}
				if (!text.isEmpty()) {
					text = text.replaceAll(Character.valueOf(text.charAt(0)).toString(), "");
					removeAllChildren(composite);
					LeafTextParser leafTextParser = new LeafTextParser();
					leafTextParser.parse(composite, text);
					if (composite.size() == 0) {
						textUnit.remove(composite);
						i--;
					}
				}
			} else {
				Composite composite = (Composite) textUnit.getChild(i);
				changeLetters(composite);
			}
		}
	}

	private void removeAllChildren(Composite composite) {
		for (int i = composite.size() - 1; i >= 0; i--) {
			composite.remove(composite.getChild(i));
		}
	}
}
