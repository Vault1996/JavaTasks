package by.epam.parsing.action.interpreter;

import by.epam.parsing.action.interpreter.exception.CantInterpretException;

public class NonTerminalExpressionNumber extends AbstractMathExpression {
	private int number;
	public NonTerminalExpressionNumber(int number) {
		this.number = number;
	}
	@Override
	public void interpret (Context c) throws CantInterpretException {
		c.pushValue(number);
	}
}
