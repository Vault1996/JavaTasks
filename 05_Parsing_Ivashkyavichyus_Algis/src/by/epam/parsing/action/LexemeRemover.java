package by.epam.parsing.action;

import by.epam.parsing.entity.composite.Composite;
import by.epam.parsing.entity.composite.Leaf;
import by.epam.parsing.type.TypeOfTextUnit;

public class LexemeRemover {
	public void removeLexeme(Composite textUnit, int length, char startLetter) {
		for (int i = 0; i < textUnit.size(); i++) {
			if(textUnit.getType() == TypeOfTextUnit.SENTENCE){
				Composite composite = (Composite) textUnit.getChild(i);
				String text = "";
				for (int j = 0; j < composite.size(); j++) {
					text += ((Leaf) composite.getChild(j)).getText();
				}
				if(!text.isEmpty()) {
					if (text.charAt(0) == startLetter && text.length() == length) {
						textUnit.remove(composite);
					}
				}
			} else {
				Composite composite = (Composite) textUnit.getChild(i);
				removeLexeme(composite, length, startLetter);
			}
		}
	}
}
