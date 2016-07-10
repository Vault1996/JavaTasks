package by.epam.parsing.action.interpreter;

import by.epam.parsing.action.interpreter.exception.CantInterpretException;

public abstract class AbstractMathExpression {
	public abstract void interpret(Context context) throws CantInterpretException;
}