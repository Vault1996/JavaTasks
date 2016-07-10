package by.epam.parsing.action.interpreter;

import by.epam.parsing.action.interpreter.exception.CantInterpretException;

public class TerminalExpressionMultiply extends AbstractMathExpression {
	@Override
	public void interpret(Context c) throws CantInterpretException {
		c.pushValue(c.popValue() * c.popValue());
	}
}