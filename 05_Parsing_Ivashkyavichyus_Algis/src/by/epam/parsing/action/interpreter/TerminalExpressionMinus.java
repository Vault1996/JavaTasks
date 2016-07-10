package by.epam.parsing.action.interpreter;

import by.epam.parsing.action.interpreter.exception.CantInterpretException;

public class TerminalExpressionMinus extends AbstractMathExpression {
	@Override
	public void interpret(Context c) throws CantInterpretException {
		Integer tmp = c.popValue();
		c.pushValue(c.popValue() - tmp);
	}
}
