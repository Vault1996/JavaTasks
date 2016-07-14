package by.epam.parsing.action.interpreter;

import by.epam.parsing.exception.CantInterpretException;

public class TerminalExpressionPlus extends AbstractMathExpression {
	@Override
	public void interpret(Context c) throws CantInterpretException {
		c.pushValue(c.popValue() + c.popValue());
	}
}