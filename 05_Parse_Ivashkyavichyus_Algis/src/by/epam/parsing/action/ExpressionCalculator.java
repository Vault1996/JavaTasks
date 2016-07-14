package by.epam.parsing.action;


import by.epam.parsing.exception.CantInterpretException;
import by.epam.parsing.action.interpreter.Interpreter;
import by.epam.parsing.entity.composite.Component;
import by.epam.parsing.entity.composite.Leaf;
import by.epam.parsing.exception.NoSuchOperationException;
import by.epam.parsing.type.TypeOfTextUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExpressionCalculator {
	private static final Logger LOGGER = LogManager.getLogger(ExpressionCalculator.class);

	public void calculate(Component textUnit){
		try {
			for (int i = 0; i < textUnit.size(); i++) {
				if (textUnit.getType() == TypeOfTextUnit.LEXEME) {
					Component component = textUnit.getChild(i);
					if (component.getType() == TypeOfTextUnit.FORMULA) {
						String formula = ((Leaf) component).getText();
						try {
							Interpreter interpreter = new Interpreter(formula);
							formula = interpreter.calculate().toString();
							((Leaf) component).setText(formula);
						} catch (CantInterpretException e) {
							LOGGER.warn("Can't interpret expression " + formula);
						}
					}
				} else {
					Component component = textUnit.getChild(i);
					calculate(component);
				}
			}
		} catch (NoSuchOperationException e) {
			LOGGER.error("getChild() in Leaf");
		}
	}
}
